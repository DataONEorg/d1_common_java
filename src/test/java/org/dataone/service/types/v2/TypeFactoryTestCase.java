package org.dataone.service.types.v2;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.LinkedList;

import org.dataone.service.types.v1.Event;
import org.dataone.service.types.v2.Node;
import org.dataone.service.types.v2.NodeList;
import org.dataone.service.util.TypeMarshaller;
import org.jibx.runtime.JiBXException;
import org.junit.Test;

public class TypeFactoryTestCase {

//    @Test
    public void testBuildMinimalSystemMetadata() {
        fail("Not yet implemented");
    }

    @Test
    public void testCloneV1Node() throws InstantiationException, IllegalAccessException, IOException, JiBXException {

        InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v2/mnNode1.xml");
        Node v2Node = TypeMarshaller.unmarshalTypeFromStream(Node.class, is);
        Node clone = TypeFactory.clone(v2Node);

        assertTrue("The clone should be different object references", v2Node != clone);
    }

    
    @Test
    public void convertV1NodeToV2Node() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v1/mnNode1.xml");
            org.dataone.service.types.v1.Node v1Node = 
                    TypeMarshaller.unmarshalTypeFromStream(Node.class, is);
            Node v2Node = TypeFactory.convertTypeFromType(v1Node, Node.class);
            
            
            assertEquals("ContactSubjectLists should be the same size", 
                    v1Node.sizeContactSubjectList(), v2Node.sizeContactSubjectList());
            assertEquals("SubjectLists should be the same size",
                    v1Node.sizeSubjectList(),v2Node.sizeSubjectList());
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InstantiationException ex) {
            fail("Test misconfiguration" + ex);
        } catch (IllegalAccessException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (JiBXException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InvocationTargetException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (NoSuchMethodException ex) {
            fail("Test misconfiguration" +  ex);
        }
    }
    
    @Test
    public void convertV2NodeToV1Node() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v2/mnNode1.xml");
            Node v2Node = 
                    TypeMarshaller.unmarshalTypeFromStream(Node.class, is);
            
            v2Node.addProperty(new Property());
            v2Node.getProperty(0).setKey("theKey");
            v2Node.getProperty(0).setType("theType");
            v2Node.getProperty(0).setValue("theValue");
            v2Node.addProperty(new Property());
            v2Node.getProperty(1).setKey("otherKey");
            v2Node.getProperty(1).setValue("otherValue");
            
            org.dataone.service.types.v1.Node v1Node = TypeFactory.convertTypeFromType(v2Node, Node.class);

            assertEquals("ContactSubjectLists should be the same size", 
                    v1Node.sizeContactSubjectList(), v2Node.sizeContactSubjectList());
            assertEquals("SubjectLists should be the same size",
                    v1Node.sizeSubjectList(),v2Node.sizeSubjectList());
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InstantiationException ex) {
            fail("Test misconfiguration" + ex);
        } catch (IllegalAccessException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (JiBXException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InvocationTargetException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (NoSuchMethodException ex) {
            fail("Test misconfiguration" +  ex);
        }
    }
    
    @Test
    public void convertV1NodeListToV2NodeList() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v1/nodeListSample2.xml");
            org.dataone.service.types.v1.NodeList v1NodeList = 
                    TypeMarshaller.unmarshalTypeFromStream(org.dataone.service.types.v1.NodeList.class, is);
            org.dataone.service.types.v2.NodeList v2NodeList = 
                    TypeFactory.convertTypeFromType(v1NodeList, org.dataone.service.types.v2.NodeList.class);
            
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InstantiationException ex) {
            fail("Test misconfiguration" + ex);
        } catch (IllegalAccessException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (JiBXException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InvocationTargetException ex) {
                fail("Test misconfiguration" +  ex);
        } catch (NoSuchMethodException ex) {
            fail("Test misconfiguration" +  ex);
        }
    }
    

    
    @Test
    public void convertV2NodeListToV1NodeList() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v2/mnNode1.xml");

            NodeList v2NodeList = new NodeList();
            Node v2Node1 = TypeMarshaller.unmarshalTypeFromStream(Node.class, is);
            
            v2Node1.addProperty(new Property());
            v2Node1.getProperty(0).setKey("theKey");
            v2Node1.getProperty(0).setType("theType");
            v2Node1.getProperty(0).setValue("theValue");
            v2Node1.addProperty(new Property());
            v2Node1.getProperty(1).setKey("otherKey");
            v2Node1.getProperty(1).setValue("otherValue");

            v2NodeList.addNode(v2Node1);

            
            Node v2Node2 = TypeFactory.clone(v2Node1);
            v2Node2.setIdentifier(TypeFactory.buildNodeReference("nodeTwo"));
            v2NodeList.addNode(v2Node2);
            
            Node v2Node3 = TypeFactory.clone(v2Node1);
            v2Node3.setIdentifier(TypeFactory.buildNodeReference("nodeThree"));
            v2NodeList.addNode(v2Node3);
            
            Node v2Node4 = TypeFactory.clone(v2Node1);
            v2Node4.setIdentifier(TypeFactory.buildNodeReference("nodeFour"));
            v2NodeList.addNode(v2Node4);

            
            org.dataone.service.types.v1.NodeList v1Node = 
                    TypeFactory.convertTypeFromType(v2NodeList, org.dataone.service.types.v1.NodeList.class);

        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InstantiationException ex) {
            fail("Test misconfiguration" + ex);
        } catch (IllegalAccessException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (JiBXException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InvocationTargetException ex) {
                fail("Test misconfiguration" +  ex);
        } catch (NoSuchMethodException ex) {
            fail("Test misconfiguration" +  ex);
        }
    }
    
    @Test
    public void convertV2LogToV1Log_compatibleEvent() throws InstantiationException, IllegalAccessException, JiBXException, IOException {
        Log v2Log = new Log();
        v2Log.addLogEntry(new LogEntry());
        v2Log.getLogEntry(0).setDateLogged(new Date());
        v2Log.getLogEntry(0).setEntryId("id1");
        v2Log.getLogEntry(0).setEvent(Event.DELETE.xmlValue());
        v2Log.getLogEntry(0).setIdentifier(TypeFactory.buildIdentifier("foo"));
        v2Log.getLogEntry(0).setIpAddress("1.1.1.1");
        v2Log.getLogEntry(0).setNodeIdentifier(TypeFactory.buildNodeReference("place"));
        v2Log.getLogEntry(0).setSubject(TypeFactory.buildSubject("me"));
        v2Log.getLogEntry(0).setUserAgent("firefox-v234");
        
        LogEntry le2 = TypeFactory.clone(v2Log.getLogEntry(0));
        le2.setEntryId("id2");
        le2.setEvent(Event.READ.xmlValue());
        v2Log.addLogEntry(le2);
        try {
            org.dataone.service.types.v1.Log v1Log = 
                    TypeFactory.convertTypeFromType(v2Log, org.dataone.service.types.v1.Log.class);
            assertEquals("Should still have 2 LogEntry", 2, v1Log.sizeLogEntryList());
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Exception in converting log");
        }
        
    }
        
    
    @Test
    public void convertV2LogToV1Log_incompatibleEvent() throws InstantiationException, IllegalAccessException, JiBXException, IOException {
        Log v2Log = new Log();
        v2Log.addLogEntry(new LogEntry());
        v2Log.getLogEntry(0).setDateLogged(new Date());
        v2Log.getLogEntry(0).setEntryId("id1");
        v2Log.getLogEntry(0).setEvent("not_a_V1_Event");
        v2Log.getLogEntry(0).setIdentifier(TypeFactory.buildIdentifier("foo"));
        v2Log.getLogEntry(0).setIpAddress("1.1.1.1");
        v2Log.getLogEntry(0).setNodeIdentifier(TypeFactory.buildNodeReference("place"));
        v2Log.getLogEntry(0).setSubject(TypeFactory.buildSubject("me"));
        v2Log.getLogEntry(0).setUserAgent("firefox-v234");
        
        LogEntry le2 = TypeFactory.clone(v2Log.getLogEntry(0));
        le2.setEntryId("id2");
        le2.setEvent(Event.READ.toString());
        v2Log.addLogEntry(le2);
        v2Log.setTotal(2);
        
        try {
            org.dataone.service.types.v1.Log v1Log = 
                    TypeFactory.convertTypeFromType(v2Log, org.dataone.service.types.v1.Log.class);
            
            assertTrue("V1 log should have 1 LogEntry (incompatible one removed)", v1Log.sizeLogEntryList() == 1);
            assertTrue("the only log should be of event type READ", v1Log.getLogEntry(0).getEvent() == Event.READ);
            assertTrue("the Total should not have been reduced", v1Log.getTotal() == 2);
            assertTrue("the count should have been reduced", v1Log.getCount() == 1);
        }
        catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Exception in converting log");
        }
    }
    
    @Test
    public void convertV2LogToV1Log_emptyLog() throws InstantiationException, IllegalAccessException, JiBXException, IOException {
        Log v2Log = new Log();
        try {
            org.dataone.service.types.v1.Log v1Log = 
                    TypeFactory.convertTypeFromType(v2Log, org.dataone.service.types.v1.Log.class);
            
            assertTrue("V1 log should have 0 LogEntries ", v1Log.sizeLogEntryList() == 0);
        }
        catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Exception in converting log");
        }
    }
    
    @Test
    public void convertV2LogToV1LogEntry_incompatibleEvent() throws InstantiationException, IllegalAccessException, JiBXException, IOException {
        LogEntry v2LogEntry = new LogEntry();
        v2LogEntry.setDateLogged(new Date());
        v2LogEntry.setEntryId("id1");
        v2LogEntry.setEvent("not_a_V1_Event");
        v2LogEntry.setIdentifier(TypeFactory.buildIdentifier("foo"));
        v2LogEntry.setIpAddress("1.1.1.1");
        v2LogEntry.setNodeIdentifier(TypeFactory.buildNodeReference("place"));
        v2LogEntry.setSubject(TypeFactory.buildSubject("me"));
        v2LogEntry.setUserAgent("firefox-v234");
        
        try {
            org.dataone.service.types.v1.LogEntry v1LogEntry = 
                    TypeFactory.convertTypeFromType(v2LogEntry, org.dataone.service.types.v1.LogEntry.class);
            
            fail("Should not have been able to generate a v1.LogEntry using an incompatible event");
        }
        catch (InstantiationException e) {
            ; // desired outcome
        }
        catch (IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Unexpected Exception in converting log");
        }
    }
    
    
    @Test
    public void convertV1ToV2Log() throws InstantiationException, IllegalAccessException, JiBXException, IOException {
        org.dataone.service.types.v1.Log v1Log = new org.dataone.service.types.v1.Log();
        v1Log.addLogEntry(new org.dataone.service.types.v1.LogEntry());
        v1Log.getLogEntry(0).setDateLogged(new Date());
        v1Log.getLogEntry(0).setEntryId("id1");
        v1Log.getLogEntry(0).setEvent(Event.DELETE);
        v1Log.getLogEntry(0).setIdentifier(TypeFactory.buildIdentifier("foo"));
        v1Log.getLogEntry(0).setIpAddress("1.1.1.1");
        v1Log.getLogEntry(0).setNodeIdentifier(TypeFactory.buildNodeReference("place"));
        v1Log.getLogEntry(0).setSubject(TypeFactory.buildSubject("me"));
        v1Log.getLogEntry(0).setUserAgent("firefox-v234");
        
        org.dataone.service.types.v1.LogEntry le2 = TypeFactory.clone(v1Log.getLogEntry(0));
        le2.setEntryId("id2");
        le2.setEvent(Event.READ);
        v1Log.addLogEntry(le2);
        try {
            Log v2Log = TypeFactory.convertTypeFromType(v1Log, Log.class);
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Exception in converting log");
        }
    }
        
    
    @Test
    public void convertV2ObjectFormatListToV1ObjectFormatList() throws InstantiationException, IllegalAccessException, JiBXException, IOException {
        ObjectFormatList v2FormatList = new ObjectFormatList();
        v2FormatList.addObjectFormat(new ObjectFormat());
        v2FormatList.getObjectFormat(0).setExtension("csv");
        v2FormatList.getObjectFormat(0).setFormatId(TypeFactory.buildFormatIdentifier("text/csv"));
        v2FormatList.getObjectFormat(0).setFormatName("CSV");
        v2FormatList.getObjectFormat(0).setFormatType("DATA");
        v2FormatList.getObjectFormat(0).setMediaType(new MediaType());
        v2FormatList.getObjectFormat(0).getMediaType().setName("foo");
        v2FormatList.getObjectFormat(0).getMediaType().setPropertyList(new LinkedList<MediaTypeProperty>());
        v2FormatList.getObjectFormat(0).getMediaType().addProperty(new MediaTypeProperty());
        v2FormatList.getObjectFormat(0).getMediaType().getProperty(0).setName("aKey");
        v2FormatList.getObjectFormat(0).getMediaType().getProperty(0).setValue("aValue");
        
        ObjectFormat format2 = TypeFactory.clone(v2FormatList.getObjectFormat(0));
        format2.setExtension("ksv");
        format2.setFormatId(TypeFactory.buildFormatIdentifier("text/ksv"));
        format2.setFormatName("KSV");
        
        v2FormatList.addObjectFormat(format2);
        
        try {
            org.dataone.service.types.v1.ObjectFormatList v1FList = 
                    TypeFactory.convertTypeFromType(v2FormatList, org.dataone.service.types.v1.ObjectFormatList.class);
            
            assertTrue("V1 ObjectFormatList should have 2 formats", v1FList.sizeObjectFormatList() == 2);

        }
        catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Exception in converting FormatList");
        }
    }
        
}
