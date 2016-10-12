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
 * 
 * $Id$
 */

package org.dataone.service.util;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.dataone.configuration.Settings;
import org.dataone.exceptions.MarshallingException;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.log4j.Logger;
import org.dataone.service.types.v1.Checksum;
import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.Node;
import org.dataone.service.types.v1.NodeReference;
import org.dataone.service.types.v1.ObjectFormatIdentifier;
import org.dataone.service.types.v1.ObjectList;
import org.dataone.service.types.v1.Subject;
import org.dataone.service.types.v1.SystemMetadata;
import org.junit.Test;
import org.xml.sax.SAXException;


/**
 *
 * @author waltz
 */
public class TypeMarshallerTestCase {
	
	private static Logger log = Logger.getLogger(TypeMarshallerTestCase.class);

    @Test
    public void deserializeSystemMetadata() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v1/systemMetadataSample1.xml");
            TypeMarshaller.unmarshalTypeFromStream(SystemMetadata.class, is);
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InstantiationException ex) {
            fail("Test misconfiguration" + ex);
        } catch (IllegalAccessException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (MarshallingException ex) {
            fail("Test misconfiguration" +  ex);
        }
    }
    


    
    @Test
    public void deserializeSerializeSysMeta_performanceTest() {
        for (int i = 1; i <=2; i++) {
            Date start = null;
            Date mid = null;
            Date end = null;
            try {
                InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v1/systemMetadataSample1.xml");
                start = new Date();
                SystemMetadata symeta = TypeMarshaller.unmarshalTypeFromStream(SystemMetadata.class, is);
                mid = new Date();
                OutputStream os = new NullOutputStream();
                TypeMarshaller.marshalTypeToOutputStream(symeta, os);
                end = new Date();
            } catch (IOException ex) {
                fail("Test misconfiguration" +  ex);
            } catch (InstantiationException ex) {
                fail("Test misconfiguration" + ex);
            } catch (IllegalAccessException ex) {
                fail("Test misconfiguration" +  ex);
            } catch (MarshallingException ex) {
                fail("Test misconfiguration" +  ex);
            } finally {
                if (mid == null) 
                    mid = new Date();
                if (end == null)
                    end = new Date();
                System.out.printf("Unmarshalling: elapsed time (ms) %d\n", mid.getTime() - start.getTime());
                System.out.printf("Marshalling: elapsed time (ms) %d\n", end.getTime() - mid.getTime());
                System.out.printf("Total: elapsed time (ms) %d\n", end.getTime() - start.getTime());
            }
        }
    }
    
    @Test
    public void deserializeSerializeObjectList_performanceTest() {
        List<Long> uTimes = new ArrayList<>();
        List<Long> mTimes = new ArrayList<>();
        for (int i = 1; i <=50; i++) {
            Date start = null;
            Date mid = null;
            Date end = null;
            
            try {
                InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v2/objectList7000.xml");
                start = new Date();
                ObjectList ol = TypeMarshaller.unmarshalTypeFromStream(ObjectList.class, is);
                mid = new Date();
                OutputStream os = new NullOutputStream();
                TypeMarshaller.marshalTypeToOutputStream(ol, os);
                end = new Date();
            } catch (IOException ex) {
                fail("Test misconfiguration" +  ex);
            } catch (InstantiationException ex) {
                fail("Test misconfiguration" + ex);
            } catch (IllegalAccessException ex) {
                fail("Test misconfiguration" +  ex);
            } catch (MarshallingException ex) {
                fail("Test misconfiguration" +  ex);
            } finally {
                if (mid == null) 
                    mid = new Date();
                if (end == null)
                    end = new Date();
            }
            long uTime = mid.getTime() - start.getTime();
            long mTime = end.getTime() - mid.getTime();
//            System.out.printf("%d\t%d\t%d\n", i, uTime, mTime);
            uTimes.add(uTime);
            mTimes.add(mTime);
        }
        System.out.println("===================================");
        System.out.println("index\tunmarsh\tmarsh");
        System.out.println("===================================");
        System.out.printf("count\t%d\t%d\n", uTimes.size(),mTimes.size());
        System.out.printf("sum\t%d\t%d\n", sumOfLongs(uTimes), sumOfLongs(mTimes));
        System.out.printf("mean\t%d\t%d\n", sumOfLongs(uTimes)/uTimes.size(), sumOfLongs(mTimes)/mTimes.size());
        System.out.printf("median\t%d\t%d\n", medianOfLongs(uTimes), medianOfLongs(mTimes));

    }

    private long sumOfLongs(List<Long> elements) {
        long sum = 0;
        for (Long l : elements) {
            sum += l;
        }
        return sum;
    }
    
    private long medianOfLongs(List<Long> elements) {
        int count = elements.size();
        if (count == 1)
            return elements.get(0);
        
        long median = 0;
        int halfcount = count / 2;
        Long[] longs = elements.toArray(new Long[0]);
        Arrays.sort(longs);

        if (count % 2 == 0) {
            return (longs[halfcount] + longs[halfcount+1]) / 2;
        } else {
            return longs[halfcount];
        }
    }
    
    
    
    @Test
    public void deserializeNode() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v1/mnNode1.xml");
            TypeMarshaller.unmarshalTypeFromStream(Node.class, is);
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InstantiationException ex) {
            fail("Test misconfiguration" + ex);
        } catch (IllegalAccessException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (MarshallingException ex) {
            fail("Test misconfiguration" +  ex);
        }
    }

    
    @Test
    public void serializeEmptyObjectList() {
        ObjectList objectList = new ObjectList();
        assertNotNull(objectList);
        assertNotNull(objectList.getObjectInfoList());
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            TypeMarshaller.marshalTypeToOutputStream(objectList, os);
            String xmlObjectList = os.toString();
            assertNotNull(xmlObjectList);
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (MarshallingException ex) {
            fail("Test misconfiguration" +  ex);
        }
    }

    @Test
    public void deserializeEmptyObjectListSize() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v1/objectListSample2.xml");
            ObjectList objectList = TypeMarshaller.unmarshalTypeFromStream(ObjectList.class, is);
            assertNotNull(objectList);
            assertNotNull(objectList.sizeObjectInfoList());
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InstantiationException ex) {
            fail("Test misconfiguration" + ex);
        } catch (IllegalAccessException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (MarshallingException ex) {
            fail("Test misconfiguration" +  ex);
        }
    }
    
    @Test
    public void serializeNodeStylesheet() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v1/mnNode1.xml");
            Node node = TypeMarshaller.unmarshalTypeFromStream(Node.class, is);
            String styleSheet = "test.xsl";
            ByteArrayOutputStream os = new ByteArrayOutputStream();
			TypeMarshaller.marshalTypeToOutputStream(node, os , styleSheet);
			String result = os.toString("UTF-8");
			log.debug("Stylesheet result: \n" + result);
			assertTrue(result.contains(styleSheet));
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (InstantiationException ex) {
            fail("Test misconfiguration" + ex);
        } catch (IllegalAccessException ex) {
            fail("Test misconfiguration" +  ex);
        } catch (MarshallingException ex) {
            fail("Test misconfiguration" +  ex);
        }


    }
    
    
    @Test
    public void serializeSystemMetadata() {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            org.dataone.service.types.v2.SystemMetadata s = new org.dataone.service.types.v2.SystemMetadata();
            s.setIdentifier(new Identifier());
            s.getIdentifier().setValue("foooo2");
            s.setAuthoritativeMemberNode(new NodeReference());
            s.getAuthoritativeMemberNode().setValue("urn:node:authMN");
            s.setOriginMemberNode(new NodeReference());
            s.getOriginMemberNode().setValue("urn:node:authMN");
            s.setChecksum(new Checksum());
            s.getChecksum().setAlgorithm("MD5");
            s.getChecksum().setValue("2ofo349vdwlw98voiwopwkvne");
            Date now = new Date();
            s.setDateSysMetadataModified(now);
            s.setDateUploaded(now);
            s.setFormatId(new ObjectFormatIdentifier());
            s.getFormatId().setValue("eml://ecoinformatics.org/eml-2.1.0");
            s.setRightsHolder(new Subject());
            s.getRightsHolder().setValue("groucho");
            s.setSerialVersion(BigInteger.ONE);
            s.setSize(new BigInteger("9"));
//            s.setSubmitter(new Subject());
//            s.getSubmitter().setValue("harpo");

            String styleSheet = "test.xsl";
//            TypeMarshaller.marshalTypeToOutputStream(s, os,styleSheet);
            TypeMarshaller.marshalTypeToOutputStream(s, os);

            String result = os.toString("UTF-8");
            log.debug("Stylesheet result: \n" + result);
            assertTrue(result.contains("foooo2"));
            os.close();
            ByteArrayOutputStream os2 = new ByteArrayOutputStream();
            TypeMarshaller.marshalTypeToOutputStream(s, os2);
            
        } catch (IOException ex) {
            fail("Test misconfiguration" +  ex);
//        } catch (InstantiationException ex) {
//            fail("Test misconfiguration" + ex);
//        } catch (IllegalAccessException ex) {
//            fail("Test misconfiguration" +  ex);
        } catch (MarshallingException ex) {
            fail("Problem with TypeMarshaller.  Cause: " +  ex.getCause().getClass().getCanonicalName()
                    + ex.getCause().getMessage());
        }
    }
    
    @Test
    public void testMarshallingShouldDoSchemaValidation() throws InstantiationException, IllegalAccessException, IOException, MarshallingException, SAXException {
        
        InputStream is = this.getClass().getResourceAsStream("systemMetadata-invalid_schema.xml");
        org.dataone.service.types.v1.SystemMetadata sysMeta = 
                TypeMarshaller.unmarshalTypeFromStream(org.dataone.service.types.v1.SystemMetadata.class, is);
        try {
            TypeMarshaller.marshalTypeToOutputStream(sysMeta, new ByteArrayOutputStream());
        } catch (MarshallingException e) {
            // should throw exception
        }
     }
    
    @Test
    public void testValidateSchema() throws InstantiationException, IllegalAccessException, IOException, MarshallingException {
        
        InputStream is = this.getClass().getResourceAsStream("systemMetadata-invalid_schema.xml");
        org.dataone.service.types.v1.SystemMetadata sysMeta = 
                TypeMarshaller.unmarshalTypeFromStream(org.dataone.service.types.v1.SystemMetadata.class, is);
        try {
            TypeMarshaller.validateAgainstSchema(sysMeta);
        } catch (MarshallingException e) {
            // should throw exception
        }
     }
}
