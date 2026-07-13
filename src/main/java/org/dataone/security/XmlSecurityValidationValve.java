/**
 * This work was created by participants in the DataONE project, and is
 * jointly copyrighted by participating institutions in DataONE. For 
 * more information on DataONE, see our web site at http://dataone.org.
 *
 *   Copyright 2026
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 * 
 * $Id$
 */

package org.dataone.security;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.coyote.InputBuffer;
import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import xml.parsers.SAXParser;
import xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Temporary mitigation Valve to handle GHSA-95v2-fvxr-qg83-style path confusion/bypass attempts.
 * Added 2026-07-10.
 * Enable via the <Valve> element under <Host ...> in server.xml, e.g.:
 *     <Valve className="org.dataone.security.TemporaryMitigationValve" />
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
                    public int doRead(ByteChunk chunk) throws IOException {
                        byte[] buf = new byte[8192];
                        int read = bais.read(buf);
                        if (read > 0) {
                            chunk.setBytes(buf, 0, read);
                        }
                        return read;
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
        if (contentType != null && (contentType.contains("text/xml") || contentType.contains("application/xml"))) {
            return true;
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
