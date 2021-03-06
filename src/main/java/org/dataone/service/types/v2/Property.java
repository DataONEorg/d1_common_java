
package org.dataone.service.types.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
/** 
 * Additional Property elements can be included to 
 describe the Node in more detail. Some properties will come from 
 controlled vocabularies indicated by the type attribute, while 
 others will be free-form key value pairs.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Property">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string">
 *       &lt;xs:attribute type="xs:string" use="required" name="key"/>
 *       &lt;xs:attribute type="xs:string" use="optional" name="type"/>
 *     &lt;/xs:extension>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Property", propOrder = {
    "value"
})
@XmlRootElement(name = "property")
public class Property implements Serializable {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "key", required = true)
    protected String key;
    @XmlAttribute(name = "type")
    protected String type;

    private static final long serialVersionUID = 10000000;


    /** 
     * Get the extension value.
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /** 
     * Set the extension value.
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** 
     * Get the 'key' attribute value. The property key for this entry. Should be 
                    unique within the Node element.
     * 
     * @return value
     */
    public String getKey() {
        return key;
    }

    /** 
     * Set the 'key' attribute value. The property key for this entry. Should be 
                    unique within the Node element.
     * 
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /** 
     * Get the 'type' attribute value. The optional type for the property. Can be 
                    used to indicate if a controlled vocabulary is used for the
                    property key to better facilitate machine interpretation.
                  
     * 
     * @return value
     */
    public String getType() {
        return type;
    }

    /** 
     * Set the 'type' attribute value. The optional type for the property. Can be 
                    used to indicate if a controlled vocabulary is used for the
                    property key to better facilitate machine interpretation.
                  
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
