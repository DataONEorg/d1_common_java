
package org.dataone.service.types.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ObjectFormat">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:ObjectFormatIdentifier" name="formatId" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="formatName" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="formatType" minOccurs="1" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectFormat", propOrder = {
    "formatId",
    "formatName",
    "formatType"
})
@XmlRootElement(name = "objectFormat")
public class ObjectFormat implements Serializable
{
    @XmlElement(required = true)
    // TODO: make sure ObjectFormatIDentifier works
    protected ObjectFormatIdentifier formatId;
    @XmlElement(required = true)
    protected String formatName;
    @XmlElement(required = true)
    protected String formatType;

    private static final long serialVersionUID = 10000000;

    /** 
     * Get the 'formatId' element value.  The unique identifier of the object format in the
            DataONE Object Format Vocabulary. The identifier should comply with
            DataONE Identifier rules, i.e. no whitespace, only UTF-8 or US-ASCII
            printable characters.
     * 
     * @return value
     */
    public ObjectFormatIdentifier getFormatId() {
        return formatId;
    }

    /** 
     * Set the 'formatId' element value.  The unique identifier of the object format in the
            DataONE Object Format Vocabulary. The identifier should comply with
            DataONE Identifier rules, i.e. no whitespace, only UTF-8 or US-ASCII
            printable characters.
     * 
     * @param formatId
     */
    public void setFormatId(ObjectFormatIdentifier formatId) {
        this.formatId = formatId;
    }

    /** 
     * Get the 'formatName' element value. For objects that are typed using a Document Type
            Definition, this lists the well-known and accepted named version of
            the DTD. In other cases, an appropriately unambiguous descriptive
            name should be chosen.
     * 
     * @return value
     */
    public String getFormatName() {
        return formatName;
    }

    /** 
     * Set the 'formatName' element value. For objects that are typed using a Document Type
            Definition, this lists the well-known and accepted named version of
            the DTD. In other cases, an appropriately unambiguous descriptive
            name should be chosen.
     * 
     * @param formatName
     */
    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    /** 
     * Get the 'formatType' element value. A string field indicating whether or not this
            format is :term:`science data` (*DATA*), :term:`science metadata`
            (*METADATA*) or a :term:`resource map` (*RESOURCE*). If the format
            is a self-describing data format that includes science metadata,
            then the field should also be set to science metadata.
            
     * 
     * @return value
     */
    public String getFormatType() {
        return formatType;
    }

    /** 
     * Set the 'formatType' element value. A string field indicating whether or not this
            format is :term:`science data` (*DATA*), :term:`science metadata`
            (*METADATA*) or a :term:`resource map` (*RESOURCE*). If the format
            is a self-describing data format that includes science metadata,
            then the field should also be set to science metadata.
            
     * 
     * @param formatType
     */
    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }
}
