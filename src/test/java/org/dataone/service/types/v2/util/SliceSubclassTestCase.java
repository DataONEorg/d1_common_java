package org.dataone.service.types.v2.util;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.dataone.service.types.v1.Log;
import org.dataone.service.types.v1.LogEntry;
import org.dataone.service.types.v1.ObjectFormat;
import org.dataone.service.types.v1.ObjectFormatList;
import org.dataone.service.types.v1.ObjectInfo;
import org.dataone.service.types.v1.ObjectList;
import org.dataone.service.types.v1.Slice;
import org.junit.Test;

/**
 * Tests some subclasses of {@link Slice} for state consistency after using Slice as well as subclass methods to modify the state.
 */
public class SliceSubclassTestCase {

    @Test
    public void testLogV1Add() {
        
        Log l = new Log();
        l.setStart(0);
        l.setCount(0);
        l.setTotal(0);
        
        l.addLogEntry(new LogEntry());
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Adding 1 element to ObjectList should not change the start value.", start == 0);
        assertTrue("Adding 1 element to ObjectList should increase the count by 1.", count == 1);
        assertTrue("Adding 1 element to ObjectList should increase the total by 1.", total == 1);
    }
    
    @Test
    public void testLogV1Clear() {
        
        Log l = new Log();
        l.setStart(1);
        l.setCount(3);
        l.setTotal(5);
        
        List<LogEntry> logEntryList = new ArrayList<LogEntry>();
        for(int i = 1; i <= 3; i++)
            logEntryList.add(new LogEntry());
        l.setLogEntryList(logEntryList);
        
        l.clearLogEntryList();
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        
        assertTrue("Clearing the ObjectList should not change the start value.", start == 1);
        assertTrue("Clearing the ObjectList should make the count zero.", count == 0);
        assertTrue("Clearing the ObjectList should decrease the total by the previous count.", total == 2);
    }
    
    @Test
    public void testLogV1Set() {
        
        Log l = new Log();
        l.setStart(1);
        l.setCount(0);
        l.setTotal(3);
        
        List<LogEntry> logEntryList = new ArrayList<LogEntry>();
        for(int i = 1; i <= 2; i++)
            logEntryList.add(new LogEntry());
        l.setLogEntryList(logEntryList);
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Setting a new list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectList should change the count by the difference with the old count.", count == 2);
        assertTrue("Setting a new list in a ObjectList should change the total by the difference with the old count.", total == 5);
        
        logEntryList.add(new LogEntry());
        logEntryList.add(new LogEntry());
        logEntryList.add(new LogEntry());
        l.setLogEntryList(logEntryList);
        
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting a new list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectList should change the count by the difference with the old count.", count == 5);
        assertTrue("Setting a new list in a ObjectList should change the total by the difference with the old count.", total == 8);
        
        l.setLogEntryList(new ArrayList<LogEntry>());
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting an empty list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting an empty list in a ObjectList should change the count to zero.", count == 0);
        assertTrue("Setting an empty list in a ObjectList should change the total by the difference with the old count.", total == 3);
        
        try {
            l.setLogEntryList(null);
        } catch (Exception e) {
            assertTrue("Setting a new list to null in a ObjectList should not yield an exception!", false);
        }
        assertTrue("Setting a new list to null in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list to null in a ObjectList should change the count to zero.", count == 0);
        assertTrue("Setting a new list to null in a ObjectList should change the total by subtracting the old count.", total == 3);
    }
    
    @Test
    public void testLogV2Add() {
        
        org.dataone.service.types.v2.Log l = new org.dataone.service.types.v2.Log();
        l.setStart(0);
        l.setCount(0);
        l.setTotal(0);
        
        l.addLogEntry(new org.dataone.service.types.v2.LogEntry());
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Adding 1 element to ObjectList should not change the start value.", start == 0);
        assertTrue("Adding 1 element to ObjectList should increase the count by 1.", count == 1);
        assertTrue("Adding 1 element to ObjectList should increase the total by 1.", total == 1);
    }
    
    @Test
    public void testLogV2Clear() {
        
        org.dataone.service.types.v2.Log l = new org.dataone.service.types.v2.Log();
        l.setStart(1);
        l.setCount(3);
        l.setTotal(5);
        
        List<org.dataone.service.types.v2.LogEntry> logEntryList = new ArrayList<org.dataone.service.types.v2.LogEntry>();
        for(int i = 1; i <= 3; i++)
            logEntryList.add(new org.dataone.service.types.v2.LogEntry());
        l.setLogEntryList(logEntryList);
        
        l.clearLogEntryList();
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        
        assertTrue("Clearing the ObjectList should not change the start value.", start == 1);
        assertTrue("Clearing the ObjectList should make the count zero.", count == 0);
        assertTrue("Clearing the ObjectList should decrease the total by the previous count.", total == 2);
    }
    
    @Test
    public void testLogV2Set() {
        
        org.dataone.service.types.v2.Log l = new org.dataone.service.types.v2.Log();
        l.setStart(1);
        l.setCount(0);
        l.setTotal(3);
        
        List<org.dataone.service.types.v2.LogEntry> logEntryList = new ArrayList<org.dataone.service.types.v2.LogEntry>();
        for(int i = 1; i <= 2; i++)
            logEntryList.add(new org.dataone.service.types.v2.LogEntry());
        l.setLogEntryList(logEntryList);
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Setting a new list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectList should change the count by the difference with the old count.", count == 2);
        assertTrue("Setting a new list in a ObjectList should change the total by the difference with the old count.", total == 5);
        
        logEntryList.add(new org.dataone.service.types.v2.LogEntry());
        logEntryList.add(new org.dataone.service.types.v2.LogEntry());
        logEntryList.add(new org.dataone.service.types.v2.LogEntry());
        l.setLogEntryList(logEntryList);
        
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting a new list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectList should change the count by the difference with the old count.", count == 5);
        assertTrue("Setting a new list in a ObjectList should change the total by the difference with the old count.", total == 8);
        
        l.setLogEntryList(new ArrayList<org.dataone.service.types.v2.LogEntry>());
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting an empty list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting an empty list in a ObjectList should change the count to zero.", count == 0);
        assertTrue("Setting an empty list in a ObjectList should change the total by the difference with the old count.", total == 3);
        
        try {
            l.setLogEntryList(null);
        } catch (Exception e) {
            assertTrue("Setting a new list to null in a ObjectList should not yield an exception!", false);
        }
        assertTrue("Setting a new list to null in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list to null in a ObjectList should change the count to zero.", count == 0);
        assertTrue("Setting a new list to null in a ObjectList should change the total by subtracting the old count.", total == 3);
    }
    
    @Test
    public void testObjectFormatListV1Add() {
        
        ObjectFormatList l = new ObjectFormatList();
        l.setStart(0);
        l.setCount(0);
        l.setTotal(0);
        
        l.addObjectFormat(new ObjectFormat());
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Adding 1 element to ObjectFormatList should not change the start value.", start == 0);
        assertTrue("Adding 1 element to ObjectFormatList should increase the count by 1.", count == 1);
        assertTrue("Adding 1 element to ObjectFormatList should increase the total by 1.", total == 1);
    }
    
    @Test
    public void testObjectFormatListV1Clear() {
        
        ObjectFormatList l = new ObjectFormatList();
        l.setStart(1);
        l.setCount(3);
        l.setTotal(5);
        
        List<ObjectFormat> objectFormatList = new ArrayList<ObjectFormat>();
        for(int i = 1; i <= 3; i++)
            objectFormatList.add(new ObjectFormat());
        l.setObjectFormatList(objectFormatList);
        
        l.clearObjectFormatList();
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        
        assertTrue("Clearing the ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Clearing the ObjectFormatList should make the count zero.", count == 0);
        assertTrue("Clearing the ObjectFormatList should decrease the total by the previous count.", total == 2);
    }
    
    @Test
    public void testObjectFormatListV1Set() {
        
        ObjectFormatList l = new ObjectFormatList();
        l.setStart(1);
        l.setCount(0);
        l.setTotal(3);
        
        List<ObjectFormat> objectFormatList = new ArrayList<ObjectFormat>();
        for(int i = 1; i <= 2; i++)
            objectFormatList.add(new ObjectFormat());
        l.setObjectFormatList(objectFormatList);
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Setting a new list in a ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectFormatList should change the count by the difference with the old count.", count == 2);
        assertTrue("Setting a new list in a ObjectFormatList should change the total by the difference with the old count.", total == 5);
        
        objectFormatList.add(new ObjectFormat());
        objectFormatList.add(new ObjectFormat());
        objectFormatList.add(new ObjectFormat());
        l.setObjectFormatList(objectFormatList);
        
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting a new list in a ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectFormatList should change the count by the difference with the old count.", count == 5);
        assertTrue("Setting a new list in a ObjectFormatList should change the total by the difference with the old count.", total == 8);
        
        l.setObjectFormatList(new ArrayList<ObjectFormat>());
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting an empty list in a ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Setting an empty list in a ObjectFormatList should change the count to zero.", count == 0);
        assertTrue("Setting an empty list in a ObjectFormatList should change the total by the difference with the old count.", total == 3);
        
        try {
            l.setObjectFormatList(null);
        } catch (Exception e) {
            assertTrue("Setting a new list to null in a ObjectFormatList should not yield an exception!", false);
        }
        assertTrue("Setting a new list to null in a ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Setting a new list to null in a ObjectFormatList should change the count to zero.", count == 0);
        assertTrue("Setting a new list to null in a ObjectFormatList should change the total by subtracting the old count.", total == 3);
    }
    

    @Test
    public void testObjectFormatListV2Add() {
        
        org.dataone.service.types.v2.ObjectFormatList l = new org.dataone.service.types.v2.ObjectFormatList();
        l.setStart(0);
        l.setCount(0);
        l.setTotal(0);
        
        l.addObjectFormat(new org.dataone.service.types.v2.ObjectFormat());
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Adding 1 element to ObjectFormatList should not change the start value.", start == 0);
        assertTrue("Adding 1 element to ObjectFormatList should increase the count by 1.", count == 1);
        assertTrue("Adding 1 element to ObjectFormatList should increase the total by 1.", total == 1);
    }
    
    @Test
    public void testObjectFormatListV2Clear() {
        
        org.dataone.service.types.v2.ObjectFormatList l = new org.dataone.service.types.v2.ObjectFormatList();
        l.setStart(1);
        l.setCount(3);
        l.setTotal(5);
        
        List<org.dataone.service.types.v2.ObjectFormat> objectFormatList = new ArrayList<org.dataone.service.types.v2.ObjectFormat>();
        for(int i = 1; i <= 3; i++)
            objectFormatList.add(new org.dataone.service.types.v2.ObjectFormat());
        l.setObjectFormatList(objectFormatList);
        
        l.clearObjectFormatList();
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        
        assertTrue("Clearing the ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Clearing the ObjectFormatList should make the count zero.", count == 0);
        assertTrue("Clearing the ObjectFormatList should decrease the total by the previous count.", total == 2);
    }
    
    @Test
    public void testObjectFormatListV2Set() {
        
        org.dataone.service.types.v2.ObjectFormatList l = new org.dataone.service.types.v2.ObjectFormatList();
        l.setStart(1);
        l.setCount(0);
        l.setTotal(3);
        
        List<org.dataone.service.types.v2.ObjectFormat> objectFormatList = new ArrayList<org.dataone.service.types.v2.ObjectFormat>();
        for(int i = 1; i <= 2; i++)
            objectFormatList.add(new org.dataone.service.types.v2.ObjectFormat());
        l.setObjectFormatList(objectFormatList);
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Setting a new list in a ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectFormatList should change the count by the difference with the old count.", count == 2);
        assertTrue("Setting a new list in a ObjectFormatList should change the total by the difference with the old count.", total == 5);
        
        objectFormatList.add(new org.dataone.service.types.v2.ObjectFormat());
        objectFormatList.add(new org.dataone.service.types.v2.ObjectFormat());
        objectFormatList.add(new org.dataone.service.types.v2.ObjectFormat());
        l.setObjectFormatList(objectFormatList);
        
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting a new list in a ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectFormatList should change the count by the difference with the old count.", count == 5);
        assertTrue("Setting a new list in a ObjectFormatList should change the total by the difference with the old count.", total == 8);
        
        l.setObjectFormatList(new ArrayList<org.dataone.service.types.v2.ObjectFormat>());
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting an empty list in a ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Setting an empty list in a ObjectFormatList should change the count to zero.", count == 0);
        assertTrue("Setting an empty list in a ObjectFormatList should change the total by the difference with the old count.", total == 3);
        
        try {
            l.setObjectFormatList(null);
        } catch (Exception e) {
            assertTrue("Setting a new list to null in a ObjectFormatList should not yield an exception!", false);
        }
        assertTrue("Setting a new list to null in a ObjectFormatList should not change the start value.", start == 1);
        assertTrue("Setting a new list to null in a ObjectFormatList should change the count to zero.", count == 0);
        assertTrue("Setting a new list to null in a ObjectFormatList should change the total by subtracting the old count.", total == 3);
    }
    
    @Test
    public void testObjectListV1Add() {
        
        ObjectList l = new ObjectList();
        l.setStart(0);
        l.setCount(0);
        l.setTotal(0);
        
        l.addObjectInfo(new ObjectInfo());
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Adding 1 element to ObjectList should not change the start value.", start == 0);
        assertTrue("Adding 1 element to ObjectList should increase the count by 1.", count == 1);
        assertTrue("Adding 1 element to ObjectList should increase the total by 1.", total == 1);
    }
    
    @Test
    public void testObjectListV1Clear() {
        
        ObjectList l = new ObjectList();
        l.setStart(1);
        l.setCount(3);
        l.setTotal(5);
        
        List<ObjectInfo> objInfoList = new ArrayList<ObjectInfo>();
        for(int i = 1; i <= 3; i++)
            objInfoList.add(new ObjectInfo());
        l.setObjectInfoList(objInfoList);
        
        l.clearObjectInfoList();
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        
        assertTrue("Clearing the ObjectList should not change the start value.", start == 1);
        assertTrue("Clearing the ObjectList should make the count zero.", count == 0);
        assertTrue("Clearing the ObjectList should decrease the total by the previous count.", total == 2);
    }
    
    @Test
    public void testObjectListV1Set() {
        
        ObjectList l = new ObjectList();
        l.setStart(1);
        l.setCount(0);
        l.setTotal(3);
        
        List<ObjectInfo> objInfoList = new ArrayList<ObjectInfo>();
        for(int i = 1; i <= 2; i++)
            objInfoList.add(new ObjectInfo());
        l.setObjectInfoList(objInfoList);
        
        int start = l.getStart();
        int count = l.getCount();
        int total = l.getTotal();
        assertTrue("Setting a new list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectList should change the count by the difference with the old count.", count == 2);
        assertTrue("Setting a new list in a ObjectList should change the total by the difference with the old count.", total == 5);
        
        objInfoList.add(new ObjectInfo());
        objInfoList.add(new ObjectInfo());
        objInfoList.add(new ObjectInfo());
        l.setObjectInfoList(objInfoList);
        
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting a new list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list in a ObjectList should change the count by the difference with the old count.", count == 5);
        assertTrue("Setting a new list in a ObjectList should change the total by the difference with the old count.", total == 8);
        
        l.setObjectInfoList(new ArrayList<ObjectInfo>());
        start = l.getStart();
        count = l.getCount();
        total = l.getTotal();
        assertTrue("Setting an empty list in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting an empty list in a ObjectList should change the count to zero.", count == 0);
        assertTrue("Setting an empty list in a ObjectList should change the total by the difference with the old count.", total == 3);
        
        try {
            l.setObjectInfoList(null);
        } catch (Exception e) {
            assertTrue("Setting a new list to null in a ObjectList should not yield an exception!", false);
        }
        assertTrue("Setting a new list to null in a ObjectList should not change the start value.", start == 1);
        assertTrue("Setting a new list to null in a ObjectList should change the count to zero.", count == 0);
        assertTrue("Setting a new list to null in a ObjectList should change the total by subtracting the old count.", total == 3);
    }
    
}
