
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * Extends the Version 1.x :class:`Types.Node` by adding 
 an optional unbounded parameter entry providing additional simple 
 metadata relevant to a Node. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Node">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns1:Node">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns:Property" name="property" minOccurs="0" maxOccurs="unbounded"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Node extends org.dataone.service.types.v1.Node implements
    Serializable
{
    private static final long serialVersionUID = 10000000;
    private List<Property> propertyList = new ArrayList<Property>();

    /** 
     * Get the list of 'property' element items. Allows additional attributes be added to the 
                  Node document as needed.
     * 
     * @return list
     */
    public List<Property> getPropertyList() {
        return propertyList;
    }

    /** 
     * Set the list of 'property' element items. Allows additional attributes be added to the 
                  Node document as needed.
     * 
     * @param list
     */
    public void setPropertyList(List<Property> list) {
        propertyList = list;
    }

    /** 
     * Get the number of 'property' element items.
     * @return count
     */
    public int sizePropertyList() {
        if (propertyList == null) {
            propertyList = new ArrayList<Property>();
        }
        return propertyList.size();
    }

    /** 
     * Add a 'property' element item.
     * @param item
     */
    public void addProperty(Property item) {
        if (propertyList == null) {
            propertyList = new ArrayList<Property>();
        }
        propertyList.add(item);
    }

    /** 
     * Get 'property' element item by position.
     * @return item
     * @param index
     */
    public Property getProperty(int index) {
        if (propertyList == null) {
            propertyList = new ArrayList<Property>();
        }
        return propertyList.get(index);
    }

    /** 
     * Remove all 'property' element items.
     */
    public void clearPropertyList() {
        if (propertyList == null) {
            propertyList = new ArrayList<Property>();
        }
        propertyList.clear();
    }
}
