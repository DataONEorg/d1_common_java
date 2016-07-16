
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/** 
 * The *ReplicationPolicy* for an object defines if
 replication should be attempted for this object, and if so, how many
 replicas should be maintained. It also permits specification of
 preferred and blocked nodes as potential replication targets.

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ReplicationPolicy">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:NodeReference" name="preferredMemberNode" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:NodeReference" name="blockedMemberNode" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:boolean" name="replicationAllowed"/>
 *   &lt;xs:attribute type="xs:int" name="numberReplicas"/>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReplicationPolicy", propOrder = {
    "preferredMemberNode",
    "blockedMemberNode"
})
@XmlRootElement(name = "replicationPolicy")
public class ReplicationPolicy implements Serializable {

    protected List<NodeReference> preferredMemberNode = new ArrayList<NodeReference>();
    protected List<NodeReference> blockedMemberNode = new ArrayList<NodeReference>();
    @XmlAttribute(name = "replicationAllowed")
    protected Boolean replicationAllowed;
    @XmlAttribute(name = "numberReplicas")
    protected Integer numberReplicas;

    private static final long serialVersionUID = 10000000;


    /** 
     * Get the list of 'preferredMemberNode' element items. Preferred Nodes are utilized over other nodes as
            replication targets, up to the number of replicas requested. If
            preferred nodes are unavailable, or if insufficient nodes are listed
            as preferred to meet the requested number of replicas, then the
            Coordinating Nodes will pick additional replica nodes for the
            content. 
     * 
     * @return list
     */
    public List<NodeReference> getPreferredMemberNodeList() {
        return preferredMemberNode;
    }

    /** 
     * Set the list of 'preferredMemberNode' element items. Preferred Nodes are utilized over other nodes as
            replication targets, up to the number of replicas requested. If
            preferred nodes are unavailable, or if insufficient nodes are listed
            as preferred to meet the requested number of replicas, then the
            Coordinating Nodes will pick additional replica nodes for the
            content. 
     * 
     * @param list
     */
    public void setPreferredMemberNodeList(List<NodeReference> list) {
        preferredMemberNode = list;
    }

    /** 
     * Get the number of 'preferredMemberNode' element items.
     * @return count
     */
    public int sizePreferredMemberNodeList() {
        if (preferredMemberNode == null) {
            preferredMemberNode = new ArrayList<NodeReference>();
        }
        return preferredMemberNode.size();
    }

    /** 
     * Add a 'preferredMemberNode' element item.
     * @param item
     */
    public void addPreferredMemberNode(NodeReference item) {
        if (preferredMemberNode == null) {
            preferredMemberNode = new ArrayList<NodeReference>();
        }
        preferredMemberNode.add(item);
    }

    /** 
     * Get 'preferredMemberNode' element item by position.
     * @return item
     * @param index
     */
    public NodeReference getPreferredMemberNode(int index) {
        if (preferredMemberNode == null) {
            preferredMemberNode = new ArrayList<NodeReference>();
        }
        return preferredMemberNode.get(index);
    }

    /** 
     * Remove all 'preferredMemberNode' element items.
     */
    public void clearPreferredMemberNodeList() {
        if (preferredMemberNode == null) {
            preferredMemberNode = new ArrayList<NodeReference>();
        }
        preferredMemberNode.clear();
    }

    /** 
     * Get the list of 'blockedMemberNode' element items. The object MUST never be replicated to nodes
            listed as *blockedMemberNodes*. Where there is a conflict between a
     *preferredMemberNode* and a *blockedMemberNode* entry, the
     *blockedMemberNode* entry prevails. 
     * 
     * @return list
     */
    public List<NodeReference> getBlockedMemberNodeList() {
        return blockedMemberNode;
    }

    /** 
     * Set the list of 'blockedMemberNode' element items. The object MUST never be replicated to nodes
            listed as *blockedMemberNodes*. Where there is a conflict between a
     *preferredMemberNode* and a *blockedMemberNode* entry, the
     *blockedMemberNode* entry prevails. 
     * 
     * @param list
     */
    public void setBlockedMemberNodeList(List<NodeReference> list) {
        blockedMemberNode = list;
    }

    /** 
     * Get the number of 'blockedMemberNode' element items.
     * @return count
     */
    public int sizeBlockedMemberNodeList() {
        if (blockedMemberNode == null) {
            blockedMemberNode = new ArrayList<NodeReference>();
        }
        return blockedMemberNode.size();
    }

    /** 
     * Add a 'blockedMemberNode' element item.
     * @param item
     */
    public void addBlockedMemberNode(NodeReference item) {
        if (blockedMemberNode == null) {
            blockedMemberNode = new ArrayList<NodeReference>();
        }
        blockedMemberNode.add(item);
    }

    /** 
     * Get 'blockedMemberNode' element item by position.
     * @return item
     * @param index
     */
    public NodeReference getBlockedMemberNode(int index) {
        if (blockedMemberNode == null) {
            blockedMemberNode = new ArrayList<NodeReference>();
        }
        return blockedMemberNode.get(index);
    }

    /** 
     * Remove all 'blockedMemberNode' element items.
     */
    public void clearBlockedMemberNodeList() {
        if (blockedMemberNode == null) {
            blockedMemberNode = new ArrayList<NodeReference>();
        }
        blockedMemberNode.clear();
    }

    /** 
     * Get the 'replicationAllowed' attribute value. A boolean flag indicating if the object should be
          replicated (*true*, default) or not (*false*).
     * 
     * @return value
     */
    public Boolean getReplicationAllowed() {
        return replicationAllowed;
    }

    /** 
     * Set the 'replicationAllowed' attribute value. A boolean flag indicating if the object should be
          replicated (*true*, default) or not (*false*).
     * 
     * @param replicationAllowed
     */
    public void setReplicationAllowed(Boolean replicationAllowed) {
        this.replicationAllowed = replicationAllowed;
    }

    /** 
     * Get the 'numberReplicas' attribute value. An integer indicating the number of replicas
          targeted for this object. Defaults to 3.
     * 
     * @return value
     */
    public Integer getNumberReplicas() {
        return numberReplicas;
    }

    /** 
     * Set the 'numberReplicas' attribute value. An integer indicating the number of replicas
          targeted for this object. Defaults to 3.
     * 
     * @param numberReplicas
     */
    public void setNumberReplicas(Integer numberReplicas) {
        this.numberReplicas = numberReplicas;
    }
}
