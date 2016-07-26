
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import javax.xml.datatype.XMLGregorianCalendar;
/** 
 * Store results from the :func:`MNCore.ping`
 method.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Ping">
 *   &lt;xs:attribute type="xs:boolean" name="success"/>
 *   &lt;xs:attribute type="xs:dateTime" name="lastSuccess"/>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ping")
@XmlRootElement(name = "ping")
public class Ping implements Serializable {

    @XmlAttribute(name = "success")
    protected Boolean success;
    @XmlAttribute(name = "lastSuccess")
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(org.dataone.service.util.DateMarshallingAdapter.class)
    protected Date lastSuccess;

    private static final long serialVersionUID = 10000000;


    /** 
     * Get the 'success' attribute value. A boolean flag indicating *true* if the node was
          reached by the last :func:`MNCore.ping` or :func:`CNCore.ping` call,
          otherwise *false*.
     * 
     * @return value
     */
    public Boolean getSuccess() {
        return success;
    }

    /** 
     * Set the 'success' attribute value. A boolean flag indicating *true* if the node was
          reached by the last :func:`MNCore.ping` or :func:`CNCore.ping` call,
          otherwise *false*.
     * 
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /** 
     * Get the 'lastSuccess' attribute value. The date time value (UTC) of the last time a
          successful ping was performed.
     * 
     * @return value
     */
    public Date getLastSuccess() {
        return lastSuccess;
    }

    /** 
     * Set the 'lastSuccess' attribute value. The date time value (UTC) of the last time a
          successful ping was performed.
     * 
     * @param lastSuccess
     */
    public void setLastSuccess(Date lastSuccess) {
        this.lastSuccess = lastSuccess;
    }
}
