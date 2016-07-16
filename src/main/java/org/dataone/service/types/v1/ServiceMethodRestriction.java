
package org.dataone.service.types.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/** 
 * Describes an optional restriction policy for a given
 method. If this element exists for a service method, its use is
 restricted, and only :term:`Subjects` listed in the list are allowed to
 invoke the method named in the *methodName*
 attribute.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ServiceMethodRestriction">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns:SubjectList">
 *       &lt;xs:attribute type="xs:string" use="required" name="methodName"/>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceMethodRestriction")
@XmlRootElement(name = "serviceMethodRestriction")
public class ServiceMethodRestriction extends SubjectList implements Serializable
{

    @XmlAttribute(name = "methodName", required = true)
    protected String methodName;

    private static final long serialVersionUID = 10000000;

    /** 
     * Get the 'methodName' attribute value. The formal name of the method in this *Service*
            which is to be restricted.
     * 
     * @return value
     */
    public String getMethodName() {
        return methodName;
    }

    /** 
     * Set the 'methodName' attribute value. The formal name of the method in this *Service*
            which is to be restricted.
     * 
     * @param methodName
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
