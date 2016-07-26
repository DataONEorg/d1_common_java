
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.NodeReference;
import org.dataone.service.types.v1.Subject;

/** 
 * Extends :class:`Types.LogEntry` by relaxing the value 
 space for the *event* element.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="LogEntry">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="entryId" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="ns1:Identifier" name="identifier" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="ipAddress" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="userAgent" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="ns1:Subject" name="subject" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="event" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:dateTime" name="dateLogged" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="ns1:NodeReference" name="nodeIdentifier" minOccurs="1" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogEntry", propOrder = {
    "entryId",
    "identifier",
    "ipAddress",
    "userAgent",
    "subject",
    "event",
    "dateLogged",
    "nodeIdentifier"
})
@XmlRootElement(name = "logEntry")
public class LogEntry implements Serializable
{
    private static final long serialVersionUID = 10000000;
    
    @XmlElement(required = true)
    protected String entryId;
    @XmlElement(required = true)
    protected Identifier identifier;
    @XmlElement(required = true)
    protected String ipAddress;
    @XmlElement(required = true)
    protected String userAgent;
    @XmlElement(required = true)
    protected Subject subject;
    @XmlElement(required = true)
    protected String event;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(org.dataone.service.util.DateMarshallingAdapter.class)
    protected Date dateLogged;
    @XmlElement(required = true)
    protected NodeReference nodeIdentifier;


    /** 
     * Get the 'entryId' element value. A unique identifier for this log entry. The
            identifier should be unique for a particular node; This is not drawn
            from the same value space as other identifiers in DataONE, and so is
            not subject to the same restrictions.
     * 
     * @return value
     */
    public String getEntryId() {
        return entryId;
    }

    /** 
     * Set the 'entryId' element value. A unique identifier for this log entry. The
            identifier should be unique for a particular node; This is not drawn
            from the same value space as other identifiers in DataONE, and so is
            not subject to the same restrictions.
     * 
     * @param entryId
     */
    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    /** 
     * Get the 'identifier' element value. The :term:`identifier` of the object that was the
            target of the operation which generated this log entry.
     * 
     * @return value
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /** 
     * Set the 'identifier' element value. The :term:`identifier` of the object that was the
            target of the operation which generated this log entry.
     * 
     * @param identifier
     */
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    /** 
     * Get the 'ipAddress' element value. The IP address, as reported by the service receiving
            the request, of the request origin.
     * 
     * @return value
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /** 
     * Set the 'ipAddress' element value. The IP address, as reported by the service receiving
            the request, of the request origin.
     * 
     * @param ipAddress
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /** 
     * Get the 'userAgent' element value. The user agent of the client making the request, as
            reported in the User-Agent HTTP header.
     * 
     * @return value
     */
    public String getUserAgent() {
        return userAgent;
    }

    /** 
     * Set the 'userAgent' element value. The user agent of the client making the request, as
            reported in the User-Agent HTTP header.
     * 
     * @param userAgent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /** 
     * Get the 'subject' element value. The :term:`Subject` used for making the request.
            This may be the DataONE :term:`public` user if the request is not
            authenticated, otherwise it will be the *subject* of the certificate
            used for authenticating the request.
     * 
     * @return value
     */
    public Subject getSubject() {
        return subject;
    }

    /** 
     * Set the 'subject' element value. The :term:`Subject` used for making the request.
            This may be the DataONE :term:`public` user if the request is not
            authenticated, otherwise it will be the *subject* of the certificate
            used for authenticating the request.
     * 
     * @param subject
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /** 
     * Get the 'event' element value. A non-empty string indicating the type of event 
              logged. A value from the :class:`Types.Event` enumeration is 
              recommended though no longer required for Version 2.x.
            
     * 
     * @return value
     */
    public String getEvent() {
        return event;
    }

    /** 
     * Set the 'event' element value. A non-empty string indicating the type of event 
              logged. A value from the :class:`Types.Event` enumeration is 
              recommended though no longer required for Version 2.x.
            
     * 
     * @param event
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /** 
     * Get the 'dateLogged' element value. A :class:`Types.DateTime` time stamp indicating when
            the event triggering the log message ocurred. Note that all time
            stamps in DataONE are in UTC.
     * 
     * @return value
     */
    public Date getDateLogged() {
        return dateLogged;
    }

    /** 
     * Set the 'dateLogged' element value. A :class:`Types.DateTime` time stamp indicating when
            the event triggering the log message ocurred. Note that all time
            stamps in DataONE are in UTC.
     * 
     * @param dateLogged
     */
    public void setDateLogged(Date dateLogged) {
        this.dateLogged = dateLogged;
    }

    /** 
     * Get the 'nodeIdentifier' element value. The unique identifier for the node where the log
            message was generated.
     * 
     * @return value
     */
    public NodeReference getNodeIdentifier() {
        return nodeIdentifier;
    }

    /** 
     * Set the 'nodeIdentifier' element value. The unique identifier for the node where the log
            message was generated.
     * 
     * @param nodeIdentifier
     */
    public void setNodeIdentifier(NodeReference nodeIdentifier) {
        this.nodeIdentifier = nodeIdentifier;
    }
}
