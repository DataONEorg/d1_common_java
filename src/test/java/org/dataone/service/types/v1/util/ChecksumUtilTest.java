package org.dataone.service.types.v1.util;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.dataone.service.types.v1.Checksum;
import org.junit.Before;
import org.junit.Test;

public class ChecksumUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testChecksumInputStreamString() throws NoSuchAlgorithmException, IOException {
		byte[] ba = {'a','b','c','d','e'};
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);
		Checksum cs = ChecksumUtil.checksum(bais, "MD5");
		System.out.println("CS alg: " + cs.getAlgorithm());
		System.out.println("CS value: " + cs.getValue());
		assertTrue("algorithm should match", cs.getAlgorithm().equalsIgnoreCase("MD5"));
		
		
	}

	@Test
	public final void testChecksumByteArrayString() throws NoSuchAlgorithmException {
		byte[] ba = {'a','b','c','d','e'};
		Checksum cs = ChecksumUtil.checksum(ba, "MD5");
		System.out.println("CS alg: " + cs.getAlgorithm());
		System.out.println("CS value: " + cs.getValue());
		assertTrue("algorithm should match", cs.getAlgorithm().equalsIgnoreCase("MD5"));
		
	}
	
	
	@Test
	public final void testChecksumBadAlgorithm() {
		byte[] ba = {'a','b','c','d','e'};
		try {
			Checksum cs = ChecksumUtil.checksum(ba, "BEEP");
			fail("Should have thrown exception");
		} catch (NoSuchAlgorithmException e) {
			
		}		
	}
	
	
	@Test
	public final void testChecksumNullParameter() {
				try {
			Checksum cs = ChecksumUtil.checksum((byte[]) null, "BEEP");
			fail("Should have thrown exception");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("exception thrown: " + e.getClass().getCanonicalName());
		}		
	}
	

	@Test
	public final void testAreChecksumsEqual() {
		Checksum cs1 = new Checksum();
		cs1.setAlgorithm("foo");
		cs1.setValue("bar");
		
		Checksum cs1b = new Checksum();
		cs1b.setAlgorithm("foo");
		cs1b.setValue("bar");
		
		Checksum cs2 = new Checksum();
		cs2.setAlgorithm("biz");
		cs2.setValue("baz");
		
		Checksum csEmpty = new Checksum();
		
		Checksum csHalf = new Checksum();
		csHalf.setAlgorithm("MD23");
		
		
		assertTrue("equal values and algorithms should be equal", ChecksumUtil.areChecksumsEqual(cs1, cs1b));
		assertFalse("different value and aogirithm should return false", ChecksumUtil.areChecksumsEqual(cs1,cs2));
		assertFalse("empty Checksum properties should return false", ChecksumUtil.areChecksumsEqual(cs2, csEmpty));
		assertFalse("half-filled Checksum properties should return false", ChecksumUtil.areChecksumsEqual(cs2, csHalf));
	}

}
