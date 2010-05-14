/**
 * Copyright 2010 Regents of the University of California and the
 *                National Center for Ecological Analysis and Synthesis
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.dataone.service.exceptions;

import java.util.TreeMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Base exceptions
 */
public class BaseExceptionTest extends TestCase
{
    private String msg = "The specified object does not exist on this node.";
    private TreeMap<String, String> trace = new TreeMap<String, String>();
    private BaseException e = null;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BaseExceptionTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BaseExceptionTest.class );
    }
    
    /** Common setup code for all of the tests. */
    public void setUp() {
        trace.put("identifier", "123XYZ");
        trace.put("method", "mn.get");
        e = new BaseException(404, "14001", msg, trace);
        assertNotNull(e);    
    }
    
    /**
     * Test creation of an exception, and serialization of the fields into XML.
     */
    public void testSerializeXML()
    {   
        String xml = e.serialize(BaseException.FMT_XML);
        System.out.println(xml);
        
        assertNotNull(xml);
        assertTrue(xml.indexOf("<error") != -1);
        assertTrue(xml.indexOf("'404'") != -1);
        assertTrue(xml.indexOf("'14001'") != -1);
        assertTrue(xml.indexOf(msg) != -1);
        assertTrue(xml.indexOf("identifier") != -1);
        assertTrue(xml.indexOf("123XYZ") != -1);
        assertTrue(xml.indexOf("method") != -1);
        assertTrue(xml.indexOf("mn.get") != -1);
    }
    
    /**
     * Test creation of an exception, and serialization of the fields into JSON.
     */
    public void testSerializeJSON()
    {   
        String json = e.serialize(BaseException.FMT_JSON);
        System.out.println(json);
        
        assertNotNull(json);
        assertTrue(json.indexOf("'errorCode': 404") != -1);
        assertTrue(json.indexOf("'detailCode': 14001") != -1);
        assertTrue(json.indexOf(msg) != -1);
        assertTrue(json.indexOf("'identifier': '123XYZ'") != -1);
        assertTrue(json.indexOf("'method': 'mn.get'") != -1);
    }
    
    /**
     * Test creation of an exception, and serialization of the fields into HTML.
     */
    public void testSerializeHTML()
    {   
        String html = e.serialize(BaseException.FMT_HTML);
        System.out.println(html);
        
        assertNotNull(html);
        assertTrue(html.indexOf("<html>") != -1);
        assertTrue(html.indexOf("404") != -1);
        assertTrue(html.indexOf("14001") != -1);
        assertTrue(html.indexOf(msg) != -1);
        assertTrue(html.indexOf("identifier") != -1);
        assertTrue(html.indexOf("123XYZ") != -1);
        assertTrue(html.indexOf("method") != -1);
        assertTrue(html.indexOf("mn.get") != -1);
    }
}
