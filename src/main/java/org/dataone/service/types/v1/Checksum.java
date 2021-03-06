
package org.dataone.service.types.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/** 
 * Represents the value of a computed :term:`checksum`
 expressed as a hexadecimal formatted version of the message digest. Note
 that these hex values should be treated as case-insensitive strings, in
 that leading zeros must be preserved, and digests can use a mixture of
 upper and lower case letters to represent the hex values. Comparison
 algorithms MUST be able to handle any variant of these representations
 (e.g., by performing a case-insensitive string match on hex digests from
 the same algorithm).
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Checksum">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string">
 *       &lt;xs:attribute type="xs:string" use="required" name="algorithm"/>
 *     &lt;/xs:extension>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Checksum", propOrder = {
    "value"
})
@XmlRootElement(name = "checksum")
public class Checksum implements Serializable
{
    @XmlValue
    protected String value;
    @XmlAttribute(name = "algorithm", required = true)
    protected String algorithm;
    
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
     * Get the 'algorithm' attribute value.
     * 
     * @return value
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /** 
     * Set the 'algorithm' attribute value.
     * 
     * @param algorithm
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
