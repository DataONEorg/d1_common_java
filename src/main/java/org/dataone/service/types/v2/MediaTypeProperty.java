
package org.dataone.service.types.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
/** 
 * Additional optional properties for MediaType as 
 described by IANA.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="MediaTypeProperty">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string">
 *       &lt;xs:attribute type="xs:string" use="required" name="name"/>
 *     &lt;/xs:extension>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MediaTypeProperty", propOrder = {
    "value"
})
@XmlRootElement(name = "mediaTypeProperty")
public class MediaTypeProperty implements Serializable {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "name", required = true)
    protected String name;

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
     * Get the 'name' attribute value. The property name for this entry. 
                  
     * 
     * @return value
     */
    public String getName() {
        return name;
    }

    /** 
     * Set the 'name' attribute value. The property name for this entry. 
                  
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
