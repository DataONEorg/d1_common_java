
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.dataone.service.types.v1.Slice;

/** 
 * Extends :class:`Types.ObjectFormatList` to provide a 
 list of :class:`v2_0.Types.ObjectFormat`.

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ObjectFormatList">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns1:Slice">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns:ObjectFormat" name="objectFormat" minOccurs="1" maxOccurs="unbounded"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ObjectFormatList extends Slice implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private List<ObjectFormat> objectFormatList = new ArrayList<ObjectFormat>();

    /** 
     * Get the list of 'objectFormat' element items.
     * 
     * @return list
     */
    public List<ObjectFormat> getObjectFormatList() {
        return objectFormatList;
    }

    /** 
     * Set the list of 'objectFormat' element items.
     * 
     * @param list
     */
    public void setObjectFormatList(List<ObjectFormat> list) {
        objectFormatList = list;
        int newSize = 0;
        if (list != null)
            newSize = list.size();
        setTotal(getTotal() - getCount() + newSize);
        setCount(newSize);
    }

    /** 
     * Get the number of 'objectFormat' element items.
     * @return count
     */
    public int sizeObjectFormatList() {
        if (objectFormatList == null) {
            objectFormatList = new ArrayList<ObjectFormat>();
        }
        return objectFormatList.size();
    }

    /** 
     * Add a 'objectFormat' element item.
     * @param item
     */
    public void addObjectFormat(ObjectFormat item) {
        if (objectFormatList == null) {
            objectFormatList = new ArrayList<ObjectFormat>();
        }
        objectFormatList.add(item);
        setTotal(getTotal() + 1);
        setCount(getCount() + 1);
    }

    /** 
     * Get 'objectFormat' element item by position.
     * @return item
     * @param index
     */
    public ObjectFormat getObjectFormat(int index) {
        if (objectFormatList == null) {
            objectFormatList = new ArrayList<ObjectFormat>();
        }
        return objectFormatList.get(index);
    }

    /** 
     * Remove all 'objectFormat' element items.
     */
    public void clearObjectFormatList() {
        if (objectFormatList == null) {
            objectFormatList = new ArrayList<ObjectFormat>();
        }
        setTotal(getTotal() - getCount());
        setCount(0);
        objectFormatList.clear();
    }
}
