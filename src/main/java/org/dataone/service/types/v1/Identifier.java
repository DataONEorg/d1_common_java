
package org.dataone.service.types.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/** 
 * An :term:`identifier` (:term:`PID`) in the DataONE
 system that is used to uniquely and globally identify an object.
 Identifiers can not be reused once assigned. Identifiers can not be 
 deleted from the DataONE system.Identifiers are represented by a Unicode 
 string of printable characters, excluding :term:`whitespace`. All 
 representations of identifiers must be encoded in 7-bit ASCII or 
 UTF-8.Identifiers have a maximum length of 800 characters,
 and a variety of other properties designed for preservation and
 longevity. Some discussion on this is described in the `PID
 documentation`_ and in decision `ticket 577`_. .. _ticket 577: https://redmine.dataone.org/issues/577
 .. _PID documentation: http://purl.dataone.org/architecture/design/PIDs.html

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Identifier">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string"/>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Identifier", propOrder = {
    "value"
})
@XmlRootElement( name = "identifier" )
public class Identifier implements Serializable, Comparable
{
    private static final long serialVersionUID = 10000000;

    @XmlValue
    protected String value;

    /** 
     * Get the 'Identifier' complexType value.
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /** 
     * Set the 'Identifier' complexType value.
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** 
     * 1 value 2 Value 3 String 4 (java.lang.String) 5 Identifier  value is a string, override equals of Identifier.
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass())
            return false;
        Identifier otherIdentifier = (Identifier) other;
        if (value == null) {
            if (otherIdentifier.getValue() == null) {
                return true;
            } else {
                return false;
            }
        }
        return value.equals(otherIdentifier.getValue());
    }

    /** 
     * return the hashcode of Identifier's string value.
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
        Identifier otherIdentifier = (Identifier) other;
        return value.compareTo(otherIdentifier.getValue());
    }
}
