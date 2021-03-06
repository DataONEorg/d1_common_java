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

package org.dataone.service.types.v1.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.dataone.service.types.v1.Checksum;

/**
 *
 * @author waltz
 */
public class ChecksumUtil {
   /**
     * return a Checksum based on the input of the stream
     * 
     * A useful technique when creating systemMetadata is to wrap the the 
     * inputStream in an org.apache.commons.io.input.CountingInputStream,
     * to get the length of the content from the stream (after calling this method).
     * This saves you from having to re-read or turn the inputStream into a byte[].
     * 
     * @param is
     * @param algorithmAlgorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static Checksum checksum(InputStream is, String checksumAlgorithm) throws NoSuchAlgorithmException, IOException {
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance(checksumAlgorithm);
        int numRead;

        do {
            numRead = is.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        // reset if it is supported
        //
        // mark is only supported on two Java supplied InputStreams
        // BufferedInputStream and ByteArrayInputStream
        // for BufferedInputStream, reset will only go the beginning of the
        // buffer (which if shorter than the stream itself, will result
        // in resetting to the middle of the stream )

        if (is.markSupported()) {
        	is.reset();
        }
        
        String csStr = getHex(complete.digest());
        Checksum checksum = new Checksum();
        checksum.setValue(csStr);
        checksum.setAlgorithm(checksumAlgorithm);
        return checksum;
    }
    
    
    /**
     * produce a checksum for the given byte array
     */
    public static Checksum checksum(byte[] object, String algorithm) throws NoSuchAlgorithmException 
    {
    	MessageDigest complete = MessageDigest.getInstance(algorithm);
    	complete.update(object);
        String csStr = getHex(complete.digest());
        
    	Checksum checksum = new Checksum();
        checksum.setValue(csStr);
        checksum.setAlgorithm(algorithm);
        return checksum;
    }
    
    
    /**
     * convert a byte array to a hex string
     */
    private static String getHex(byte[] raw) {
        final String HEXES = "0123456789abcdef";
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    /**
     * Returns true if and only if checksum1 and checksum2 are equal:
     *  Values are equal, algorithms are equal.
     *  Null objects or properties of checksum objects will return false
     * @param checksum1
     * @param checksum2
     * @return
     */
    public static boolean areChecksumsEqual(Checksum checksum1, Checksum checksum2) {
        boolean equal = false;
        if (checksum1 != null && checksum2 != null && checksum1.getValue() != null
                && checksum2.getValue() != null && checksum1.getAlgorithm() != null
                && checksum2.getAlgorithm() != null
                && checksum1.getValue().equals(checksum2.getValue())
                && checksum1.getAlgorithm().equals(checksum2.getAlgorithm())) {
            equal = true;
        }
        return equal;
    }
}
