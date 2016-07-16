
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Services", propOrder = {
    "service"
})
@XmlRootElement(name = "services")
public class Services implements Serializable {

    @XmlElement(required = true)
    protected List<Service> service = new ArrayList<Service>();

    private static final long serialVersionUID = 10000000;


    /** 
     * Get the list of 'service' element items.
     * 
     * @return list
     */
    public List<Service> getServiceList() {
        return service;
    }

    /** 
     * Set the list of 'service' element items.
     * 
     * @param list
     */
    public void setServiceList(List<Service> list) {
        service = list;
    }

    /** 
     * Get the number of 'service' element items.
     * @return count
     */
    public int sizeServiceList() {
        if (service == null) {
            service = new ArrayList<Service>();
        }
        return service.size();
    }

    /** 
     * Add a 'service' element item.
     * @param item
     */
    public void addService(Service item) {
        if (service == null) {
            service = new ArrayList<Service>();
        }
        service.add(item);
    }

    /** 
     * Get 'service' element item by position.
     * @return item
     * @param index
     */
    public Service getService(int index) {
        if (service == null) {
            service = new ArrayList<Service>();
        }
        return service.get(index);
    }

    /** 
     * Remove all 'service' element items.
     */
    public void clearServiceList() {
        if (service == null) {
            service = new ArrayList<Service>();
        }
        service.clear();
    }
}
