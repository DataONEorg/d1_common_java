
package org.dataone.service.types.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/** 
 * Extends Version 1.x :class:`Types.ObjectFormat` by 
 adding :term:`mediaType` and :term:`extension` elements.

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ObjectFormat">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns1:ObjectFormat">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns:MediaType" name="mediaType" minOccurs="0"/>
 *         &lt;xs:element type="xs:string" name="extension" minOccurs="0"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectFormat", propOrder = {
    "mediaType",
    "extension"
})
@XmlRootElement(name = "objectFormat")
public class ObjectFormat extends org.dataone.service.types.v1.ObjectFormat
    implements Serializable
{
    protected MediaType mediaType;
    protected String extension;
    
    private static final long serialVersionUID = 10000000;


    /** 
     * Get the 'mediaType' element value. The IANA Media Type for this object format.
                  
     * 
     * @return value
     */
    public MediaType getMediaType() {
        return mediaType;
    }

    /** 
     * Set the 'mediaType' element value. The IANA Media Type for this object format.
                  
     * 
     * @param mediaType
     */
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    /** 
     * Get the 'extension' element value. Suggested file name extension to be used 
                    when serializing this type of object to a file. The value 
                    should not include the period (.).
     * 
     * @return value
     */
    public String getExtension() {
        return extension;
    }

    /** 
     * Set the 'extension' element value. Suggested file name extension to be used 
                    when serializing this type of object to a file. The value 
                    should not include the period (.).
     * 
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
}
