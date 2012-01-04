
package org.dataone.service.types.v1;

import java.io.Serializable;
import org.dataone.service.types.SubjectBase;

/** 
 * An identifier for a Person (user), Group,
 Organization, or System.The :term:`Subject` is a string that provides a formal
 name to identify a user or group in the DataONE Identity Management
 Service. The *subject* is represented by a unique, persistent,
 non-reassignable identifier string that follows the same constraints as
 :class:`Types.Identifier`.
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
public class Subject extends org.dataone.service.types.SubjectBase implements
     Serializable, Comparable
{
    private static final long serialVersionUID = 10000000;
    private String value;

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
}
