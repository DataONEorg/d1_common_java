
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * A list of services that are provided by a node. Used
 in Node descriptions so that Nodes can provide metadata about each
 service they implement and support. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Services">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:Service" name="service" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Services implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private List<Service> serviceList = new ArrayList<Service>();

    /** 
     * Get the list of 'service' element items.
     * 
     * @return list
     */
    public List<Service> getServiceList() {
        return serviceList;
    }

    /** 
     * Set the list of 'service' element items.
     * 
     * @param list
     */
    public void setServiceList(List<Service> list) {
        serviceList = list;
    }

    /** 
     * Get the number of 'service' element items.
     * @return count
     */
    public int sizeServiceList() {
        if (serviceList == null) {
            serviceList = new ArrayList<Service>();
        }
        return serviceList.size();
    }

    /** 
     * Add a 'service' element item.
     * @param item
     */
    public void addService(Service item) {
        if (serviceList == null) {
            serviceList = new ArrayList<Service>();
        }
        serviceList.add(item);
    }

    /** 
     * Get 'service' element item by position.
     * @return item
     * @param index
     */
    public Service getService(int index) {
        if (serviceList == null) {
            serviceList = new ArrayList<Service>();
        }
        return serviceList.get(index);
    }

    /** 
     * Remove all 'service' element items.
     */
    public void clearServiceList() {
        if (serviceList == null) {
            serviceList = new ArrayList<Service>();
        }
        serviceList.clear();
    }
}
