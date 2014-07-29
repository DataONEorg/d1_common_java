
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * A list of ObjectFormatId elements

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="FormatList">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="formatType" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class FormatList implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private List<String> formatTypeList = new ArrayList<String>();

    /** 
     * Get the list of 'formatType' element items.
     * 
     * @return list
     */
    public List<String> getFormatTypeList() {
        return formatTypeList;
    }

    /** 
     * Set the list of 'formatType' element items.
     * 
     * @param list
     */
    public void setFormatTypeList(List<String> list) {
        formatTypeList = list;
    }

    /** 
     * Get the number of 'formatType' element items.
     * @return count
     */
    public int sizeFormatTypeList() {
        if (formatTypeList == null) {
            formatTypeList = new ArrayList<String>();
        }
        return formatTypeList.size();
    }

    /** 
     * Add a 'formatType' element item.
     * @param item
     */
    public void addFormatType(String item) {
        if (formatTypeList == null) {
            formatTypeList = new ArrayList<String>();
        }
        formatTypeList.add(item);
    }

    /** 
     * Get 'formatType' element item by position.
     * @return item
     * @param index
     */
    public String getFormatType(int index) {
        if (formatTypeList == null) {
            formatTypeList = new ArrayList<String>();
        }
        return formatTypeList.get(index);
    }

    /** 
     * Remove all 'formatType' element items.
     */
    public void clearFormatTypeList() {
        if (formatTypeList == null) {
            formatTypeList = new ArrayList<String>();
        }
        formatTypeList.clear();
    }
}
