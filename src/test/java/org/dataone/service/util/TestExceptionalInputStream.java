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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.apache.commons.io.IOUtils;
import org.dataone.exceptions.MarshallingException;
import org.junit.Ignore;
import org.junit.Test;


public class TestExceptionalInputStream {
	
	
	@Test
	public void testNotException() throws IOException {
		String startingInput = "this is not a dataone error, but not a dataone type, so should be treated as exception";
		InputStream is = new ByteArrayInputStream(startingInput.getBytes("UTF-8"));
		ExceptionalInputStream eis = new ExceptionalInputStream(is);
		if (!eis.isException()) {
			fail("should not have gotten here");
		}
		String endingInput = IOUtils.toString(eis);
		assertEquals(startingInput, endingInput);
	}
	
	
	
	/**
	 * test the behavior of PushbackInputStreams
	 * multiple reads followed by multiple unreads.
	 * @throws IOException
	 */
	
	@Test
	public void testMultiplePushbacks() throws IOException {
		String startingInput84 = "this is not an error.  Not at all.  Just a very long, boring nonsense statement.";
		
		InputStream is = new ByteArrayInputStream(startingInput84.getBytes("UTF-8"));
		// should be 84 (divisible by 4)
		int strlength = startingInput84.length();
		PushbackInputStream pbis = new PushbackInputStream(is,strlength + 4);
		
		System.out.println(strlength);
		System.out.println(Math.round(strlength/4)+1);
		
		byte[] a = new byte[Math.round(strlength/4)+1];
		byte[] b = new byte[Math.round(strlength/4)+1];
		byte[] c = new byte[Math.round(strlength/4)+1];
		byte[] d = new byte[Math.round(strlength/4)+1];
		

		pbis.read(a);
		pbis.read(b);
		pbis.read(c);
		
		// example is designed to read beyond length of stream,
		int x = pbis.read(d);
		// so need to only unread the number of bytes read
		pbis.unread(d,0,x);
		
		pbis.unread(c);
		pbis.unread(b);
		pbis.unread(a);
		
		String endingInput = IOUtils.toString(pbis);
		assertEquals(startingInput84.length(), endingInput.length());
		assertEquals(startingInput84, endingInput);
	}

	/**
	 * test the behavior of PushbackInputStreams
	 * multiple read-unread paired calls.  
	 * @throws IOException
	 */
	@Test
	public void testMultiplePushPulls() throws IOException {
		String startingInput84 = "this is not an error.  Not at all.  Just a very long, boring nonsense statement.";
		
		InputStream is = new ByteArrayInputStream(startingInput84.getBytes("UTF-8"));
		// should be 84 (divisible by 4)
		int strlength = startingInput84.length();
		PushbackInputStream pbis = new PushbackInputStream(is,strlength + 4);
		
		System.out.println(strlength);
		System.out.println(Math.round(strlength/4)+1);
		int quarterlengthplus = Math.round(strlength/4)+1;
		
		byte[] a = new byte[quarterlengthplus];
		byte[] b = new byte[quarterlengthplus * 2];
		byte[] c = new byte[quarterlengthplus * 3];
		byte[] d = new byte[quarterlengthplus * 4];
		

		pbis.read(a);		
		pbis.unread(a);
		pbis.read(b);		
		pbis.unread(b);
		pbis.read(c);		
		pbis.unread(c);
		// example is designed to read beyond length of stream,
		int x = pbis.read(d);
		// so need to only unread the number of bytes read
		pbis.unread(d,0,x);
		
		String endingInput = IOUtils.toString(pbis);
		assertEquals(startingInput84.length(), endingInput.length());
		assertEquals(startingInput84, endingInput);
	}
	

	@Test
	public void testIsException() throws IOException {
		String startingInput = 
		"<error name='NotFound'\n" + 
		"       errorCode='404'\n" +
		"       detailCode='1020.1'\n" +
		"       pid='123XYZ'>\n" +
		"  <description>The specified object does not exist on this node.</description>\n" +
		"  <traceInformation>\n" +
		"    method: mn.get\n" +
		"    hint: http://cn.dataone.org/cn/resolve/123XYZ\n" +
		"  </traceInformation>\n" +	
		"</error>";
		InputStream is = new ByteArrayInputStream(startingInput.getBytes("UTF-8"));
		ExceptionalInputStream eis = new ExceptionalInputStream(is);
		if (!eis.isException()) {
			fail("should not have gotten here");
		}
		String endingInput = IOUtils.toString(eis);
		assertEquals(startingInput, endingInput);
		
	}
	
	/*
	 * Empty streams should not be exceptions, because they are valid 
	 * returns from the interface.
	 */
	@Test
	public void testEmptyStream() throws IOException {
		String startingInput = "";
		InputStream is = new ByteArrayInputStream(startingInput.getBytes("UTF-8"));
		ExceptionalInputStream eis = new ExceptionalInputStream(is);
		if (eis.isException()) {
			fail("should not have gotten here");
		}
		String endingInput = IOUtils.toString(eis);
		assertEquals(startingInput, endingInput);
		
	}
	
	@Ignore("functionality not written yet")
	@Test
	public void testIsDataoneType() throws MarshallingException, IOException {
//		Identifier id = new Identifier();
//		id.setValue("foo");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		TypeMarshaller.marshalTypeToOutputStream(id, baos);
		System.out.println(baos.toString());
	}
	
}
