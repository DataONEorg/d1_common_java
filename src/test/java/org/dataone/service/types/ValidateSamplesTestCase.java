package org.dataone.service.types;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.junit.*;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import org.dataone.service.types.Checksum;
import org.dataone.service.types.ChecksumAlgorithm;
import org.dataone.service.types.Event;
import org.dataone.service.types.Identifier;
import org.dataone.service.types.ListObjects;
import org.dataone.service.types.Log;
import org.dataone.service.types.LogEntry;
import org.dataone.service.types.NodeReference;
import org.dataone.service.types.ObjectFormat;
import org.dataone.service.types.ObjectInfo;
import org.dataone.service.types.Principal;
import org.dataone.service.types.SystemMetadata;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;


import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import static org.junit.Assert.*;

public class ValidateSamplesTestCase {

    @Test
    public void fake() throws Exception {
        // parse an XML document into a DOM tree
        assertTrue(true);
    }

    @Test
    public void validateSysmetaSample() throws Exception, SAXException, IOException, ParserConfigurationException {

        assertTrue(validateExamples("https://repository.dataone.org/software/cicore/trunk/schemas/systemmetadata.xsd","/org/dataone/service/samples/systemMetadataSample1.xml"));

    }

    @Test
    public void validateSysmetaMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {

        assertTrue(testSystemMetadataMarshalling("/org/dataone/service/samples/systemMetadataSample1.xml"));

    }

    @Test
    public void validateListObjectsSample() throws Exception, SAXException, IOException, ParserConfigurationException {

        assertTrue(validateExamples("https://repository.dataone.org/software/cicore/trunk/schemas/listobjects.xsd","/org/dataone/service/samples/listObjectsSample1.xml"));

    }

    @Test
    public void validateListObjectsMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {

        assertTrue(testListObjectsMarshalling("/org/dataone/service/samples/listObjectsSample1.xml"));

    }

    @Test
    public void validateLoggingSample() throws Exception, SAXException, IOException, ParserConfigurationException {

        assertTrue(validateExamples("https://repository.dataone.org/software/cicore/trunk/schemas/logging.xsd","/org/dataone/service/samples/loggingSample1.xml"));

    }

    @Test
    public void validateLoggingMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {

        assertTrue(testLoggingMarshalling("/org/dataone/service/samples/loggingSample1.xml"));

    }

    private boolean validateExamples(String xsdUrlString, String xmlDocument) throws Exception, SAXException, IOException, ParserConfigurationException {
        DocumentBuilder parser;
        // create a SchemaFactory capable of understanding WXS schemas
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Document document;
        Schema schema;
        Source schemaFile;
         URL xsdUrl = new URL(xsdUrlString);
        
        URLConnection xsdUrlConnection = xsdUrl.openConnection();
        InputStream xsdUrlStream = xsdUrlConnection.getInputStream();
        if (xsdUrlStream  == null)
                System.out.println(xsdUrlString + " InputStream is null");
        else
                System.out.println("Validate: " + xsdUrlString);


        // load a WXS schema, represented by a Schema instance

        
        schemaFile = new StreamSource(xsdUrlStream);
        schema = factory.newSchema(schemaFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setSchema(schema);
        documentBuilderFactory.setValidating(false);

        parser = documentBuilderFactory.newDocumentBuilder();

        // load in the file to validate
        document = parser.parse(this.getClass().getResourceAsStream(xmlDocument));
        System.out.println(document.getDocumentElement().getNodeName());
        // create a Validator instance, which can be used to validate an instance document

        ValidateXmlDocument validateXmlDocument = new ValidateXmlDocument(schema);


        // validate the DOM tree

        return validateXmlDocument.validate(document);
         
    }

    /**
     *
     * @author Robert P Waltz.
     */
    private class ValidateXmlDocument {
//	    Logger logger = Logger.getRootLogger();

        private class ErrorHandlerImpl implements ErrorHandler {

            public void warning(SAXParseException exception) throws SAXException {
                System.out.print(exception.getMessage());
                // do nothing;
            }

            public void error(SAXParseException exception) throws SAXException {
                System.out.print(exception.getMessage());
//	            logger.warn(exception.getMessage());
                throw exception;
            }

            public void fatalError(SAXParseException exception) throws SAXException {
                System.out.print(exception.getMessage());
//	            logger.warn(exception.getMessage());
                throw exception;
            }
        }
        private Validator validator = null;

        public ValidateXmlDocument(Schema schema) {
            // TODO Auto-generated constructor stub
            try {
                ErrorHandlerImpl errorHandlerImpl = new ErrorHandlerImpl();

                validator = schema.newValidator();
                validator.setErrorHandler(errorHandlerImpl);
            } catch (Exception e) {
//                logger.error("FATAL ERROR: INITIALIZATION OF XML VALIDATE SERVICE IMPL\n",e);
                validator = null;
            }
        }

        public boolean validate(Document document) throws SAXException, Exception {

            System.out.print(toString(document));


            DOMSource domSource = new DOMSource(document);
            if (domSource == null) {
                throw new Exception("TEI SOURCE IS NULL");
            }
            validator.validate(domSource);

            return true;
        }

        public String toString(Document document) throws Exception {
            String result = null;

            if (document != null) {
                StringWriter strWtr = new StringWriter();
                StreamResult strResult = new StreamResult(strWtr);
                TransformerFactory tfac = TransformerFactory.newInstance();

                Transformer t = tfac.newTransformer();
                t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                t.setOutputProperty(OutputKeys.METHOD, "xml"); //xml, html, text
                t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                t.transform(new DOMSource(document.getDocumentElement()), strResult);

                result = strResult.getWriter().toString();
            }

            return result;
        }//toString()
    }

    public boolean testSystemMetadataMarshalling(String externalSystemMetadata) throws Exception {
        SystemMetadata systemMetadata = new SystemMetadata();

        Identifier identifier = new Identifier();
        identifier.setValue("ABC432");
//        systemMetadata.
        systemMetadata.setIdentifier(identifier);
        ObjectFormat objectFormat;

        systemMetadata.setObjectFormat(ObjectFormat.CF_1_0);
        systemMetadata.setSize(1235431);
        Principal submitter = new Principal();
        submitter.setValue("Kermit de Frog");
        systemMetadata.setSubmitter(submitter);
        Principal rightsHolder = new Principal();
        rightsHolder.setValue("DataONE");
        systemMetadata.setRightsHolder(rightsHolder);
        systemMetadata.setDateSysMetadataModified(new Date());
        systemMetadata.setDateUploaded(new Date());
        NodeReference originMemberNode = new NodeReference();
        originMemberNode.setValue("mn1");
        systemMetadata.setOriginMemberNode(originMemberNode);
        NodeReference authoritativeMemberNode = new NodeReference();
        authoritativeMemberNode.setValue("mn1");
        systemMetadata.setAuthoritativeMemberNode(authoritativeMemberNode);
        Checksum checksum = new Checksum();
        checksum.setValue("V29ybGQgSGVsbG8h");
        checksum.setAlgorithm(ChecksumAlgorithm.SH_A1);

        systemMetadata.setChecksum(checksum);

        IBindingFactory bfact =
                BindingDirectory.getFactory(org.dataone.service.types.SystemMetadata.class);

        IMarshallingContext mctx = bfact.createMarshallingContext();
        ByteArrayOutputStream testSytemMetadataOutput = new ByteArrayOutputStream();

        mctx.marshalDocument(systemMetadata, "UTF-8", null, testSytemMetadataOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);


        ByteArrayInputStream testSystemMetadataInput = new ByteArrayInputStream(testSytemMetadataOutput.toByteArray());

        //       BindingDirectory.getFactory("binding", "org.dataone.service.types");
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();

        systemMetadata = (SystemMetadata) uctx.unmarshalDocument(testSystemMetadataInput, null);

        InputStream inputStream = this.getClass().getResourceAsStream(externalSystemMetadata);
        try {
            systemMetadata = (SystemMetadata) uctx.unmarshalDocument(inputStream, null);

        } finally {
            inputStream.close();
        }
        return true;
    }

    public boolean testListObjectsMarshalling(String externalListObjects) throws Exception {
        ListObjects listObjects = new ListObjects();
        listObjects.setCount(3);
        listObjects.setStart(0);
        listObjects.setTotal(3);

        List <ObjectInfo> objectInfoList = new ArrayList<ObjectInfo>();

        listObjects.setObjectInfoList(objectInfoList);

        ObjectInfo objectInfo1 = new ObjectInfo();
        Identifier identifier1 = new Identifier();
        identifier1.setValue("ABC123");
        objectInfo1.setIdentifier(identifier1);
        objectInfo1.setObjectFormat(ObjectFormat.CF_1_0);
        Checksum checksum1 = new Checksum();
        checksum1.setValue("V29ybGQgSGVsbG8h");
        checksum1.setAlgorithm(ChecksumAlgorithm.SH_A1);
        objectInfo1.setChecksum(checksum1);
        objectInfo1.setDateSysMetadataModified(new Date());
        objectInfo1.setSize(412341324);
        listObjects.addObjectInfo(objectInfo1);

        ObjectInfo objectInfo2 = new ObjectInfo();
        Identifier identifier2 = new Identifier();
        identifier2.setValue("ABC456");
        objectInfo2.setIdentifier(identifier1);
        objectInfo2.setObjectFormat(ObjectFormat.DARWIN_2);
        Checksum checksum2 = new Checksum();
        checksum2.setValue("V29ybGQgSGVsaF89");
        checksum2.setAlgorithm(ChecksumAlgorithm.M_D5);
        objectInfo2.setChecksum(checksum1);
        objectInfo2.setDateSysMetadataModified(new Date());
        objectInfo2.setSize(9087654);
        listObjects.addObjectInfo(objectInfo2);

        ObjectInfo objectInfo3 = new ObjectInfo();
        Identifier identifier3 = new Identifier();
        identifier3.setValue("ABC456");
        objectInfo3.setIdentifier(identifier1);
        objectInfo3.setObjectFormat(ObjectFormat.FGDC_STD_001_1998);
        Checksum checksum3 = new Checksum();
        checksum3.setValue("V29ybGQgSGVsaF89ybGE8987adf3");
        checksum3.setAlgorithm(ChecksumAlgorithm.SH_A512);
        objectInfo3.setChecksum(checksum1);
        objectInfo3.setDateSysMetadataModified(new Date());
        objectInfo3.setSize(90654);
        listObjects.addObjectInfo(objectInfo3);


        IBindingFactory bfact =
                BindingDirectory.getFactory(org.dataone.service.types.ListObjects.class);

        IMarshallingContext mctx = bfact.createMarshallingContext();
        ByteArrayOutputStream testListObjectsOutput = new ByteArrayOutputStream();

        mctx.marshalDocument(listObjects, "UTF-8", null, testListObjectsOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);


        ByteArrayInputStream testListObjectsInput = new ByteArrayInputStream(testListObjectsOutput.toByteArray());

        //       BindingDirectory.getFactory("binding", "org.dataone.service.types");
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();

        listObjects = (ListObjects) uctx.unmarshalDocument(testListObjectsInput, null);

        InputStream inputStream = this.getClass().getResourceAsStream(externalListObjects);
        try {
            listObjects = (ListObjects) uctx.unmarshalDocument(inputStream, null);

        } finally {
            inputStream.close();
        }
        return true;
    }


    public boolean testLoggingMarshalling(String externalLoggingObjects) throws Exception {

        Log log = new Log();
        List <LogEntry> logEntryList = new ArrayList<LogEntry>();

        log.setLogEntryList(logEntryList);

        LogEntry logEntry1 = new LogEntry();
        logEntry1.setDateLogged(new Date());
        Identifier entry1 = new Identifier();
        entry1.setValue("1");
        logEntry1.setEntryId(entry1);

        logEntry1.setEvent(Event.READ);
        Identifier id1 = new Identifier();
        id1.setValue("ABC");
        logEntry1.setIdentifier(id1);

        logEntry1.setIpAddress("123.123.123.123");

        NodeReference memberNode = new NodeReference();
        memberNode.setValue("mn1");
        logEntry1.setMemberNode(memberNode);

        Principal principal1 = new Principal();
        principal1.setValue("Kermit de Frog");
        logEntry1.setPrincipal(principal1);

        logEntry1.setUserAgent("Mozilla/4.0 (compatible; MSIE 6.0; Update a; AOL 6.0; Windows 98)");
        log.addLogEntry(logEntry1);

        LogEntry logEntry2 = new LogEntry();
        logEntry2.setDateLogged(new Date());
        Identifier entry2 = new Identifier();
        entry2.setValue("2");
        logEntry2.setEntryId(entry2);

        logEntry2.setEvent(Event.READ);
        Identifier id2 = new Identifier();
        id2.setValue("DEF");
        logEntry2.setIdentifier(id2);

        logEntry2.setIpAddress("123.123.123.123");

        NodeReference memberNode2 = new NodeReference();
        memberNode2.setValue("mn1");
        logEntry2.setMemberNode(memberNode2);

        Principal principal2 = new Principal();
        principal2.setValue("Fozzy Bear");
        logEntry2.setPrincipal(principal1);

        logEntry2.setUserAgent("Mozilla/4.0 (compatible; MSIE 6.0; Update a; AOL 6.0; Windows 98)");
        log.addLogEntry(logEntry2);

        IBindingFactory bfact =
                BindingDirectory.getFactory(org.dataone.service.types.Log.class);

        IMarshallingContext mctx = bfact.createMarshallingContext();
        ByteArrayOutputStream testLogOutput = new ByteArrayOutputStream();

        mctx.marshalDocument(log, "UTF-8", null, testLogOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);


        ByteArrayInputStream testLogInput = new ByteArrayInputStream(testLogOutput.toByteArray());
/**
	BufferedReader in = new BufferedReader(
				new InputStreamReader(
				testLogInput));

	String inputLine;

	while ((inputLine = in.readLine()) != null)
	    System.out.println(inputLine);

	in.close();
*/
        //       BindingDirectory.getFactory("binding", "org.dataone.service.types");
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();

        log = (Log) uctx.unmarshalDocument(testLogInput, null);




        InputStream inputStream = this.getClass().getResourceAsStream(externalLoggingObjects);
        try {
            log = (Log) uctx.unmarshalDocument(inputStream, null);

        } finally {
            inputStream.close();
        }
        return true;
    }

}