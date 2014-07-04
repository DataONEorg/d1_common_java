
package org.dataone.service.types.v2;

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
 *         &lt;xs:element type="xs:string" name="serviceExtensions"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Node extends org.dataone.service.types.v1.Node
{
    private String serviceExtensions;

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
}
