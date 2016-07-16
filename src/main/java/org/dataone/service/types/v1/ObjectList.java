
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * A list of object locations (nodes) from which the
 object can be retrieved. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ObjectList">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns:Slice">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns:ObjectInfo" name="objectInfo" minOccurs="0" maxOccurs="unbounded"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectList", propOrder = {
    "objectInfo"
})
@XmlRootElement( name = "objectList")
public class ObjectList extends Slice implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private List<ObjectInfo> objectInfo = new ArrayList<ObjectInfo>();

    /** 
     * Get the list of 'objectInfo' element items.
     * 
     * @return list
     */
    public List<ObjectInfo> getObjectInfoList() {
        return objectInfo;
    }

    /** 
     * Set the list of 'objectInfo' element items.
     * 
     * @param list
     */
    public void setObjectInfoList(List<ObjectInfo> list) {
        objectInfo = list;
    }

    /** 
     * Get the 'count' attribute value. The number of entries in the slice.
     * @return size of wrapped list
     */
    @Override
    public int getCount() {
        if (objectInfo == null) {
            objectInfo = new ArrayList<ObjectInfo>();
        }
        return objectInfo.size();
    }

    /** 
     * Get the number of 'objectInfo' element items.
     * @return count
     */
    public int sizeObjectInfoList() {
        if (objectInfo == null) {
            objectInfo = new ArrayList<ObjectInfo>();
        }
        return objectInfo.size();
    }

    /** 
     * Add a 'objectInfo' element item.
     * @param item
     */
    public void addObjectInfo(ObjectInfo item) {
        if (objectInfo == null) {
            objectInfo = new ArrayList<ObjectInfo>();
        }
        objectInfo.add(item);
    }

    /** 
     * Get 'objectInfo' element item by position.
     * @return item
     * @param index
     */
    public ObjectInfo getObjectInfo(int index) {
        if (objectInfo == null) {
            objectInfo = new ArrayList<ObjectInfo>();
        }
        return objectInfo.get(index);
    }

    /** 
     * Remove all 'objectInfo' element items.
     */
    public void clearObjectInfoList() {
        if (objectInfo == null) {
            objectInfo = new ArrayList<ObjectInfo>();
        }
        objectInfo.clear();
    }
}
