
package org.dataone.service.types.v1;

import java.io.Serializable;
import javax.security.auth.x500.X500Principal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/** 
 * An identifier for a Person (user), Group,
 Organization, or System.The :term:`Subject` is a string that provides a formal
 name to identify a user or group in the DataONE Identity Management
 Service. The *subject* is represented by a unique, persistent,
 non-reassignable identifier string that follows the same constraints as
 :class:`Types.Identifier`. Subjects are immutable and can not be 
 deleted.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Subject">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string"/>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Subject", propOrder = {
    "value"
})
@XmlRootElement( name = "subject" )
public class Subject implements Serializable, Comparable
{
    private static final long serialVersionUID = 10000000;
    @XmlValue
    protected String value;

    /** 
     * Get the 'Subject' complexType value.
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /** 
     * Set the 'Subject' complexType value.
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** 
     * Value is a string, override equals of Subject.
     * @param other
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        Subject otherSubject = (Subject) other;
        String standardizedOtherSubject = standardizeDN(otherSubject.getValue());
        String standardizedSubject = standardizeDN(value);
        if (standardizedSubject == null) {
            if (standardizedOtherSubject == null) {
                return true;
            } else {
                return false;
            }
        }
        return standardizedSubject.equals(standardizedOtherSubject);
    }

    /** 
     * return the hashcode of Subject's string value.
     * @return int
     */
    @Override
    public int hashCode() {
        if (value == null) return 0;
        String standardizedSubject = standardizeDN(value);
        return standardizedSubject.hashCode();
    }

    /** 
     * Compares order based on the String value of two objects of the same type.
     * @param other
     * @return int
     * @throws ClassCastException 
     */
    @Override
    public int compareTo(Object other) throws ClassCastException {
        Subject otherSubject = (Subject) other;
        String standardizedOtherSubject = standardizeDN(otherSubject.getValue());
        String standardizedSubject = standardizeDN(value);
        return standardizedSubject.compareTo(standardizedOtherSubject);
    }

    /** 
     * Uses X500Principal.RFC2253 format for internal comparison/equality checks
     * @param name the [reasonable] DN representation 
     * @return String the standard D1 representation 
     */
    private String standardizeDN(String name) {
        String standardizedName = null;
        try {
            X500Principal principal = new X500Principal(name);
            standardizedName = principal.getName(X500Principal.RFC2253);
        } catch (IllegalArgumentException e) {
            standardizedName = name;
        }
        return standardizedName;
    }
}
