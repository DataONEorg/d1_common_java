package org.dataone.security;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.coyote.InputBuffer;
import org.apache.tomcat.util.net.ApplicationBufferHandler;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Temporary mitigation Valve to reject multipart XML parts that declare DTDs/entities (XXE defense).
 * Added 2026-07-10.
 * Enable via the {@code <Valve>} element under {@code <Host ...>} in server.xml, e.g.:
 * {@code <Valve className="org.dataone.security.XmlSecurityValidationValve" />}
 */
public class XmlSecurityValidationValve extends ValveBase {

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        String contentType = request.getContentType();

        // Only inspect if it's a multipart request
        if (contentType != null && contentType.toLowerCase().startsWith("multipart/form-data")) {
            try {
                // 1. Buffer the raw input stream
                InputStream rawInputStream = request.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = rawInputStream.read(buffer)) > -1) {
                    baos.write(buffer, 0, len);
                }
                byte[] requestBytes = baos.toByteArray();

                // 2. Parse the multipart data using Tomcat's built-in FileUpload utilities
                ServletRequestContext requestContext = new ServletRequestContext(request) {
                    @Override
                    public InputStream getInputStream() {
                        return new ByteArrayInputStream(requestBytes);
                    }
                };

                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest(requestContext);

                for (FileItem item : items) {
                    // Check if the part is an XML content type or looks like XML
                    String partContentType = item.getContentType();
                    if (isXmlType(partContentType, item.getName())) {
                        
                        // 3. Inspect for DTD / External Entities
                        if (containsForbiddenXmlStructures(item.getInputStream())) {
                            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Malicious XML content detected.");
                            return; // Halt processing immediately
                        }
                    }
                }

                // 4. Re-inject the buffered bytes back into Tomcat's pipeline for downstream processing
                request.getCoyoteRequest().setInputBuffer(new InputBuffer() {
                    private final ByteArrayInputStream bais = new ByteArrayInputStream(requestBytes);

                    @Override
                    public int doRead(ApplicationBufferHandler handler) throws IOException {
                        byte[] buf = new byte[8192];
                        int read = bais.read(buf);
                        if (read > 0) {
                            handler.setByteBuffer(ByteBuffer.wrap(buf, 0, read));
                        }
                        return read;
                    }

                    @Override
                    public int available() {
                        return bais.available();
                    }
                });

            } catch (Exception e) {
                // Handle parsing errors or malicious attempts gracefully
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request payload.");
                return;
            }
        }

        // If safe or not multipart, pass to the next valve in the chain
        getNext().invoke(request, response);
    }

    private boolean isXmlType(String contentType, String fileName) {
        if (contentType != null) {
            String ct = contentType.toLowerCase();
            if (ct.contains("text/xml") || ct.contains("application/xml")) {
                return true;
            }
        }
        return fileName != null && fileName.toLowerCase().endsWith(".xml");
    }

    private boolean containsForbiddenXmlStructures(InputStream xmlStream) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            
            // 1. DO NOT disallow DOCTYPE completely.
            spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
            
            // 2. Enable external general entities & parameter entities processing 
            // so our custom resolver can catch them if they are present.
            spf.setFeature("http://xml.org/sax/features/external-general-entities", true);
            spf.setFeature("http://xml.org/sax/features/external-parameter-entities", true);
            spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);

            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            
            // 3. Create a strict interceptor handler
            DefaultHandler2 strictSecurityHandler = new DefaultHandler2() {
                
                // Catch External DTDs and External General Entities
                @Override
                public InputSource resolveEntity(String name, String publicId, String baseURI, String systemId) throws org.xml.sax.SAXException {
                    if (systemId != null || publicId != null) {
                        throw new org.xml.sax.SAXException("Malicious XML: External entity or DTD resolution blocked: " + systemId);
                    }
                    return null; 
                }

                // Catch External Parameter Entities inside the DOCTYPE declaration
                @Override
                public InputSource getExternalSubset(String name, String baseURI) throws org.xml.sax.SAXException {
                    throw new org.xml.sax.SAXException("Malicious XML: External DTD subset blocked.");
                }

                // Catch Entity Declarations (like SYSTEM "file:///") before they can even be resolved
                @Override
                public void externalEntityDecl(String name, String publicId, String systemId) throws org.xml.sax.SAXException {
                    throw new org.xml.sax.SAXException("Malicious XML: External entity declaration detected.");
                }
            };

            // Register the handler for both resolution and advanced lexical intercepting
            xmlReader.setEntityResolver(strictSecurityHandler);
            xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", strictSecurityHandler);
            
            // Parse the stream to trigger the interceptor if anything malicious is declared
            xmlReader.parse(new InputSource(xmlStream));
            
            return false; // Safe! No external definitions or resolutions were triggered.
        } catch (Exception e) {
            // Exception thrown by our security handler means we intercepted an attack vector
            return true; 
        }
    }

}
