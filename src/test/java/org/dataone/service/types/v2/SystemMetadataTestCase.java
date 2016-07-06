/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dataone.service.types.v2;

import org.dataone.service.types.D1NamespaceContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.dataone.exceptions.MarshallingException;
import org.apache.log4j.Logger;
import org.dataone.service.types.v1.AccessPolicy;
import org.dataone.service.types.v1.AccessRule;
import org.dataone.service.types.v1.Checksum;
import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.NodeReference;
import org.dataone.service.types.v1.ObjectFormatIdentifier;
import org.dataone.service.types.v1.Permission;
import org.dataone.service.types.v1.Replica;
import org.dataone.service.types.v1.ReplicationPolicy;
import org.dataone.service.types.v1.ReplicationStatus;
import org.dataone.service.types.v1.Subject;
import org.dataone.service.util.TypeMarshaller;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 *
 * @author waltz
 */
public class SystemMetadataTestCase {

    private static Logger log = Logger.getLogger(SystemMetadataTestCase.class);
    private static SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    private static NamespaceContext namespaceContext = new D1NamespaceContext();
    private static XPath xpath = XPathFactory.newInstance().newXPath();
    private static DocumentBuilder domParser;
    /* Confirm that all the fields have been correctly populated

     */

    @BeforeClass
    public static void initBeforeClass() throws ParserConfigurationException {
        documentBuilderFactory.setNamespaceAware(true);

        documentBuilderFactory.setValidating(false);
        xpath.setNamespaceContext(namespaceContext);
        domParser = documentBuilderFactory.newDocumentBuilder();
    }
    /* serialize and then check the results of the xml against
     xpath queries to ensure that all the elements are 
     accounted for that were set
     */

    @Test
    public void systemMetadataCheck() {
        try {

            SystemMetadata sysMeta = createBaseSystemMetadata("test001");
            ReplicationPolicy replicationPolicy = new ReplicationPolicy();
            replicationPolicy.setNumberReplicas(3);
            replicationPolicy.setReplicationAllowed(Boolean.TRUE);
            sysMeta.setReplicationPolicy(replicationPolicy);
            Date now = new Date();
            Replica replica = new Replica();
            NodeReference replicaReference = new NodeReference();
            replicaReference.setValue("urn:node:cnDevTest");
            replica.setReplicaMemberNode(replicaReference);
            replica.setReplicaVerified(now);
            replica.setReplicationStatus(ReplicationStatus.COMPLETED);
            List<Replica> replicaList = new ArrayList<>();
            replicaList.add(replica);

            sysMeta.setReplicaList(replicaList);
            log.info(sysMeta.sizeReplicaList());

            AccessRule accessRule = new AccessRule();
            Subject subject = new Subject();
            subject.setValue("you@autochthonic.org");
            Permission permission = Permission.WRITE;
            accessRule.addSubject(subject);
            accessRule.addPermission(permission);

            AccessPolicy accessPolicy = new AccessPolicy();
            accessPolicy.addAllow(accessRule);

            sysMeta.setAccessPolicy(accessPolicy);

            InputStream sysMetaInputStream = typeToInputStream(sysMeta);
            Document sysMetaDom = domParser.parse(sysMetaInputStream);

            try {
                log.info(printDocument(sysMetaDom));
            } catch (Exception ex) {
                log.error(ex, ex);
            }

            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/identifier"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/identifier[text()='test001']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/seriesId[text()='testest']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/serialVersion[text()='1']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/formatId[text()='eml://ecoinformatics.org/eml-2.1.0']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/size[text()='243']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/checksum[@algorithm='MD5'][text()='72643530066e4d67016fa814479eeda3']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/rightsHolder[text()='me@autochthonic.org']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/originMemberNode[text()='urn:node:thatMN']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/authoritativeMemberNode[text()='urn:node:thisMN']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/replicationPolicy[@numberReplicas='3'][@replicationAllowed='true']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/replica/replicaMemberNode[text()='urn:node:cnDevTest']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/replica/replicationStatus[text()='completed']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/accessPolicy/allow/subject[text()='you@autochthonic.org']"));
            assertTrue(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/accessPolicy/allow/permission[text()='write']"));
        } catch (Exception ex) {
            log.error(ex, ex);
            fail("Test misconfiguration" + ex);
        }

    }
    /* https://redmine.dataone.org/issues/7422
     AccessPolicy should not be serialized as an empty element
     Also, AccessPolicy should not be serialized unless 
     it is valid.
     */
    @Test
    public void systemMetadataNoEmptyAccessPolicy() throws Exception {

        SystemMetadata sysMeta = createBaseSystemMetadata("test002");

            // with an access policy that is blank
        // the serialized result should not
        // have an access policy element
        AccessPolicy accessPolicy = new AccessPolicy();

        sysMeta.setAccessPolicy(accessPolicy);

        InputStream sysMetaInputStream = typeToInputStream(sysMeta);
        Document sysMetaDom = domParser.parse(sysMetaInputStream);
/*        try {
            log.info(printDocument(sysMetaDom));
        } catch (Exception ex) {
            log.error(ex, ex);
        } */
        assertFalse(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/accessPolicy"));

            // add an empty array of access rules
        // the serialized result should not
        // have an access policy element            
        List<AccessRule> accessRules = new ArrayList<>();
        accessPolicy.setAllowList(accessRules);
        sysMetaInputStream = typeToInputStream(sysMeta);
        sysMetaDom = domParser.parse(sysMetaInputStream);

        assertFalse(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/accessPolicy"));

            // the following tests result in serialization exceptions 
            // add a blank accessRule to the accessPolicy's 
        // accessRule list
        boolean passed = false;
        AccessRule accessRule = new AccessRule();
        accessPolicy.addAllow(accessRule);
        try {
            sysMetaInputStream = typeToInputStream(sysMeta);
        } catch (IllegalStateException ex) {
            log.info("Passed with " + ex.getMessage());
            passed = true;
        }
        assertTrue(passed);

            // add a blank Subject to the accessRule
        passed = false;
        Subject subject = new Subject();
        accessRule.addSubject(subject);

        try {
            sysMetaInputStream = typeToInputStream(sysMeta);
        } catch (IOException e) {
            log.info("Passed with " + e.getMessage());
            passed = true;
        }
        assertTrue(passed);

        passed = false;

            // make the added subject valid, but
        // have an empty permission's list
        subject.setValue("testest");

        try {
            sysMetaInputStream = typeToInputStream(sysMeta);
        } catch (IllegalStateException ex) {
            log.info("Passed with " + ex.getMessage());
            passed = true;
        }
        assertTrue(passed);

        passed = false;
        accessRule.clearSubjectList();

            // add a valid permission to the accessRule
        // but no subject list
        Permission permission = Permission.CHANGE_PERMISSION;
        accessRule.addPermission(permission);
        try {
            sysMetaInputStream = typeToInputStream(sysMeta);
        } catch (IllegalStateException ex) {
            log.info("Passed with " + ex.getMessage());
            passed = true;
        }
        assertTrue(passed);

    }

    @Test
    public void systemMetadataNoEmptyReplicationPolicy() throws Exception {
        SystemMetadata sysMeta = createBaseSystemMetadata("test003");
            ReplicationPolicy replicationPolicy = new ReplicationPolicy();
            sysMeta.setReplicationPolicy(replicationPolicy);
            InputStream sysMetaInputStream = typeToInputStream(sysMeta);
            Document sysMetaDom = domParser.parse(sysMetaInputStream);            
            assertFalse(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/replicationPolicy"));
            
    }
   
    @Test
    public void systemMetadataNoEmptyReplicas() throws Exception {
        SystemMetadata sysMeta = createBaseSystemMetadata("test003");
            ReplicationPolicy replicationPolicy = new ReplicationPolicy();
            sysMeta.setReplicationPolicy(replicationPolicy);
            InputStream sysMetaInputStream = typeToInputStream(sysMeta);
            Document sysMetaDom = domParser.parse(sysMetaInputStream);            
            assertFalse(hasXPath(sysMetaDom, "/d1_v2.0:systemMetadata/replicationPolicy"));
            
    }
    private InputStream typeToInputStream(Object type) throws MarshallingException, IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TypeMarshaller.marshalTypeToOutputStream(type, os);

        String result = os.toString("UTF-8");

        os.close();

        ByteArrayInputStream is = new ByteArrayInputStream(result.getBytes());
        return is;
    }

    public boolean hasXPath(Document doc, String expression) throws Exception {

        org.w3c.dom.NodeList result = (org.w3c.dom.NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);

        if (result == null || result.getLength() <= 0) {
            return false;
        } else {

            return true;
        }

    }

    public String printDocument(Document document) throws Exception {
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

    private SystemMetadata createBaseSystemMetadata(String pid) {
        SystemMetadata sysMeta = new SystemMetadata();
        sysMeta.setIdentifier(new Identifier());
        sysMeta.getIdentifier().setValue(pid);
        sysMeta.setSeriesId(new Identifier());
        sysMeta.getSeriesId().setValue("testest");
        sysMeta.setAuthoritativeMemberNode(new NodeReference());
        sysMeta.getAuthoritativeMemberNode().setValue("urn:node:thisMN");
        sysMeta.setOriginMemberNode(new NodeReference());
        sysMeta.getOriginMemberNode().setValue("urn:node:thatMN");
        sysMeta.setChecksum(new Checksum());
        sysMeta.getChecksum().setAlgorithm("MD5");
        sysMeta.getChecksum().setValue("72643530066e4d67016fa814479eeda3");
        Date now = new Date();
        sysMeta.setDateSysMetadataModified(now);
        sysMeta.setDateUploaded(now);
        sysMeta.setFormatId(new ObjectFormatIdentifier());
        sysMeta.getFormatId().setValue("eml://ecoinformatics.org/eml-2.1.0");
        sysMeta.setRightsHolder(new Subject());
        sysMeta.getRightsHolder().setValue("me@autochthonic.org");
        sysMeta.setSerialVersion(BigInteger.ONE);
        sysMeta.setSize(new BigInteger("243"));
        return sysMeta;
    }
}
