
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * A set of values that describe a member or coordinating
 node, its Internet location, and the services it supports. Several nodes
 may exist on a single physical device or hostname. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Node">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns1:Node">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="xs:string" name="serviceExtensions" minOccurs="0"/>
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
    private String serviceExtensions;
    private List<Property> propertyList = new ArrayList<Property>();

    /** 
     * Get the 'serviceExtensions' element value. TODO: define the content of this?
     * 
     * @return value
     */
    public String getServiceExtensions() {
        return serviceExtensions;
    }

    /** 
     * Set the 'serviceExtensions' element value. TODO: define the content of this?
     * 
     * @param serviceExtensions
     */
    public void setServiceExtensions(String serviceExtensions) {
        this.serviceExtensions = serviceExtensions;
    }

    /** 
     * Get the list of 'property' element items. Allows additional attributes be added to the Node document as needed
     * 
     * @return list
     */
    public List<Property> getPropertyList() {
        return propertyList;
    }

    /** 
     * Set the list of 'property' element items. Allows additional attributes be added to the Node document as needed
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
