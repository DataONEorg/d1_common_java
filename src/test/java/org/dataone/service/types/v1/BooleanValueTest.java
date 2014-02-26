package org.dataone.service.types.v1;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dataone.service.util.TypeMarshaller;
import org.jibx.runtime.JiBXException;
import org.junit.Before;
import org.junit.Test;

public class BooleanValueTest {

	public static String xml;
	@Before
	public void setUp() throws Exception {
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
"<d1:systemMetadata xmlns:d1=\"http://ns.dataone.org/service/types/v1\">" +
"<serialVersion>1</serialVersion>" +
"    <identifier>08d2c688-19fd-4cd5-88ff-4ed76bf40332</identifier>" +
"    <formatId>FGDC-STD-001-1998</formatId>" +
"    <size>24018</size>" +
"    <checksum algorithm=\"md5\">c367489854f10a2edf2168e8394c2bf4</checksum>" +
"    <submitter>uid=EDACGSTORE,o=EDAC,dc=everything,dc=EDAC</submitter>" +
"    <rightsHolder>uid=EDACGSTORE,o=EDAC,dc=everything,dc=EDAC</rightsHolder>" +
"    <accessPolicy>" +
"        <allow><subject>public</subject><permission>read</permission></allow>" +
"    </accessPolicy>" +
"    <replicationPolicy replicationAllowed=\"False\"></replicationPolicy>" +
"        <obsoletedBy>71fd14aa-8c7e-4e89-a3f3-1df2a011db2a</obsoletedBy>" +
"        <archived>true</archived>" +    
"    <dateUploaded>2013-10-14T16:52:08.674723Z</dateUploaded>" +
"    <dateSysMetadataModified>2014-02-11T20:37:01.095402Z</dateSysMetadataModified>" +    
"    <originMemberNode>urn:node:EDACGSTORE</originMemberNode>" +
"    <authoritativeMemberNode>urn:node:EDACGSTORE</authoritativeMemberNode>" +
"</d1:systemMetadata>";
	}

	@Test
	public final void testBooleanInSysmeta_CapitalizedFalse() 
	throws IOException, InstantiationException, IllegalAccessException {
				
		InputStream is = IOUtils.toInputStream(xml);
		try {
			TypeMarshaller.unmarshalTypeFromStream(SystemMetadata.class, is);
			fail("Capitalized 'False' in boolean field throws exception");
		} catch (JiBXException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public final void testBoleanInSysmeta_LowercaseFalse() 
	throws IOException, InstantiationException, IllegalAccessException {
				
		String lcXml = StringUtils.replace(xml, "False", "false");

		InputStream is = IOUtils.toInputStream(lcXml);
		try {
			TypeMarshaller.unmarshalTypeFromStream(SystemMetadata.class, is);
			
		} catch (JiBXException e) {
			fail("lowercase 'false' in boolean field should not throw exception: " + e.getMessage());
		}
		
	}
	

}
