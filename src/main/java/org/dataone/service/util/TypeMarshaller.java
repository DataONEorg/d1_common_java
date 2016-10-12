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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.dataone.configuration.Settings;
import org.dataone.exceptions.MarshallingException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * The standard class used to marshal and unmarshal datatypes to and from input
 * and output streams and file structures.
 * 
 * This class maintains static global state of expensive-to-create JAXB contexts.
 * 
 * Schema validation is performed by default upon marshalling, using either the packaged
 * DataONE schemas, or if set, from the list of schemas indicated by marshalling.d1.schema.urls
 * (Can only be reset with restart of the runtime).
 * 
 * Validation by default can be disabled with the configuration property 
 * 'marshalling.d1.schema.validation=false'
 * 
 * 
 * 
 * @author rwaltz
 */
public class TypeMarshaller {

    static Logger logger = Logger.getLogger(TypeMarshaller.class.getName());

    static final protected Map<Class,JAXBContext> jaxbContextMap = new HashMap<>();
    
    static final protected boolean USE_SCHEMA_VALIDATION = 
            Settings.getConfiguration().getBoolean("marshalling.d1.schema.validation", /* default */ true);
    
    /** The java representation of the DataONE schemas used to validate unmarshalling */
    static final protected Schema D1_SCHEMAS;
    static {
        // initialization of D1_SCHEMAS
        StreamSource[] schemas = null;

        String[] schemaUrls = Settings.getConfiguration().getStringArray("marshalling.d1.schema.urls");

        if (schemaUrls == null || schemaUrls.length == 0) {
            // load the ones from the jar
            schemas = new StreamSource[] {
                    new StreamSource( TypeMarshaller.class.getResourceAsStream("dataoneTypes.xsd") ),
                    new StreamSource( TypeMarshaller.class.getResourceAsStream("dataoneTypes_v1.1.xsd")),
                    new StreamSource( TypeMarshaller.class.getResourceAsStream("dataoneTypes_v2.0.xsd")),
                    new StreamSource( TypeMarshaller.class.getResourceAsStream("dataoneErrors.xsd"))
            };
        } 
        else {
            schemas = new StreamSource[schemaUrls.length];
            for (int i=0; i<schemaUrls.length; i++) {
                String url = schemaUrls[i];
                logger.debug("Adding schema location: " + url);

                try {
                    schemas[i] = new StreamSource((new URL(url)).openStream());
                } catch (IOException e) {
                    throw new Error("Failed to initialize TypeMarshaller with the (DataONE) schemas!! IOException from: " + url,e);
                }
            }
        } 

        // build a new in memory schema from the individual ones
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            D1_SCHEMAS =  schemaFactory.newSchema(schemas);
        } catch (SAXException e) {
            throw new Error("Failed to initialize TypeMarshaller with the DataONE schemas!!", e);
        } finally {
            for (StreamSource s: schemas) {
                IOUtils.closeQuietly(s.getInputStream());
            }
        }
    }

    
    /**
     * A method to manage JAXB data type contexts, as they are expensive to build
     * and destroy.  Contexts are thread safe, while the Marshallers and Unmarshallers
     * built from these are not (and therefore not maintained).
     * @param clazz - the class of the context needed
     * @return the JAXBContext for the class
     * @throws JAXBException
     */
    protected static synchronized JAXBContext getJAXBContext(Class clazz) throws JAXBException {
        if (! jaxbContextMap.containsKey(clazz) ) {
            jaxbContextMap.put(clazz,JAXBContext.newInstance( clazz ));
        }
        return jaxbContextMap.get(clazz);
    }
    

    /**
     * A method to validate DataONE types against the schemas registered with the
     * TypeMarshaller.  It delegates validation to the underlying SAX parse, but is 
     * more efficient than marshalTypeTo... methods in this class, because it does 
     * not create output streams, or generate any XML.
     *  
     * @param typeObject
     * @throws MarshallingException
     * @since 2.3.0
     */
    public static void validateAgainstSchema(Object typeObject) throws MarshallingException {

        try {
            Marshaller jaxbMarshaller = TypeMarshaller.getJAXBContext(typeObject.getClass()).createMarshaller();
            jaxbMarshaller.setSchema(D1_SCHEMAS);
            jaxbMarshaller.marshal( typeObject, new DefaultHandler());
        } 
        catch (JAXBException e) {
            throw new MarshallingException(e);
        }
    }
    
    
    /**
     * Marshalls the typeObject to the provided file.
     * Schema validation occurs by default.
     * @param typeObject
     * @param filenamePath
     * @throws MarshallingException
     * @throws IOException
     * @throws FileNotFoundException
     */
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
    
    /**
     * Marshalls the typeObject to the provided outputStream.
     * Schema validation occurs by default.
     * Does not close the outputstream. 
     * @param typeObject
     * @param os
     * @throws MarshallingException
     * @throws IOException
     */    
    public static void marshalTypeToOutputStream(Object typeObject, OutputStream os) 
    throws MarshallingException, IOException 
    {
        marshalTypeToOutputStream(typeObject, os, null);
    }
    
    /**
     * Marshalls the typeObject to the provided outputStream.
     * Schema validation occurs by default.
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
        try {
            Marshaller jaxbMarshaller = TypeMarshaller.getJAXBContext(typeObject.getClass()).createMarshaller();
            //                jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "dataoneTypes.xsd");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            if (styleSheet != null) {
                jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",  
                        String.format("<?xml-stylesheet type=\"text/xsl\" href=\"%s\" ?>", styleSheet));
            }
            if (TypeMarshaller.USE_SCHEMA_VALIDATION)
                jaxbMarshaller.setSchema(D1_SCHEMAS);
            
            jaxbMarshaller.marshal( typeObject, os );

        } catch (JAXBException e) {
            throw new MarshallingException(e.getMessage(),e);
        }
    }
    
    
    /**
     * Unmarshals the contents of the filenamePath into the specified domainClass.
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
        try {
            Unmarshaller jaxbUnmarshaller = TypeMarshaller.getJAXBContext(domainClass).createUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(file); 
        } catch (JAXBException e) {
            throw new MarshallingException(e.getMessage(),e);
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
     * and unequivocally closes the passed in InputStream .
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
        try {
            Unmarshaller jaxbUnmarshaller = TypeMarshaller.getJAXBContext(domainClass).createUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new MarshallingException(e.getMessage(),e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
