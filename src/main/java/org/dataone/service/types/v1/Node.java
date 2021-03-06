
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
/** 
 * A set of values that describe a member or coordinating
 node, its Internet location, and the services it supports. Several nodes
 may exist on a single physical device or hostname. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Node">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:NodeReference" name="identifier" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="name" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="description" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="baseURL" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="ns:Services" name="services" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:Synchronization" name="synchronization" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:NodeReplicationPolicy" name="nodeReplicationPolicy" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:Ping" name="ping" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:Subject" name="subject" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:Subject" name="contactSubject" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:boolean" use="required" name="replicate"/>
 *   &lt;xs:attribute type="xs:boolean" use="required" name="synchronize"/>
 *   &lt;xs:attribute type="ns:NodeType" use="required" name="type"/>
 *   &lt;xs:attribute type="ns:NodeState" use="required" name="state"/>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Node", propOrder = {
    "identifier",
    "name",
    "description",
    "baseURL",
    "services",
    "synchronization",
    "nodeReplicationPolicy",
    "ping",
    "subject",
    "contactSubject"
})
@XmlRootElement(name = "node")
public class Node implements Serializable {

    @XmlElement(required = true)
    protected NodeReference identifier;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String baseURL;
    protected Services services;
    protected Synchronization synchronization;
    protected NodeReplicationPolicy nodeReplicationPolicy;
    protected Ping ping;
    protected List<Subject> subject = new ArrayList<Subject>();
    @XmlElement(required = true)
    protected List<Subject> contactSubject  = new ArrayList<Subject>();
    @XmlAttribute(name = "replicate", required = true)
    protected boolean replicate;
    @XmlAttribute(name = "synchronize", required = true)
    protected boolean synchronize;
    @XmlAttribute(name = "type", required = true)
    protected NodeType type;
    @XmlAttribute(name = "state", required = true)
    protected NodeState state;


    /** 
     * Get the 'identifier' element value. A unique identifier for the node of the form 
            ``urn:node:NODEID`` where NODEID is the node specific identifier. 
            This value MUST NOT change for future implementations of the 
            same node, whereas the *baseURL* may change in the future. 
            
     * 
     * @return value
     */
    public NodeReference getIdentifier() {
        return identifier;
    }

    /** 
     * Set the 'identifier' element value. A unique identifier for the node of the form 
            ``urn:node:NODEID`` where NODEID is the node specific identifier. 
            This value MUST NOT change for future implementations of the 
            same node, whereas the *baseURL* may change in the future. 
            
     * 
     * @param identifier
     */
    public void setIdentifier(NodeReference identifier) {
        this.identifier = identifier;
    }

    /** 
     * Get the 'name' element value. A human readable name of the Node. This name can
            be used as a label in many systems to represent the node, and thus
            should be short, but understandable. 
     * 
     * @return value
     */
    public String getName() {
        return name;
    }

    /** 
     * Set the 'name' element value. A human readable name of the Node. This name can
            be used as a label in many systems to represent the node, and thus
            should be short, but understandable. 
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Get the 'description' element value. Description of a Node, explaining the community it
            serves and other relevant information about the node, such as what
            content is maintained by this node and any other free style notes.
            
     * 
     * @return value
     */
    public String getDescription() {
        return description;
    }

    /** 
     * Set the 'description' element value. Description of a Node, explaining the community it
            serves and other relevant information about the node, such as what
            content is maintained by this node and any other free style notes.
            
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 
     * Get the 'baseURL' element value. The base URL of the node, indicating the
             protocol, fully qualified domain name, and path to the implementing
             service, excluding the version of the API. e.g.
             ``https://server.example.edu/app/d1/mn`` rather than
             ``https://server.example.edu/app/d1/mn/v1``
     * 
     * @return value
     */
    public String getBaseURL() {
        return baseURL;
    }

    /** 
     * Set the 'baseURL' element value. The base URL of the node, indicating the
             protocol, fully qualified domain name, and path to the implementing
             service, excluding the version of the API. e.g.
             ``https://server.example.edu/app/d1/mn`` rather than
             ``https://server.example.edu/app/d1/mn/v1``
     * 
     * @param baseURL
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    /** 
     * Get the 'services' element value. A list of services that are provided by this node.
            Used in node descriptions so that nodes can provide metadata about
            each service they implement and support.
     * 
     * @return value
     */
    public Services getServices() {
        return services;
    }

    /** 
     * Set the 'services' element value. A list of services that are provided by this node.
            Used in node descriptions so that nodes can provide metadata about
            each service they implement and support.
     * 
     * @param services
     */
    public void setServices(Services services) {
        this.services = services;
    }

    /** 
     * Get the 'synchronization' element value. Configuration information for the process by which
              content is harvested from Member Nodes to Coordinating Nodes. This
              includes the schedule on which harvesting should occur, and metadata
              about the last synchronization attempts for the
              node.
     * 
     * @return value
     */
    public Synchronization getSynchronization() {
        return synchronization;
    }

    /** 
     * Set the 'synchronization' element value. Configuration information for the process by which
              content is harvested from Member Nodes to Coordinating Nodes. This
              includes the schedule on which harvesting should occur, and metadata
              about the last synchronization attempts for the
              node.
     * 
     * @param synchronization
     */
    public void setSynchronization(Synchronization synchronization) {
        this.synchronization = synchronization;
    }

    /** 
     * Get the 'nodeReplicationPolicy' element value. The replication policy for this node that expresses
              constraints on object size, total objects, source nodes, and object
              format types. A node may want to restrict replication from only
              certain peer nodes, may have file size limits, total allocated size
              limits, or may want to focus on being a replica target for
              domain-specific object formats.
     * 
     * @return value
     */
    public NodeReplicationPolicy getNodeReplicationPolicy() {
        return nodeReplicationPolicy;
    }

    /** 
     * Set the 'nodeReplicationPolicy' element value. The replication policy for this node that expresses
              constraints on object size, total objects, source nodes, and object
              format types. A node may want to restrict replication from only
              certain peer nodes, may have file size limits, total allocated size
              limits, or may want to focus on being a replica target for
              domain-specific object formats.
     * 
     * @param nodeReplicationPolicy
     */
    public void setNodeReplicationPolicy(
            NodeReplicationPolicy nodeReplicationPolicy) {
        this.nodeReplicationPolicy = nodeReplicationPolicy;
    }

    /** 
     * Get the 'ping' element value. Stored results from the :func:`MNCore.ping` and
             :func:`CNCore.ping` methods.
     * 
     * @return value
     */
    public Ping getPing() {
        return ping;
    }

    /** 
     * Set the 'ping' element value. Stored results from the :func:`MNCore.ping` and
             :func:`CNCore.ping` methods.
     * 
     * @param ping
     */
    public void setPing(Ping ping) {
        this.ping = ping;
    }

    /** 
     * Get the list of 'subject' element items. The :term:`Subject` of this node, which can be
            repeated as needed. The *Node.subject* represents the identifier of
            the node that would be found in X.509 certificates used to securely
            communicate with this node. Thus, it is an :term:`X.509
            Distinguished Name` that applies to the host on which the Node is
            operating. When (and if) this hostname changes the new subject for
            the node would be added to the Node to track the subject that has
            been used in various access control rules over time.
            
     * 
     * @return list
     */
    public List<Subject> getSubjectList() {
        return subject;
    }
    /* Same as getSubjectList, but for use with Serializer
       The method will produce a null return if an empty
       subject array has been created, but nothing added
       https://redmine.dataone.org/issues/7422
    */
        public List<Subject> grabSubjectListNullIfEmpty() {
        if (subject != null && subject.isEmpty()) {
                return null;
        }
        return subject;
    }
    /** 
     * Set the list of 'subject' element items. The :term:`Subject` of this node, which can be
            repeated as needed. The *Node.subject* represents the identifier of
            the node that would be found in X.509 certificates used to securely
            communicate with this node. Thus, it is an :term:`X.509
            Distinguished Name` that applies to the host on which the Node is
            operating. When (and if) this hostname changes the new subject for
            the node would be added to the Node to track the subject that has
            been used in various access control rules over time.
            
     * 
     * @param list
     */
    public void setSubjectList(List<Subject> list) {
        subject = list;
    }

    /** 
     * Get the number of 'subject' element items.
     * @return count
     */
    public int sizeSubjectList() {
        if (subject == null) {
            subject = new ArrayList<Subject>();
        }
        return subject.size();
    }

    /** 
     * Add a 'subject' element item.
     * @param item
     */
    public void addSubject(Subject item) {
        if (subject == null) {
            subject = new ArrayList<Subject>();
        }
        subject.add(item);
    }

    /** 
     * Get 'subject' element item by position.
     * @return item
     * @param index
     */
    public Subject getSubject(int index) {
        if (subject == null) {
            subject = new ArrayList<Subject>();
        }
        return subject.get(index);
    }

    /** 
     * Remove all 'subject' element items.
     */
    public void clearSubjectList() {
        if (subject == null) {
            subject = new ArrayList<Subject>();
        }
        subject.clear();
    }

    /** 
     * Get the list of 'contactSubject' element items. The appropriate person or group to contact
            regarding the disposition, management, and status of this Member
            Node. The *Node.contactSubject* is an :term:`X.509 Distinguished
            Name` for a person or group that can be used to look up current
            contact details (e.g., name, email address) for the contact in the
            DataONE Identity service. DataONE uses the *contactSubject* to
            provide notices of interest to DataONE nodes, including information
            such as policy changes, maintenance updates, node outage
            notifications, among other information useful for administering a
            node. Each node that is registered with DataONE must provide at
            least one *contactSubject* that has been :term:`verified` with
            DataONE. 
     * 
     * @return list
     */
    public List<Subject> getContactSubjectList() {
        return contactSubject;
    }

    /** 
     * Set the list of 'contactSubject' element items. The appropriate person or group to contact
            regarding the disposition, management, and status of this Member
            Node. The *Node.contactSubject* is an :term:`X.509 Distinguished
            Name` for a person or group that can be used to look up current
            contact details (e.g., name, email address) for the contact in the
            DataONE Identity service. DataONE uses the *contactSubject* to
            provide notices of interest to DataONE nodes, including information
            such as policy changes, maintenance updates, node outage
            notifications, among other information useful for administering a
            node. Each node that is registered with DataONE must provide at
            least one *contactSubject* that has been :term:`verified` with
            DataONE. 
     * 
     * @param list
     */
    public void setContactSubjectList(List<Subject> list) {
        contactSubject = list;
    }

    /** 
     * Get the number of 'contactSubject' element items.
     * @return count
     */
    public int sizeContactSubjectList() {
        if (contactSubject == null) {
            contactSubject = new ArrayList<Subject>();
        }
        return contactSubject.size();
    }

    /** 
     * Add a 'contactSubject' element item.
     * @param item
     */
    public void addContactSubject(Subject item) {
        if (contactSubject == null) {
            contactSubject = new ArrayList<Subject>();
        }
        contactSubject.add(item);
    }

    /** 
     * Get 'contactSubject' element item by position.
     * @return item
     * @param index
     */
    public Subject getContactSubject(int index) {
        if (contactSubject == null) {
            contactSubject = new ArrayList<Subject>();
        }
        return contactSubject.get(index);
    }

    /** 
     * Remove all 'contactSubject' element items.
     */
    public void clearContactSubjectList() {
        if (contactSubject == null) {
            contactSubject = new ArrayList<Subject>();
        }
        contactSubject.clear();
    }

    /** 
     * Get the 'replicate' attribute value. Set to *true* if the node is willing to be a
          :term:`replication target`, otherwise *false*.
     * 
     * @return value
     */
    public boolean isReplicate() {
        return replicate;
    }

    /** 
     * Set the 'replicate' attribute value. Set to *true* if the node is willing to be a
          :term:`replication target`, otherwise *false*.
     * 
     * @param replicate
     */
    public void setReplicate(boolean replicate) {
        this.replicate = replicate;
    }

    /** 
     * Get the 'synchronize' attribute value. Set to *true* if the node should be
          :term:`synchronized` by a Coordinating Node, otherwise
     *false*.
     * 
     * @return value
     */
    public boolean isSynchronize() {
        return synchronize;
    }

    /** 
     * Set the 'synchronize' attribute value. Set to *true* if the node should be
          :term:`synchronized` by a Coordinating Node, otherwise
     *false*.
     * 
     * @param synchronize
     */
    public void setSynchronize(boolean synchronize) {
        this.synchronize = synchronize;
    }

    /** 
     * Get the 'type' attribute value. The type of the node (Coordinating, Member), chosen 
          from the :class:`Types.NodeType` type.
     * 
     * @return value
     */
    public NodeType getType() {
        return type;
    }

    /** 
     * Set the 'type' attribute value. The type of the node (Coordinating, Member), chosen 
          from the :class:`Types.NodeType` type.
     * 
     * @param type
     */
    public void setType(NodeType type) {
        this.type = type;
    }

    /** 
     * Get the 'state' attribute value. The state of the node (*up*, *down*), chosen from
          the :class:`Types.NodeState` type.
     * 
     * @return value
     */
    public NodeState getState() {
        return state;
    }

    /** 
     * Set the 'state' attribute value. The state of the node (*up*, *down*), chosen from
          the :class:`Types.NodeState` type.
     * 
     * @param state
     */
    public void setState(NodeState state) {
        this.state = state;
    }
}
