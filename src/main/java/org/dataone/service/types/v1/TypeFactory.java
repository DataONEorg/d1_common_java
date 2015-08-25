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

package org.dataone.service.types.v1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dataone.service.exceptions.NotFound;
import org.dataone.service.exceptions.ServiceFailure;
import org.dataone.service.types.v1.util.ChecksumUtil;
import org.dataone.service.util.TypeMarshaller;
import org.jibx.runtime.JiBXException;


/**
 * A Factory class for building, cloning, and converting common DataONE types.
 * Dedicated builders and cloning methods are built for the "ID" types, and 
 * templated clone and convert methods for other types.
 * 
 * @author rnahf
 *
 */
public class TypeFactory {

    protected static Logger logger = Logger.getLogger(TypeFactory.class.getName());

    /**
     * Factory method for creating an ObjectFormatIdentifier. Does not enforce business rules.
     * @param value - a String containing at least one non-whitespace character 
     * @return
     */
    public static ObjectFormatIdentifier buildFormatIdentifier(String value) {

        ObjectFormatIdentifier fid = new ObjectFormatIdentifier();
        fid.setValue(value);
        return fid;
    }

    /**
     * Factory method for creating an Identifier. Does not enforce business rules.
     * @param value - a String containing at least one non-whitespace character 
     * @return
     */
    public static Identifier buildIdentifier(String value) {
        
        Identifier id = new Identifier();
        id.setValue(value);
        return id;
    }

    /**
     * Factory method for creating a NodeReference. Does not enforce business rules.
     * @param value - a String containing at least one non-whitespace character 
     * @return
     */
    public static NodeReference buildNodeReference(String value){
        
        NodeReference id = new NodeReference();
        id.setValue(value);
        return id;
    }

    /**
     * Factory method for creating a Subject.  Does not enforce business rules.
     * @param value - a String containing at least one non-whitespace character 
     * @return
     */
    public static Subject buildSubject(String value) {
        
        Subject s = new Subject();
        s.setValue(value);
        return s;
    }
    
//    /**
//     * Factory method for creating a schema-valid Subject.  Does not enforce business rules.
//     * @param value - a String containing at least one non-whitespace character 
//     * @return
//     * @throws ValidationException - if value is null, zero length, or only whitespace characters
//     */
//    public static Subject buildSubject(String value)
//    throws ValidationException 
//    {
//        if (StringUtils.isBlank(value))
//            throw new ValidationException("the value field cannot be null nor contain only whitespace characters!");
//        
//        Subject s = new Subject();
//        s.setValue(value);
//        return s;
//    }

    /**
     * Factory method for cloning an ObjectFormatIdentifier from another, such that they
     * do not share any references for mutable properties.
     * @param orig
     * @return
     */
    public static ObjectFormatIdentifier cloneFormatIdentifier(
            ObjectFormatIdentifier orig) {
        if (orig == null) return null;
        // Strings are immutable so using existing one
        return TypeFactory.buildFormatIdentifier(orig.getValue());
    }
    
    /**
     * Factory method for cloning an Identifier from another, such that they
     * do not share any references for mutable properties.
     * @param orig
     * @return
     */
    public static Identifier cloneIdentifier(Identifier orig) {
        if (orig == null) return null;
        // Strings are immutable so using existing one
        return TypeFactory.buildIdentifier(orig.getValue());
    }
    
    /**
     * Factory method for cloning a NodeReference from another, such that they
     * do not share any references for mutable properties.
     * @param orig
     * @return
     */
    public static NodeReference cloneNodeReference(NodeReference orig) {
        if (orig == null) return null;
        // Strings are immutable so using existing one
        return TypeFactory.buildNodeReference(orig.getValue());
    }
    
    /**
     * Factory method for cloning a Subject from another, such that they
     * do not share any references for mutable properties.
     * @param orig
     * @return
     * @throws ValidationException 
     */
    public static Subject cloneSubject(Subject orig) {
        if (orig == null) return null;
        // Strings are immutable so using existing one
        return TypeFactory.buildSubject(orig.getValue());
    }
    
    
    /**
     * Clone via marshalling and unmarshalling using the TypeMarshaller to ensure 
     * a DEEP copy and no shared object references.  If original is null, returns null.
     * <p>
     * Called using:  TypeFactory.<Type>clone(original)
     * 
     * @param original
     * @return
     * @throws JiBXException
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    public static <T> T clone(Object original) throws JiBXException, IOException, 
    InstantiationException, IllegalAccessException {
        if (original == null) return null;
        
        if (logger.isDebugEnabled()) 
            logger.debug(String.format("Cloning object %s (of type %s)...", 
                    original.toString(), original.getClass().getCanonicalName()));
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        TypeMarshaller.marshalTypeToOutputStream(original, baos);
        return (T)TypeMarshaller.unmarshalTypeFromStream(original.getClass(), 
                new ByteArrayInputStream(baos.toByteArray()));
    }

//////////////////////////////////////////////////////////////////////////////////////////

    public static AccessRule buildAccessRule(String subjectString, Permission permission) {
        if (subjectString == null || permission == null) {
            return null;
        }
        AccessRule ar = new AccessRule();
        ar.addSubject(TypeFactory.buildSubject(subjectString));
        ar.addPermission(permission);
        return ar;
    }

    public static AccessRule buildAccessRule(String subjectString, Permission[] permissions) {
        if (subjectString == null || permissions == null) {
            return null;
        }
        AccessRule ar = new AccessRule();
        ar.addSubject(TypeFactory.buildSubject(subjectString));

        for (Permission p: permissions)
            ar.addPermission(p);

        return ar;
    }

//////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Builds a minimal and 'typical' SystemMetadata object containing all of the required fields needed
     * for submission to DataONE at time of create.  'Typical' in this case denotes
     * that the rightsHolder and submitter are the same Subject. The Checksum and 
     * content length are derived from the InputStream.  
     * @param id
     * @param data
     * @param checksumAlgorithm - if null, defaults to "MD5"
     * @param formatId - this method does not validate whether the formatId is supported
     * @param submitterRightsHolder - uses this Subject as values for both of the properties
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws NotFound
     * @throws ServiceFailure
     */
    public static SystemMetadata buildMinimalSystemMetadata(Identifier id, InputStream data, 
            String checksumAlgorithm, ObjectFormatIdentifier formatId, Subject submitterRightsHolder) 
                    throws NoSuchAlgorithmException, IOException, NotFound, ServiceFailure {
        
        SystemMetadata sm = new SystemMetadata();
        sm.setIdentifier(id);

        sm.setFormatId(formatId);

        //generate the checksum and length fields from the inputStream
        CountingInputStream cis = new CountingInputStream(data);

        if (checksumAlgorithm == null)  checksumAlgorithm = "MD5";
        Checksum checksum = ChecksumUtil.checksum(cis, checksumAlgorithm);

        sm.setChecksum(checksum);
        sm.setSize(new BigInteger(String.valueOf( cis.getByteCount() )));
        cis.close();

        // serializer needs a value, though MN will ignore the value
        sm.setSerialVersion(BigInteger.ONE);

        // set submitter and rightholder from the associated string
        sm.setSubmitter(submitterRightsHolder);
        sm.setRightsHolder(submitterRightsHolder);

        Date now = new Date();
        sm.setDateUploaded(now);
        sm.setDateSysMetadataModified(now);

        return sm;
    }
    
//////////////////////////////////////////////////////////////////////////////////////////    
    
    
}
