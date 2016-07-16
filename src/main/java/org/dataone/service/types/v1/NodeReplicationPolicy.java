
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
/** 
 * The overall replication policy for the node that
 expresses constraints on object size, total objects, source nodes, and
 object format types. A node may choose to restrict replication from only
 certain peer nodes, may have file size limits, total allocated size
 limits, or may want to focus on being a :term:`replication target` for
 domain-specific object formats.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="NodeReplicationPolicy">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:long" name="maxObjectSize" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:long" name="spaceAllocated" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="ns:NodeReference" name="allowedNode" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:ObjectFormatIdentifier" name="allowedObjectFormat" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NodeReplicationPolicy", propOrder = {
    "maxObjectSize",
    "spaceAllocated",
    "allowedNode",
    "allowedObjectFormat"
})
@XmlRootElement(name = "nodeReplicationPolicy")
public class NodeReplicationPolicy implements Serializable
{
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger maxObjectSize;
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger spaceAllocated;
    protected List<NodeReference> allowedNode = new ArrayList<NodeReference>();
    // TODO: check if List<ObjectFormatIDentifier> works (jaxb generated had List<String>)
    protected List<ObjectFormatIdentifier> allowedObjectFormat = new ArrayList<ObjectFormatIdentifier>();

    private static final long serialVersionUID = 10000000;

    /** 
     * Get the 'maxObjectSize' element value. An optional statement of the maximum size in octets 
            (8-bit bytes) of objects this node is willing to accept for
            replication.
     * 
     * @return value
     */
    public BigInteger getMaxObjectSize() {
        return maxObjectSize;
    }

    /** 
     * Set the 'maxObjectSize' element value. An optional statement of the maximum size in octets 
            (8-bit bytes) of objects this node is willing to accept for
            replication.
     * 
     * @param maxObjectSize
     */
    public void setMaxObjectSize(BigInteger maxObjectSize) {
        this.maxObjectSize = maxObjectSize;
    }

    /** 
     * Get the 'spaceAllocated' element value. An optional statement of the total space in bytes
            allocated for replication object storage on this
            node.
     * 
     * @return value
     */
    public BigInteger getSpaceAllocated() {
        return spaceAllocated;
    }

    /** 
     * Set the 'spaceAllocated' element value. An optional statement of the total space in bytes
            allocated for replication object storage on this
            node.
     * 
     * @param spaceAllocated
     */
    public void setSpaceAllocated(BigInteger spaceAllocated) {
        this.spaceAllocated = spaceAllocated;
    }

    /** 
     * Get the list of 'allowedNode' element items. An optional, repeatable statement of a peer source
            node from which this node is willing to replicate content, expressed
            as a :class:`Types.NodeReference`.
     * 
     * @return list
     */
    public List<NodeReference> getAllowedNodeList() {
        return allowedNode;
    }

    /** 
     * Set the list of 'allowedNode' element items. An optional, repeatable statement of a peer source
            node from which this node is willing to replicate content, expressed
            as a :class:`Types.NodeReference`.
     * 
     * @param list
     */
    public void setAllowedNodeList(List<NodeReference> list) {
        allowedNode = list;
    }

    /** 
     * Get the number of 'allowedNode' element items.
     * @return count
     */
    public int sizeAllowedNodeList() {
        if (allowedNode == null) {
            allowedNode = new ArrayList<NodeReference>();
        }
        return allowedNode.size();
    }

    /** 
     * Add a 'allowedNode' element item.
     * @param item
     */
    public void addAllowedNode(NodeReference item) {
        if (allowedNode == null) {
            allowedNode = new ArrayList<NodeReference>();
        }
        allowedNode.add(item);
    }

    /** 
     * Get 'allowedNode' element item by position.
     * @return item
     * @param index
     */
    public NodeReference getAllowedNode(int index) {
        if (allowedNode == null) {
            allowedNode = new ArrayList<NodeReference>();
        }
        return allowedNode.get(index);
    }

    /** 
     * Remove all 'allowedNode' element items.
     */
    public void clearAllowedNodeList() {
        if (allowedNode == null) {
            allowedNode = new ArrayList<NodeReference>();
        }
        allowedNode.clear();
    }

    /** 
     * Get the list of 'allowedObjectFormat' element items. An optional, repeatable statement of an object
            format that this node is willing to replicate, expressed as a
            :class:`Types.ObjectFormatIdentifier`.
     * 
     * @return list
     */
    public List<ObjectFormatIdentifier> getAllowedObjectFormatList() {
        return allowedObjectFormat;
    }

    /** 
     * Set the list of 'allowedObjectFormat' element items. An optional, repeatable statement of an object
            format that this node is willing to replicate, expressed as a
            :class:`Types.ObjectFormatIdentifier`.
     * 
     * @param list
     */
    public void setAllowedObjectFormatList(List<ObjectFormatIdentifier> list) {
        allowedObjectFormat = list;
    }

    /** 
     * Get the number of 'allowedObjectFormat' element items.
     * @return count
     */
    public int sizeAllowedObjectFormatList() {
        if (allowedObjectFormat == null) {
            allowedObjectFormat = new ArrayList<ObjectFormatIdentifier>();
        }
        return allowedObjectFormat.size();
    }

    /** 
     * Add a 'allowedObjectFormat' element item.
     * @param item
     */
    public void addAllowedObjectFormat(ObjectFormatIdentifier item) {
        if (allowedObjectFormat == null) {
            allowedObjectFormat = new ArrayList<ObjectFormatIdentifier>();
        }
        allowedObjectFormat.add(item);
    }

    /** 
     * Get 'allowedObjectFormat' element item by position.
     * @return item
     * @param index
     */
    public ObjectFormatIdentifier getAllowedObjectFormat(int index) {
        if (allowedObjectFormat == null) {
            allowedObjectFormat = new ArrayList<ObjectFormatIdentifier>();
        }
        return allowedObjectFormat.get(index);
    }

    /** 
     * Remove all 'allowedObjectFormat' element items.
     */
    public void clearAllowedObjectFormatList() {
        if (allowedObjectFormat == null) {
            allowedObjectFormat = new ArrayList<ObjectFormatIdentifier>();
        }
        allowedObjectFormat.clear();
    }
}
