
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.dataone.service.types.v1.Slice;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectFormatList", propOrder = {
    "objectFormat"
})
@XmlRootElement(name = "objectFormatList")
public class ObjectFormatList extends Slice implements Serializable
{
    @XmlElement(required = true)
    protected List<ObjectFormat> objectFormat = new ArrayList<ObjectFormat>();
    private static final long serialVersionUID = 10000000;


    /** 
     * Get the list of 'objectFormat' element items.
     * 
     * @return list
     */
    public List<ObjectFormat> getObjectFormatList() {
        return objectFormat;
    }

    /** 
     * Set the list of 'objectFormat' element items.
     * 
     * @param list
     */
    public void setObjectFormatList(List<ObjectFormat> list) {
        objectFormat = list;
    }

    /** 
     * Get the 'count' attribute value. The number of entries in the slice.
     * @return size of wrapped list
     */
    @Override
    public int getCount() {
        if (objectFormat == null) {
            objectFormat = new ArrayList<ObjectFormat>();
        }
        return objectFormat.size();
    }

    /** 
     * Get the number of 'objectFormat' element items.
     * @return count
     */
    public int sizeObjectFormatList() {
        if (objectFormat == null) {
            objectFormat = new ArrayList<ObjectFormat>();
        }
        return objectFormat.size();
    }

    /** 
     * Add a 'objectFormat' element item.
     * @param item
     */
    public void addObjectFormat(ObjectFormat item) {
        if (objectFormat == null) {
            objectFormat = new ArrayList<ObjectFormat>();
        }
        objectFormat.add(item);
    }

    /** 
     * Get 'objectFormat' element item by position.
     * @return item
     * @param index
     */
    public ObjectFormat getObjectFormat(int index) {
        if (objectFormat == null) {
            objectFormat = new ArrayList<ObjectFormat>();
        }
        return objectFormat.get(index);
    }

    /** 
     * Remove all 'objectFormat' element items.
     */
    public void clearObjectFormatList() {
        if (objectFormat == null) {
            objectFormat = new ArrayList<ObjectFormat>();
        }
        objectFormat.clear();
    }
}
