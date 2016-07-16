
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/** 
 *  A list of :class:`Types.Node` entries returned by
 :func:`CNCore.listNodes()`.NodeList is described in
 :mod:`NodeList`.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="NodeList">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:Node" name="node" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NodeList", propOrder = {
    "node"
})
@XmlRootElement(name = "nodeList")
public class NodeList implements Serializable
{
    @XmlElement(required = true)
    protected List<Node> node = new ArrayList<Node>();
    private static final long serialVersionUID = 10000000;


    /** 
     * Get the list of 'node' element items.
     * 
     * @return list
     */
    public List<Node> getNodeList() {
        return node;
    }

    /** 
     * Set the list of 'node' element items.
     * 
     * @param list
     */
    public void setNodeList(List<Node> list) {
        node = list;
    }

    /** 
     * Get the number of 'node' element items.
     * @return count
     */
    public int sizeNodeList() {
        if (node == null) {
            node = new ArrayList<Node>();
        }
        return node.size();
    }

    /** 
     * Add a 'node' element item.
     * @param item
     */
    public void addNode(Node item) {
        if (node == null) {
            node = new ArrayList<Node>();
        }
        node.add(item);
    }

    /** 
     * Get 'node' element item by position.
     * @return item
     * @param index
     */
    public Node getNode(int index) {
        if (node == null) {
            node = new ArrayList<Node>();
        }
        return node.get(index);
    }

    /** 
     * Remove all 'node' element items.
     */
    public void clearNodeList() {
        if (node == null) {
            node = new ArrayList<Node>();
        }
        node.clear();
    }
}
