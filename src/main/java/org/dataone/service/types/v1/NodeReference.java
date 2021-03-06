
package org.dataone.service.types.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/** 
 * A unique identifier for a DataONE Node. The
 *NodeReference* must be unique across nodes, and must always be
 assigned to one Member or Coordinating Node instance even in the event of 
 the *BaseURL* or other characteristics changing.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="NodeReference">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string"/>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NodeReference", propOrder = {
    "value"
})
@XmlRootElement(name = "nodeReference")
public class NodeReference implements Serializable, Comparable
{
    private static final long serialVersionUID = 10000000;
    @XmlValue
    protected String value;

    /** 
     * Get the 'NodeReference' complexType value.
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /** 
     * Set the 'NodeReference' complexType value.
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** 
     * 1 value 2 Value 3 String 4 (java.lang.String) 5 NodeReference  value is a string, override equals of NodeReference.
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass())
            return false;
        NodeReference otherNodeReference = (NodeReference) other;
        if (value == null) {
            if (otherNodeReference.getValue() == null) {
                return true;
            } else {
                return false;
            }
        }
        return value.equals(otherNodeReference.getValue());
    }

    /** 
     * return the hashcode of NodeReference's string value.
     * @return int
     */
    @Override
    public int hashCode() {
        if (value == null) return 0;
        return value.hashCode();
    }

    /** 
     * Compares order based on the String value of two objects of the same type.
     * @param other
     * @return int
     * @throws ClassCastException 
     */
    @Override
    public int compareTo(Object other) throws ClassCastException {
        NodeReference otherNodeReference = (NodeReference) other;
        return value.compareTo(otherNodeReference.getValue());
    }
}
