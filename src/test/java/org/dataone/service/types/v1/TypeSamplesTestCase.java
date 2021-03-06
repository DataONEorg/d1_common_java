/**
 * This work was created by participants in the DataONE project, and is
 * jointly copyrighted by participating institutions in DataONE. For
 * more information on DataONE, see our web site at http://dataone.org.
 *
 *   Copyright ${year}
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
 */
package org.dataone.service.types.v1;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.dataone.exceptions.MarshallingException;
import org.dataone.service.types.v2.util.ObjectFormatServiceImpl;
import org.dataone.service.util.TypeMarshaller;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TypeSamplesTestCase {

    private static Logger logger = Logger.getLogger(TypeSamplesTestCase.class);
    static final String datatypeSchemaTagUrl = "https://repository.dataone.org/software/cicore/tags/D1_SCHEMA_1_0_2/";
    static String datatypeSchemaLocation = datatypeSchemaTagUrl + "dataoneTypes.xsd";
    static String systemMetadataSchemaLocation = datatypeSchemaTagUrl + "dataoneTypes.xsd";
    static String systemObjectListSchemaLocation = datatypeSchemaTagUrl + "dataoneTypes.xsd";
    static String systemLoggingSchemaLocation = datatypeSchemaTagUrl + "dataoneTypes.xsd";
    static String systemNodeRegistrySchemaLocation = datatypeSchemaTagUrl + "dataoneTypes.xsd";
    static String systemIdentifierSchemaLocation = datatypeSchemaTagUrl + "dataoneTypes.xsd";
    static String systemChecksumSchemaLocation = datatypeSchemaTagUrl + "dataoneTypes.xsd";

    @Test
    public void fake() throws Exception {
        // parse an XML document into a DOM tree
        assertTrue(true);
    }

    @Test
    public void validateSysmetaSample() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(validateExamples(systemMetadataSchemaLocation, "/org/dataone/service/samples/v1/systemMetadataSample1.xml"));

    }

    @Test
    public void validateSysmetaMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(testSystemMetadataMarshalling("/org/dataone/service/samples/v1/systemMetadataSample1.xml"));

    }

    @Test
    public void validateSysmetaSampleUnicodeSupplEscaped() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(validateExamples(systemMetadataSchemaLocation, "/org/dataone/service/samples/v1/systemMetadataSampleUnicodeSupplEscaped.xml"));

    }

//    @Test
    public void validateSysmetaSampleUnicodeSupplEscapedMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(testSystemMetadataMarshalling("/org/dataone/service/samples/v1/systemMetadataSampleUnicodeSupplEscaped.xml"));

    }

    @Test
    public void validateObjectListSample() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(validateExamples(systemObjectListSchemaLocation, "/org/dataone/service/samples/v1/objectListSample1.xml"));

    }

    @Test
    public void validateObjectListMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(testObjectListMarshalling("/org/dataone/service/samples/v1/objectListSample1.xml"));

    }

    @Test
    public void validateLoggingSample() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(validateExamples(systemLoggingSchemaLocation, "/org/dataone/service/samples/v1/loggingSample1.xml"));

    }

    @Test
    public void validateLoggingMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(testLoggingMarshalling("/org/dataone/service/samples/v1/loggingSample1.xml"));

    }

    @Test
    public void validateNodeRegistrySample() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(validateExamples(systemNodeRegistrySchemaLocation, "/org/dataone/service/samples/v1/nodeListSample1.xml"));

    }
    @Test
    public void validateNodeRegistryMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {
        assertTrue(testNodeListMarshalling("/org/dataone/service/samples/v1/nodeListSample1.xml"));
    }
    @Test
    public void validateNodeSample() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(validateExamples(systemMetadataSchemaLocation, "/org/dataone/service/samples/v1/mnNode1.xml"));

    }

    @Test
    public void validateIdentifierSample() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(validateExamples(systemIdentifierSchemaLocation, "/org/dataone/service/samples/v1/identifier1.xml"));

    }

    @Test
    public void validateIdentifierMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(testIdentifierMarshalling("/org/dataone/service/samples/v1/identifier1.xml"));

    }

    @Test
    public void validateChecksumSample() throws Exception, SAXException, IOException, ParserConfigurationException {
// TODO arguments should be injected based on version of service api to test and build
        assertTrue(validateExamples(systemChecksumSchemaLocation, "/org/dataone/service/samples/v1/checksum1.xml"));

    }

    @Test
    public void validateChecksuMarshalling() throws Exception, SAXException, IOException, ParserConfigurationException {

        assertTrue(testChecksumMarshalling("/org/dataone/service/samples/v1/checksum1.xml"));

    }

    
    @Test
    public void serializeReplica() throws MarshallingException, IOException {
        Replica r = new Replica();
        r.setReplicaMemberNode(TypeFactory.buildNodeReference("urn:node:mnTestFOO"));
        r.setReplicationStatus(ReplicationStatus.COMPLETED);
        r.setReplicaVerified(new Date());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TypeMarshaller.marshalTypeToOutputStream(r, baos);
       
        System.out.println(baos.toString());
    }
    
    
    private boolean validateExamples(String xsdUrlString, InputStream xmlInputStream) throws Exception, SAXException, IOException, ParserConfigurationException {
        DocumentBuilder parser;
        // create a SchemaFactory capable of understanding WXS schemas
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Document document;
        Schema schema;
        Source schemaFile;
        URL xsdUrl = new URL(xsdUrlString);

        URLConnection xsdUrlConnection = xsdUrl.openConnection();
        InputStream xsdUrlStream = xsdUrlConnection.getInputStream();
        if (xsdUrlStream == null) {
            logger.info(xsdUrlString + " InputStream is null");
        } else {
            logger.info("Validate: " + xsdUrlString);
        }


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
        document = parser.parse(xmlInputStream);
        logger.info(document.getDocumentElement().getNodeName());
        // create a Validator instance, which can be used to validate an instance document

        ValidateXmlDocument validateXmlDocument = new ValidateXmlDocument(schema);


        // validate the DOM tree

        return validateXmlDocument.validate(document);

    }

    private boolean validateExamples(String xsdUrlString, String xmlDocument) throws Exception, SAXException, IOException, ParserConfigurationException {
        return validateExamples(xsdUrlString, this.getClass().getResourceAsStream(xmlDocument));
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

            logger.info(toString(document));


            DOMSource domSource = new DOMSource(document);
            if (domSource == null) {
                throw new Exception("THE SOURCE IS NULL");
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

    @Test
    public void testSimpleSystemMetadataMarshalling() throws Exception {
        logger.info("Starting testing of testSimpleSystemMetadataMarshalling");
        SystemMetadata systemMetadata = new SystemMetadata();

        systemMetadata.setSerialVersion(BigInteger.ONE);
        Identifier identifier = new Identifier();
        identifier.setValue("ABC432");
//        systemMetadata.
        systemMetadata.setIdentifier(identifier);
//        ObjectFormat objectFormat;
        ObjectFormatIdentifier fmtid = new ObjectFormatIdentifier();
        fmtid.setValue("CF-1.0");
        ObjectFormat thisOF = ObjectFormatServiceImpl.getInstance().getFormat(fmtid);
        assertNotNull(thisOF);
        assertNotNull(thisOF.getFormatId());
        assertNotNull(thisOF.getFormatName());
        logger.info(thisOF.getFormatId().getValue() + " = " + thisOF.getFormatName());
        systemMetadata.setFormatId(fmtid);
        systemMetadata.setSize(new BigInteger("1235431"));
        Subject submitter = new Subject();
        submitter.setValue("Kermit de Frog");
        systemMetadata.setSubmitter(submitter);
        Subject rightsHolder = new Subject();
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
        checksum.setAlgorithm("SHA-1");

        systemMetadata.setChecksum(checksum);
        List<Replica> replicaList = new ArrayList<Replica>();

        systemMetadata.setReplicaList(replicaList);
        NodeReference nodeReference = new NodeReference();
        nodeReference.setValue("mn1");
        Replica originalReplica = new Replica();
        originalReplica.setReplicaMemberNode(nodeReference);
        originalReplica.setReplicationStatus(ReplicationStatus.COMPLETED);
        originalReplica.setReplicaVerified(new Date());

        systemMetadata.addReplica(originalReplica);

        ByteArrayOutputStream testSytemMetadataOutput = new ByteArrayOutputStream();

        TypeMarshaller.marshalTypeToOutputStream(systemMetadata,  testSytemMetadataOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);

        logger.info(testSytemMetadataOutput.toString());
        ByteArrayInputStream testSystemMetadataInput = new ByteArrayInputStream(testSytemMetadataOutput.toByteArray());



        systemMetadata =  TypeMarshaller.unmarshalTypeFromStream(SystemMetadata.class, testSystemMetadataInput);
        assertTrue(systemMetadata != null);
        assertTrue(systemMetadata.getIdentifier().getValue().equalsIgnoreCase("ABC432"));
        testSystemMetadataInput.reset();
        assertTrue(validateExamples(systemMetadataSchemaLocation, testSystemMetadataInput));
    }

    public boolean testSystemMetadataMarshalling(String externalSystemMetadata) throws Exception {
        logger.info("Starting testing of testSystemMetadataMarshalling");
        SystemMetadata systemMetadata = new SystemMetadata();

        InputStream inputStream = this.getClass().getResourceAsStream(externalSystemMetadata);
        try {
            systemMetadata = TypeMarshaller.unmarshalTypeFromStream(SystemMetadata.class, inputStream);
            ByteArrayOutputStream testSytemMetadataOutput = new ByteArrayOutputStream();
            TypeMarshaller.marshalTypeToOutputStream(systemMetadata,testSytemMetadataOutput);
            logger.info(testSytemMetadataOutput.toString());
            assertTrue(validateExamples(systemMetadataSchemaLocation, new ByteArrayInputStream(testSytemMetadataOutput.toByteArray())));

        } finally {
            inputStream.close();
        }
        return true;
    }

    // FIXME: fix missing ObjectInfo.setObjectFormat() method
    public boolean testObjectListMarshalling(String externalObjectList) throws Exception {
        ObjectList objectList = new ObjectList();
        objectList.setCount(3);
        objectList.setStart(0);
        objectList.setTotal(3);

        List<ObjectInfo> objectInfoList = new ArrayList<ObjectInfo>();

        objectList.setObjectInfoList(objectInfoList);

        ObjectInfo objectInfo1 = new ObjectInfo();
        Identifier identifier1 = new Identifier();
        identifier1.setValue("ABC123");
        objectInfo1.setIdentifier(identifier1);
        ObjectFormatIdentifier fmtid = new ObjectFormatIdentifier();
        fmtid.setValue("CF-1.0");
        objectInfo1.setFormatId(fmtid);
        Checksum checksum1 = new Checksum();
        checksum1.setValue("V29ybGQgSGVsbG8h");
        checksum1.setAlgorithm("SHA-1");
        objectInfo1.setChecksum(checksum1);
        objectInfo1.setDateSysMetadataModified(new Date());
        objectInfo1.setSize(new BigInteger("412341324"));
        objectList.addObjectInfo(objectInfo1);

        ObjectInfo objectInfo2 = new ObjectInfo();
        Identifier identifier2 = new Identifier();
        identifier2.setValue("ABC456");
        objectInfo2.setIdentifier(identifier1);
        ObjectFormatIdentifier fmtid2 = new ObjectFormatIdentifier();
        fmtid2.setValue("http://digir.net/schema/conceptual/darwin/2003/1.0/darwin2.xsd");
        objectInfo2.setFormatId(fmtid2);
        Checksum checksum2 = new Checksum();
        checksum2.setValue("V29ybGQgSGVsaF89");
        checksum2.setAlgorithm("MD5");
        objectInfo2.setChecksum(checksum1);
        objectInfo2.setDateSysMetadataModified(new Date());
        objectInfo2.setSize(new BigInteger("9087654"));
        objectList.addObjectInfo(objectInfo2);

        ObjectInfo objectInfo3 = new ObjectInfo();
        Identifier identifier3 = new Identifier();
        identifier3.setValue("ABC456");
        objectInfo3.setIdentifier(identifier1);
        ObjectFormatIdentifier fmtid3 = new ObjectFormatIdentifier();
        fmtid3.setValue("FGDC-STD-001-1998");
        objectInfo3.setFormatId(fmtid3);
        Checksum checksum3 = new Checksum();
        checksum3.setValue("V29ybGQgSGVsaF89ybGE8987adf3");
        checksum3.setAlgorithm("SHA-512");
        objectInfo3.setChecksum(checksum1);
        objectInfo3.setDateSysMetadataModified(new Date());
        objectInfo3.setSize(new BigInteger("90654"));
        objectList.addObjectInfo(objectInfo3);


        ByteArrayOutputStream testObjectListOutput = new ByteArrayOutputStream();
        TypeMarshaller.marshalTypeToOutputStream(objectList, testObjectListOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);


        ByteArrayInputStream testObjectListInput = new ByteArrayInputStream(testObjectListOutput.toByteArray());
        
        objectList = TypeMarshaller.unmarshalTypeFromStream(ObjectList.class, testObjectListInput);

        InputStream inputStream = this.getClass().getResourceAsStream(externalObjectList);
        try {
            objectList = TypeMarshaller.unmarshalTypeFromStream(ObjectList.class, inputStream);

        } finally {
            inputStream.close();
        }
        // validate deserialized Composed object above
        testObjectListInput.reset();
        assertTrue(validateExamples(systemObjectListSchemaLocation, testObjectListInput));

        // validate deserialized resource stream
        testObjectListOutput.reset();
        TypeMarshaller.marshalTypeToOutputStream(objectList, testObjectListOutput);
        testObjectListInput = new ByteArrayInputStream(testObjectListOutput.toByteArray());
        assertTrue(validateExamples(systemObjectListSchemaLocation, testObjectListInput));
        return true;
    }

    @Test
    public void testSubjectInfoMarshalling() throws Exception {
        logger.info("Starting testing of testSubjectInfoMarshalling");
        SubjectInfo subjectInfo = new SubjectInfo();

        // set the properties of SubjectInfo
        String subjectValue = "cn=test1,dc=dataone,dc=org";
        Subject subject = new Subject();
        subject.setValue(subjectValue);
        Person person = new Person();
        person.setSubject(subject);
        person.addGivenName("test");
        person.setFamilyName("test");
        person.addEmail("test@dataone.org");
        person.setVerified(Boolean.TRUE);
        subjectInfo.addPerson(person);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        TypeMarshaller.marshalTypeToOutputStream(subjectInfo,  baos);

        logger.info(baos.toString());
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());



        subjectInfo = (SubjectInfo) TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, bais);
        assertTrue(subjectInfo != null);
        assertTrue(subjectInfo.getPerson(0).getSubject().getValue().equals(subjectValue));
        bais.reset();
        assertTrue(validateExamples(datatypeSchemaLocation, bais));
    }

    public boolean testLoggingMarshalling(String externalLoggingObjects) throws Exception {

        Log log = new Log();
        List<LogEntry> logEntryList = new ArrayList<LogEntry>();

        log.setLogEntryList(logEntryList);

        LogEntry logEntry1 = new LogEntry();
        logEntry1.setDateLogged(new Date());
        String entry1 = "1";
        logEntry1.setEntryId(entry1);

        logEntry1.setEvent(Event.READ);
        Identifier id1 = new Identifier();
        id1.setValue("ABC");
        logEntry1.setIdentifier(id1);

        logEntry1.setIpAddress("123.123.123.123");

        NodeReference memberNode = new NodeReference();
        memberNode.setValue("mn1");
        logEntry1.setNodeIdentifier(memberNode);

        Subject subject1 = new Subject();
        subject1.setValue("Scooter");
        logEntry1.setSubject(subject1);

        logEntry1.setUserAgent("Mozilla/4.0 (compatible; MSIE 6.0; Update a; AOL 6.0; Windows 98)");
        log.addLogEntry(logEntry1);

        LogEntry logEntry2 = new LogEntry();
        logEntry2.setDateLogged(new Date());
        String entry2 = "2";
        logEntry2.setEntryId(entry2);

        logEntry2.setEvent(Event.READ);
        Identifier id2 = new Identifier();
        id2.setValue("DEF");
        logEntry2.setIdentifier(id2);

        logEntry2.setIpAddress("123.123.123.123");

        NodeReference memberNode2 = new NodeReference();
        memberNode2.setValue("mn1");
        logEntry2.setNodeIdentifier(memberNode2);

        Subject subject2 = new Subject();
        subject2.setValue("Fozzie Bear");
        logEntry2.setSubject(subject1);

        logEntry2.setUserAgent("Mozilla/4.0 (compatible; MSIE 6.0; Update a; AOL 6.0; Windows 98)");
        log.addLogEntry(logEntry2);


        ByteArrayOutputStream testLogOutput = new ByteArrayOutputStream();

        TypeMarshaller.marshalTypeToOutputStream(log,  testLogOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);


        ByteArrayInputStream testLogInput = new ByteArrayInputStream(testLogOutput.toByteArray());


        log = (Log) TypeMarshaller.unmarshalTypeFromStream(Log.class, testLogInput);

        InputStream inputStream = this.getClass().getResourceAsStream(externalLoggingObjects);
        try {
            log = (Log) TypeMarshaller.unmarshalTypeFromStream(Log.class, inputStream);

        } finally {
            inputStream.close();
        }
        // validate deserialized Composed object above
        testLogInput.reset();
        assertTrue(validateExamples(systemLoggingSchemaLocation, testLogInput));

        // validate deserialized resource stream
        testLogOutput.reset();
        TypeMarshaller.marshalTypeToOutputStream(log,  testLogOutput);
        testLogInput = new ByteArrayInputStream(testLogOutput.toByteArray());
        assertTrue(validateExamples(systemLoggingSchemaLocation, testLogInput));
        return true;
    }

    public boolean testNodeListMarshalling(String externalObjectList) throws Exception {
        NodeList nodeList = new NodeList();
        Node sq1dMNNode = new Node();
        String sq1dId = "sq1d";
        NodeReference sq1dNodeReference = new NodeReference();
        sq1dNodeReference.setValue(sq1dId);
        sq1dMNNode.setIdentifier(sq1dNodeReference);
        sq1dMNNode.setName("squid");
        sq1dMNNode.setDescription("this is a squid test");
        sq1dMNNode.setBaseURL("https://my.squid.test/mn");
        sq1dMNNode.setReplicate(false);
        sq1dMNNode.setSynchronize(false);
        sq1dMNNode.setState(NodeState.UP);
        sq1dMNNode.setType(NodeType.MN);
        Subject sq1dSubject = new Subject();
        sq1dSubject.setValue("cn="+sq1dId+",dc=dataone,dc=org");
        sq1dMNNode.addSubject(sq1dSubject);
        Services sq1dservices = new Services();
        Service sq1dcoreService = new Service();
        sq1dcoreService.setName("MNCore");
        sq1dcoreService.setVersion("v1");
        sq1dcoreService.setAvailable(Boolean.TRUE);

        Service sq1dreadService = new Service();
        sq1dreadService.setName("MNRead");
        sq1dreadService.setVersion("v1");
        sq1dreadService.setAvailable(Boolean.TRUE);
        sq1dservices.addService(sq1dcoreService);
        sq1dservices.addService(sq1dreadService);
        sq1dMNNode.setServices(sq1dservices);

        Subject contactSubject = new Subject();
        contactSubject.setValue("cn=who,dc=where,dc=there");
        sq1dMNNode.addContactSubject(contactSubject);
        nodeList.addNode(sq1dMNNode);


        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        TypeMarshaller.marshalTypeToOutputStream(nodeList,  testOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);

        byte[] nodeRegistryTestOutput = testOutput.toByteArray();
        String nodeRegistryStringOutput = new String(nodeRegistryTestOutput);
        logger.info(nodeRegistryStringOutput);
        ByteArrayInputStream testInput = new ByteArrayInputStream(nodeRegistryTestOutput);

        //       BindingDirectory.getFactory("binding", "org.dataone.service.types");


        nodeList = TypeMarshaller.unmarshalTypeFromStream(NodeList.class, testInput);

        InputStream inputStream = this.getClass().getResourceAsStream(externalObjectList);
        try {
            nodeList = TypeMarshaller.unmarshalTypeFromStream(NodeList.class, inputStream);

        } finally {
            inputStream.close();
        }
        // validate deserialized Composed object above
        testInput.reset();
        assertTrue(validateExamples(systemNodeRegistrySchemaLocation, testInput));

        // validate deserialized resource stream
        testOutput.reset();
        TypeMarshaller.marshalTypeToOutputStream(nodeList,  testOutput);
        testInput = new ByteArrayInputStream(testOutput.toByteArray());
        assertTrue(validateExamples(systemNodeRegistrySchemaLocation, testInput));
        return true;
    }

    public boolean testIdentifierMarshalling(String identifierDoc) throws Exception {
        Identifier id = new Identifier();
        id.setValue("ABC123");


        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        TypeMarshaller.marshalTypeToOutputStream(id,  testOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);

        byte[] identifierTestOutput = testOutput.toByteArray();
        String identifierStringOutput = new String(identifierTestOutput);
        logger.info(identifierStringOutput);
        ByteArrayInputStream testInput = new ByteArrayInputStream(identifierTestOutput);


        id = TypeMarshaller.unmarshalTypeFromStream(Identifier.class, testInput);

        InputStream inputStream = this.getClass().getResourceAsStream(identifierDoc);
        try {
            id = TypeMarshaller.unmarshalTypeFromStream(Identifier.class, inputStream);

        } finally {
            inputStream.close();
        }
        // validate deserialized Composed object above
        testInput.reset();
        assertTrue(validateExamples(systemIdentifierSchemaLocation, testInput));

        // validate deserialized resource stream
        testOutput.reset();
        TypeMarshaller.marshalTypeToOutputStream(id,  testOutput);
        testInput = new ByteArrayInputStream(testOutput.toByteArray());
        assertTrue(validateExamples(systemIdentifierSchemaLocation, testInput));
        return true;
    }

    public boolean testChecksumMarshalling(String checksumDoc) throws Exception {
        Checksum checksum = new Checksum();
        checksum.setValue("ADSFA21341234ADSFADSF");
        checksum.setAlgorithm("SHA-1");


        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

        TypeMarshaller.marshalTypeToOutputStream(checksum,  testOutput);

        //       InputStream inputStream = this.getClass().getResourceAsStream(xmlDocument);

        byte[] checksumTestOutput = testOutput.toByteArray();
        String checksumStringOutput = new String(checksumTestOutput);
        logger.info(checksumStringOutput);
        ByteArrayInputStream testInput = new ByteArrayInputStream(checksumTestOutput);

        checksum = (Checksum) TypeMarshaller.unmarshalTypeFromStream(Checksum.class, testInput);

        InputStream inputStream = this.getClass().getResourceAsStream(checksumDoc);
        try {
            checksum = (Checksum) TypeMarshaller.unmarshalTypeFromStream(Checksum.class, inputStream);

        } finally {
            inputStream.close();
        }
        // validate deserialized Composed object above
        testInput.reset();
        assertTrue(validateExamples(systemChecksumSchemaLocation, testInput));

        // validate deserialized resource stream
        testOutput.reset();
        TypeMarshaller.marshalTypeToOutputStream(checksum,  testOutput);
        testInput = new ByteArrayInputStream(testOutput.toByteArray());
        assertTrue(validateExamples(systemChecksumSchemaLocation, testInput));
        return true;
    }
}
