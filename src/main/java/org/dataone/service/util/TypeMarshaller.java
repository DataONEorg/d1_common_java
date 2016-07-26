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

package org.dataone.service.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dataone.exceptions.MarshallingException;
import org.apache.log4j.Logger;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

/**
 * The standard class used to marshal and unmarshal datatypes to and from input
 * and output streams and file structures.
 * 
 * This class maintains static global state of expensive-to-create JAXB contexts,
 * Marshallers, and Unmarshallers.  The latter two are maintained as ThreadLocals
 * to avoid multi-threading issues. 
 * 
 * @author rwaltz
 */
public class TypeMarshaller {

    static Logger logger = Logger.getLogger(TypeMarshaller.class.getName());

    static final private Boolean USE_JIBX = false;
    static final private Boolean CACHE_CONTEXT = true;
    static final private Boolean CACHE_MARSHALLERS = false;
    
    static final protected Map<Class,JAXBContext> jaxbContextMap = new HashMap<>();
    static final protected Map<Class,ThreadLocal<Unmarshaller>> jaxbUnmarshallerMap = new HashMap<>();
    static final protected Map<Class,ThreadLocal<Marshaller>> jaxbMarshallerMap = new HashMap<>();

    /**
     * A method to manage JAXB data type contexts, as they are expensive to build
     * and destroy.  Contexts are thread safe, while the Marshallers and Unmarshallers
     * built from these are not (and therefore not maintained).
     * @param clazz - the class of the context needed
     * @return the JAXBContext for the class
     * @throws JAXBException
     */
    protected static synchronized JAXBContext getJAXBContext(Class clazz) throws JAXBException {
        if (CACHE_CONTEXT) {
            if (! jaxbContextMap.containsKey(clazz) ) {
                jaxbContextMap.put(clazz,JAXBContext.newInstance( clazz ));
            }
            return jaxbContextMap.get(clazz);
        } else {
            System.out.print("c");
            return JAXBContext.newInstance( clazz);
        }
    }
    
    protected static synchronized Unmarshaller getJAXBUnmarshaller(Class clazz) throws JAXBException {
        if (CACHE_MARSHALLERS) {
            if (! jaxbUnmarshallerMap.containsKey(clazz))  {
                ThreadLocal<Unmarshaller> tlUnmarshaller = new ThreadLocal<>();
                tlUnmarshaller.set(getJAXBContext(clazz).createUnmarshaller());
                jaxbUnmarshallerMap.put(clazz,tlUnmarshaller);
            }
            else if (jaxbUnmarshallerMap.get(clazz).get() == null) {
                // have a ThreadLocal, but this thread doesn't have a legit value yet
                jaxbUnmarshallerMap.get(clazz).set(getJAXBContext(clazz).createUnmarshaller());
            }
            return jaxbUnmarshallerMap.get(clazz).get();
        } else {
            System.out.print("u");
            return getJAXBContext(clazz).createUnmarshaller();
        }
    }
    
    protected static synchronized Marshaller getJAXBMarshaller(Class clazz) throws JAXBException {
        if (CACHE_MARSHALLERS) {
            if (! jaxbMarshallerMap.containsKey(clazz)) {
                ThreadLocal<Marshaller> tlMarshaller = new ThreadLocal<>();
                tlMarshaller.set(getJAXBContext(clazz).createMarshaller());
                jaxbMarshallerMap.put(clazz,tlMarshaller);
            }
            else if (jaxbMarshallerMap.get(clazz).get() == null) {
                // have a ThreadLocal, but this thread doesn't have a legit value yet
                jaxbMarshallerMap.get(clazz).set(getJAXBContext(clazz).createMarshaller());
            }
            return jaxbMarshallerMap.get(clazz).get();
        } else {
            System.out.print("m");
            return getJAXBContext(clazz).createMarshaller();
        }
    }
    
    
    
    public static File marshalTypeToFile(Object typeObject, String filenamePath) 
    throws MarshallingException, FileNotFoundException, IOException 
    {
    	FileOutputStream typeOutput = null;
    	File outputFile = new File(filenamePath);
    	typeOutput = new FileOutputStream(outputFile);
    	
    	try {
    		marshalTypeToOutputStream(typeObject, typeOutput, null);
    	} 
    	finally {
    		if (typeOutput != null)
    			try {
    				typeOutput.close();
    			} catch (IOException ex) {
    				logger.error(ex.getMessage(), ex);
    			}
        }
        return outputFile;
    }
    
    
    public static void marshalTypeToOutputStream(Object typeObject, OutputStream os) 
    throws MarshallingException, IOException 
    {
        marshalTypeToOutputStream(typeObject, os, null);
    }
    
    /**
     * Marshalls the typeObject to the provided outputStream.  
     * Does not close the outputstream. 
     * @param typeObject
     * @param os
     * @param styleSheet
     * @throws MarshallingException
     * @throws IOException
     */
    public static void marshalTypeToOutputStream(Object typeObject, OutputStream os, String styleSheet)
    throws MarshallingException, IOException 
    {
        if (USE_JIBX) {
            try {
                IBindingFactory bfact = BindingDirectory.getFactory(typeObject.getClass());

                IMarshallingContext mctx = bfact.createMarshallingContext();
                mctx.startDocument("UTF-8", null, os);
                if (styleSheet != null) {
                    mctx.getXmlWriter().writePI("xml-stylesheet", "type=\"text/xsl\" href=\"" + styleSheet + "\"");
                }
                mctx.marshalDocument(typeObject);
            } catch (JiBXException e) {
                throw new MarshallingException(e);
            } 
        } else {
            try {
                Marshaller jaxbMarshaller = TypeMarshaller.getJAXBMarshaller(typeObject.getClass());//.createMarshaller();
//                jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "dataoneTypes.xsd");
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                if (styleSheet != null) {
                    jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",  
                            String.format("<?xml-stylesheet type=\"text/xsl\" href=\"%s\" ?>", styleSheet));
                }
                jaxbMarshaller.marshal( typeObject, os );
                
            } catch (JAXBException e) {
                throw new MarshallingException(e.getMessage(),e);
            }
        }
    }
    
    
    /**
     * Unmarshals the contents of the filenamePath into the specified domainClass
     * 
     * @param <T>
     * @param domainClass
     * @param file
     * @return - an instance of the domainClass
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws MarshallingException
     */
    public static <T> T unmarshalTypeFromFile(Class<T> domainClass, File file) 
    throws IOException, InstantiationException, IllegalAccessException, MarshallingException 
    {
        if (USE_JIBX) {
            Reader reader = null;
        
            try {
                IBindingFactory bfact = BindingDirectory.getFactory(domainClass);
                IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
                reader = new FileReader(file);
                T domainObject = (T) uctx.unmarshalDocument(reader);
                return domainObject;
            } catch (JiBXException e) {
                throw new MarshallingException(e);
            }
            finally {
                if (reader != null) {
                    reader.close();
                }
            }
        } else {
            try {
                Unmarshaller jaxbUnmarshaller = TypeMarshaller.getJAXBUnmarshaller(domainClass);
                return (T) jaxbUnmarshaller.unmarshal(file); 
            } catch (JAXBException e) {
                throw new MarshallingException(e.getMessage(),e);
            }
        }
    }

    
     /**
     * Unmarshalls the contents of file parameter to the specified domainClass
     * 
     * @param <T>
     * @param domainClass
     * @param filenamePath
     * @return - an instance of the domainClass
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws MarshallingException
     */
    public static <T> T unmarshalTypeFromFile(Class<T> domainClass, String filenamePath) 
    throws IOException, InstantiationException, IllegalAccessException, MarshallingException 
    {
        return TypeMarshaller.unmarshalTypeFromFile(domainClass, new File(filenamePath));
    }
    
    
    /**
     * Unmarshals the inputStream to the specified domainClass
     * and unequivocally closes the passed in InputStream 
     * @param <T>
     * @param domainClass
     * @param inputStream
     * @return - an instance of the domainClass
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws MarshallingException
     */
    public static <T> T unmarshalTypeFromStream(Class<T> domainClass, InputStream inputStream) 
    throws IOException, InstantiationException, IllegalAccessException, MarshallingException 
    {
        if (USE_JIBX) {
            try {
                IBindingFactory bfact = BindingDirectory.getFactory(domainClass);
                IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
                T domainObject = (T) uctx.unmarshalDocument(inputStream, null);
                return domainObject;

            } catch (JiBXException e) {
                throw new MarshallingException(e);
            }
            finally {
                if (inputStream != null) {
                    inputStream.close();
                } else {
                    throw new IOException("InputStream was null");
                }
            }
        } else {
            try {
                Unmarshaller jaxbUnmarshaller = TypeMarshaller.getJAXBUnmarshaller(domainClass);
                return (T) jaxbUnmarshaller.unmarshal(inputStream);
            } catch (JAXBException e) {
                throw new MarshallingException(e.getMessage(),e);
            }
        }
    }
}
