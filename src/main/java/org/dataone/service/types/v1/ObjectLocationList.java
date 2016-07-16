
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
 * An *ObjectLocationList* is the structure returned from
 the :func:`CNRead.resolve` method of the CN REST interface. It provides
 a list of locations from which the specified object can be retrieved.

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ObjectLocationList">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:Identifier" name="identifier" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="ns:ObjectLocation" name="objectLocation" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectLocationList", propOrder = {
    "identifier",
    "objectLocation"
})
@XmlRootElement(name = "objectLocationList")
public class ObjectLocationList implements Serializable
{
    @XmlElement(required = true)
    protected Identifier identifier;
    protected List<ObjectLocation> objectLocation = new ArrayList<ObjectLocation>();
    
    private static final long serialVersionUID = 10000000;

    /** 
     * Get the 'identifier' element value. The :term:`identifier` of the object being
          resolved.
     * 
     * @return value
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /** 
     * Set the 'identifier' element value. The :term:`identifier` of the object being
          resolved.
     * 
     * @param identifier
     */
    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    /** 
     * Get the list of 'objectLocation' element items. List of nodes from which the object can be
          retrieved
     * 
     * @return list
     */
    public List<ObjectLocation> getObjectLocationList() {
        return objectLocation;
    }

    /** 
     * Set the list of 'objectLocation' element items. List of nodes from which the object can be
          retrieved
     * 
     * @param list
     */
    public void setObjectLocationList(List<ObjectLocation> list) {
        objectLocation = list;
    }

    /** 
     * Get the number of 'objectLocation' element items.
     * @return count
     */
    public int sizeObjectLocationList() {
        if (objectLocation == null) {
            objectLocation = new ArrayList<ObjectLocation>();
        }
        return objectLocation.size();
    }

    /** 
     * Add a 'objectLocation' element item.
     * @param item
     */
    public void addObjectLocation(ObjectLocation item) {
        if (objectLocation == null) {
            objectLocation = new ArrayList<ObjectLocation>();
        }
        objectLocation.add(item);
    }

    /** 
     * Get 'objectLocation' element item by position.
     * @return item
     * @param index
     */
    public ObjectLocation getObjectLocation(int index) {
        if (objectLocation == null) {
            objectLocation = new ArrayList<ObjectLocation>();
        }
        return objectLocation.get(index);
    }

    /** 
     * Remove all 'objectLocation' element items.
     */
    public void clearObjectLocationList() {
        if (objectLocation == null) {
            objectLocation = new ArrayList<ObjectLocation>();
        }
        objectLocation.clear();
    }
}
