
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * Value drawn from the value space of IANA Media Types (
 http://www.iana.org/assignments/media-types/media-types.xhtml ). When 
 specified, indicates the IANA Media Type (aka MIME-Type) of the object. 
 The name attribute MUST include the media type and subtype 
 (e.g. text/csv). The media type value is not case sensitive.
 Any required media type parameters must be provided, and
 optional parameters may be specified. There are no explicit constraints 
 on the name of media-type properties or their values, however they 
 SHOULD conform to media type optional and required parameters as 
 specified in the respective media type RFC.

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="MediaType">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:MediaTypeProperty" name="property" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="name"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class MediaType implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private List<MediaTypeProperty> propertyList = new ArrayList<MediaTypeProperty>();
    private String name;

    /** 
     * Get the list of 'property' element items. Media-type parameter(s) as specified by the 
              respective RFC for the media-type.
            
     * 
     * @return list
     */
    public List<MediaTypeProperty> getPropertyList() {
        return propertyList;
    }

    /** 
     * Set the list of 'property' element items. Media-type parameter(s) as specified by the 
              respective RFC for the media-type.
            
     * 
     * @param list
     */
    public void setPropertyList(List<MediaTypeProperty> list) {
        propertyList = list;
    }

    /** 
     * Get the number of 'property' element items.
     * @return count
     */
    public int sizePropertyList() {
        if (propertyList == null) {
            propertyList = new ArrayList<MediaTypeProperty>();
        }
        return propertyList.size();
    }

    /** 
     * Add a 'property' element item.
     * @param item
     */
    public void addProperty(MediaTypeProperty item) {
        if (propertyList == null) {
            propertyList = new ArrayList<MediaTypeProperty>();
        }
        propertyList.add(item);
    }

    /** 
     * Get 'property' element item by position.
     * @return item
     * @param index
     */
    public MediaTypeProperty getProperty(int index) {
        if (propertyList == null) {
            propertyList = new ArrayList<MediaTypeProperty>();
        }
        return propertyList.get(index);
    }

    /** 
     * Remove all 'property' element items.
     */
    public void clearPropertyList() {
        if (propertyList == null) {
            propertyList = new ArrayList<MediaTypeProperty>();
        }
        propertyList.clear();
    }

    /** 
     * Get the 'name' attribute value. The value of the media-type specified as a 
            required 'name' attribute of the mediaType element.
          
     * 
     * @return value
     */
    public String getName() {
        return name;
    }

    /** 
     * Set the 'name' attribute value. The value of the media-type specified as a 
            required 'name' attribute of the mediaType element.
          
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
