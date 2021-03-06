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

package org.dataone.service.exceptions;

import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Base exceptions
 */
public class BaseExceptionTest extends TestCase
{
	private static Log log = LogFactory.getLog(BaseExceptionTest.class);
	private static String idString = "doi:10.6067:XCV843017_meta&v=1482951215073";
	private static String xmlEncodedIdString = "doi:10.6067:XCV843017_meta&amp;v=1482951215073";
    private static String msg = String.format("The specified object (%s) does not exist on this node.",idString);
    private static String xmlMsg = String.format("The specified object (%s) does not exist on this node.", xmlEncodedIdString);
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
        trace.put("nodeId", "3cp0");
        trace.put("method", "mn.get");
        e = new BaseException(404, "14001", idString, "c3p0", msg, trace);
        assertNotNull(e);    
    }
    
    /**
     * Test creation of an exception, and serialization of the fields into XML.
     */
    public void testSerializeXML()
    {   
        String xml = e.serialize(BaseException.FMT_XML);
        log.info(xml);
        String xml2 = e.serialize(BaseException.FMT_XML);
        log.info(xml2);
        String xml3 = e.serialize(BaseException.FMT_XML);
        log.info(xml3);
        
        assertNotNull(xml);
        assertTrue(xml.indexOf("<error") != -1);
        assertTrue(xml.indexOf("\"404\"") != -1);
        assertTrue(xml.indexOf("\"14001\"") != -1);
        System.out.println(xmlMsg);
        assertTrue(xml.indexOf(xmlMsg) != -1);
        assertTrue(xml.indexOf("identifier") != -1);
        assertTrue(xml.indexOf(String.format("identifier=\"%s\"",xmlEncodedIdString)) != -1);
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
        log.info(json);
        
        assertNotNull(json);
        assertTrue(json.indexOf("'errorCode': 404") != -1);
        assertTrue(json.indexOf("'detailCode': 14001") != -1);
        assertTrue(json.indexOf(msg) != -1);
        assertTrue(json.indexOf("'identifier': '123XYZ'") != -1);
        assertTrue(json.indexOf(String.format("'identifier': '%s'",idString))!= -1);
        assertTrue(json.indexOf("'method': 'mn.get'") != -1);
    }
    
    /**
     * Test creation of an exception, and serialization of the fields into HTML.
     */
    public void testSerializeHTML()
    {   
        String html = e.serialize(BaseException.FMT_HTML);
        log.info(html);
        
        assertNotNull(html);
        assertTrue(html.indexOf("<html>") != -1);
        assertTrue(html.indexOf("404") != -1);
        assertTrue(html.indexOf("14001") != -1);
        assertTrue(html.indexOf(msg) != -1);
        assertTrue(html.indexOf("identifier") != -1);
        assertTrue(html.indexOf("identifier") != -1);
        assertTrue(html.indexOf("123XYZ") != -1);
        assertTrue(html.indexOf("method") != -1);
        assertTrue(html.indexOf("mn.get") != -1);
    }
    
    
    /**
     * Test creation of an exception, and serialization of the fields into XML.
     */
    public void testSerializeNameFieldXML()
    {   
        NotFound e = new NotFound( "14001", "some description");
        assertNotNull(e);    
        String xml = e.serialize(BaseException.FMT_XML);
        log.info(xml);
        
        assertNotNull(xml);
        assertTrue(xml.indexOf("<error") != -1);
        assertTrue(xml.indexOf("\"404\"") != -1);
        assertTrue(xml.indexOf("name") != -1);
        assertTrue(xml.indexOf("NotFound") != -1);
    }
    
}
