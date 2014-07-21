
package org.dataone.service.types.v2;

import java.io.Serializable;

/** 
 * One value from the DataONE Object Format Vocabulary
 which is returned by :func:`CNCore.getFormat()`.An *ObjectFormat* is the structure returned from the
 :func:`CNCore.getFormat()` method of the CN REST interface. It provides
 the unique identifier and the name associated with the object format.
 Future versions may contain additional structured content from external
 common typing systems. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ObjectFormat">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns1:ObjectFormat">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="xs:string" name="mimeType" minOccurs="0"/>
 *         &lt;xs:element type="xs:string" name="extension" minOccurs="0"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ObjectFormat extends org.dataone.service.types.v1.ObjectFormat
    implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private String mimeType;
    private String extension;

    /** 
     * Get the 'mimeType' element value. The MIME type for this object format.
     * 
     * @return value
     */
    public String getMimeType() {
        return mimeType;
    }

    /** 
     * Set the 'mimeType' element value. The MIME type for this object format.
     * 
     * @param mimeType
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /** 
     * Get the 'extension' element value. The extension to be used when serializing the object to a file.
     * 
     * @return value
     */
    public String getExtension() {
        return extension;
    }

    /** 
     * Set the 'extension' element value. The extension to be used when serializing the object to a file.
     * 
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
}
