
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
/** 
 * Replica information that describes the existence of a
 replica of some object managed by the DataONE infrastructure, and its 
 status.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Replica">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:NodeReference" name="replicaMemberNode"/>
 *     &lt;xs:element type="ns:ReplicationStatus" name="replicationStatus"/>
 *     &lt;xs:element type="xs:dateTime" name="replicaVerified"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Replica", propOrder = {
    "replicaMemberNode",
    "replicationStatus",
    "replicaVerified"
})
@XmlRootElement(name = "replica")
public class Replica implements Serializable {

    @XmlElement(required = true)
    protected NodeReference replicaMemberNode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ReplicationStatus replicationStatus;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date replicaVerified;

    private static final long serialVersionUID = 10000000;

    /** 
     * Get the 'replicaMemberNode' element value. A reference to the Member Node that houses this
            replica, regardless of whether it has arrived at the Member Node or
            not. See *replicationStatus* to determine if the replica is
            completely transferred. 
     * 
     * @return value
     */
    public NodeReference getReplicaMemberNode() {
        return replicaMemberNode;
    }

    /** 
     * Set the 'replicaMemberNode' element value. A reference to the Member Node that houses this
            replica, regardless of whether it has arrived at the Member Node or
            not. See *replicationStatus* to determine if the replica is
            completely transferred. 
     * 
     * @param replicaMemberNode
     */
    public void setReplicaMemberNode(NodeReference replicaMemberNode) {
        this.replicaMemberNode = replicaMemberNode;
    }

    /** 
     * Get the 'replicationStatus' element value.  The current status of this replica, indicating
            the stage of replication process for the object. Only *completed*
            replicas should be considered as available. 
     * 
     * @return value
     */
    public ReplicationStatus getReplicationStatus() {
        return replicationStatus;
    }

    /** 
     * Set the 'replicationStatus' element value.  The current status of this replica, indicating
            the stage of replication process for the object. Only *completed*
            replicas should be considered as available. 
     * 
     * @param replicationStatus
     */
    public void setReplicationStatus(ReplicationStatus replicationStatus) {
        this.replicationStatus = replicationStatus;
    }

    /** 
     * Get the 'replicaVerified' element value.  The last date and time on which the integrity of
            a replica was verified by the coordinating node. Verification occurs
            by checking that the checksum of the stored object matches the
            checksum recorded for the object in the system
            metadata.
     * 
     * @return value
     */
    public Date getReplicaVerified() {
        return replicaVerified;
    }

    /** 
     * Set the 'replicaVerified' element value.  The last date and time on which the integrity of
            a replica was verified by the coordinating node. Verification occurs
            by checking that the checksum of the stored object matches the
            checksum recorded for the object in the system
            metadata.
     * 
     * @param replicaVerified
     */
    public void setReplicaVerified(Date replicaVerified) {
        this.replicaVerified = replicaVerified;
    }
}
