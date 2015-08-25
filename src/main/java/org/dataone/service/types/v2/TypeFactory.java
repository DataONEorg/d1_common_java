/**
 * 
 */
package org.dataone.service.types.v2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.lang.StringUtils;
import org.dataone.service.exceptions.NotFound;
import org.dataone.service.exceptions.ServiceFailure;
import org.dataone.service.types.v1.Checksum;
import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.ObjectFormatIdentifier;
import org.dataone.service.types.v1.Subject;
import org.dataone.service.types.v2.SystemMetadata;
import org.dataone.service.types.v1.util.ChecksumUtil;
import org.jibx.runtime.JiBXException;



/**
 * @author rnahf
 *
 */
public class TypeFactory extends org.dataone.service.types.v1.TypeFactory {


    
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
    
    /**
     * Intended for converting DataONE objects to an instance of another version, 
     * this method copies and converts based on common properties.  Depending on 
     * the direction of conversion, some loss of information will occur without 
     * warning if the destination type does not contain all of the properties as
     * the original, and as the same name.
     * <p>
     * Properties of the destination may share references with the original.
     * Use TypeFactory.clone(original) prior to conversion if shared references
     * are not desired. 
     * 
     * @param original the instance being converted
     * @param destinationClass the target class to return an instance of
     * @return an instance of destinationClass with all shared properties copied from the original instance
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws JiBXException
     * @throws IOException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException 
     */
    public static <T> T convertTypeFromType(Object original, Class<T> destinationClass) 
        throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        // shortcut the convert if it is converting to the same class as original
        if (original.getClass().getCanonicalName().equals(destinationClass.getCanonicalName())) 
            return (T)original;
        
        // copy properties to the new type
        
        if (destinationClass.isEnum()) {
            T[] consts = destinationClass.getEnumConstants();
            for (T c : consts) {
                if (c.toString().equals(original.toString())) {
                    return c;
                }
            }
            return null;
            //throw new InstantiationException("Could not find Enum constant for '" + original.toString() + "'");
        } 
        T destInstance = destinationClass.newInstance();
        
        
        Map<String,String> propMap = BeanUtils.describe(original);
        if (logger.isDebugEnabled()) 
            logger.debug("BeanUtils.describe produces map of size: " + propMap.size());
        
        for (Entry<String, String> propTypePair: propMap.entrySet()) {
            String propName = propTypePair.getKey();
            String valueClass = propTypePair.getValue();
            String readMethodName = "get" + StringUtils.capitalize(propName);
            
            if (logger.isTraceEnabled()) 
                    logger.trace(String.format("%s : %s", propName, valueClass));

            if (valueClass == null)
                continue;
            if (propName.equals("class"))
                continue;

            try {
                Class<?> origType = getReturnType(original,propName);
                Class<?> destType = getReturnType(destInstance,propName);
                
                if (logger.isTraceEnabled()) 
                        logger.trace(String.format("  Copying property '%s', type '%s' ==> '%s'",
                                propName, origType.getCanonicalName(), destType.getCanonicalName()));
                
                if (propName.endsWith("List")) {
                    int i=0;
                    try {
                        while (true) {
                            Object listItem = PropertyUtils.getIndexedProperty(original, propName, i);
                            logger.trace("    Got indexed Property: " + listItem.toString());
                            Class<?> destListType = getListReturnType(destInstance,propName);
                            if (logger.isTraceEnabled()) 
                                logger.trace(String.format("    [%d] Copying Indexed property '%s', type '%s' ==> '%s'",
                                        i, propName, listItem.getClass().getCanonicalName(), destListType.getCanonicalName()));
                            if (destListType == listItem.getClass())
                                // it's not a clone!
                                addIndexedProperty(destInstance, propName, listItem);
                            else
                                addIndexedProperty(destInstance, propName,
                                        TypeFactory.convertTypeFromType(listItem, destListType));
                            i++;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        if (logger.isTraceEnabled())
                                logger.trace(String.format("    for indexed property '%s', copied %d elements", propName, i));
                        ; // using this exception to exit the loop... must happen eventually
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (origType == boolean.class)
                        readMethodName = readMethodName.replaceFirst("get", "is");
                    
                    Method m = original.getClass().getMethod(readMethodName, (Class<?>[])null);
                    if (destType == origType) 
                        /// did this clone?
                        BeanUtils.copyProperty(destInstance, propName,
                                PropertyUtils.getSimpleProperty(original, propName));
//                              m.invoke(original, (Object[])null));
                        
                    else
                        BeanUtils.copyProperty(destInstance, propName, 
                                TypeFactory.convertTypeFromType(
                                        PropertyUtils.getSimpleProperty(original, propName), 
                                        destType));
                }
            } catch (NoSuchMethodException e) {
                logger.debug("  caught NoSuchMethodException - no corresponding property in destination type");
                ; // the corresponding property in the destination class doesn't exist
            }
        }
        return destInstance;
    }


    /*
     * helper class for convertTypeFromType
     */
    private static Class<?> getReturnType(Object o, String property) throws SecurityException, NoSuchMethodException {
        String methodName = "get" + StringUtils.capitalize(property);
        try {
            return o.getClass().getMethod(methodName).getReturnType();
        } catch (NoSuchMethodException e) {
             methodName = "is" +StringUtils.capitalize(property);
            return o.getClass().getMethod(methodName).getReturnType();
        } 
    }
    
    /*
     * helper class for convertTypeFromType
     */
    private static Class<?> getListReturnType(Object o, String property) throws NoSuchMethodException, SecurityException {
        
        // mangles the property name so that fooList become method name getFoo(int i);
        String methodName = "get" + StringUtils.capitalize(property.substring(0, property.length()-4));
        return o.getClass().getMethod(methodName, int.class).getReturnType(); 
    }
    
    /*
     * helper class for convertTypeFromType
     */
    private static void addIndexedProperty(Object destinationInstance, String propName, Object valueObject) 
    throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Method addMethod = destinationInstance.getClass().getMethod("add" + StringUtils.capitalize(
                propName).substring(0, propName.length()-4), valueObject.getClass());
        addMethod.invoke(destinationInstance, valueObject);
    }

}
