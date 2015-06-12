
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * A list of options that indicate the possible values for 
 a DataONE service. Each option that can be validly sent to a service is
 listed, providing the specific key that should be used when interacting 
 with the service, as well as a description of that key that allows API 
 users to understand the usage of the key.  For example, an OptionList 
 might contain a list of themes that can be used with the MNView.view 
 service, or for other services that have a configurable but controlled 
 set of parameters.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="OptionList">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="option" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 *   &lt;xs:attribute type="xs:string" use="required" name="key"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="description"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class OptionList implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private List<String> optionList = new ArrayList<String>();
    private String key;
    private String description;

    /** 
     * Get the list of 'option' element items. The key to be used within an API call to a DataONE 
            service, including a description of the key and its impact on the 
            service. For example, a key 'default' can be provided as the theme for 
            the MNView.view service.  Keys must not contain characters that will 
            need to be URL escaped.
     * 
     * @return list
     */
    public List<String> getOptionList() {
        return optionList;
    }

    /** 
     * Set the list of 'option' element items. The key to be used within an API call to a DataONE 
            service, including a description of the key and its impact on the 
            service. For example, a key 'default' can be provided as the theme for 
            the MNView.view service.  Keys must not contain characters that will 
            need to be URL escaped.
     * 
     * @param list
     */
    public void setOptionList(List<String> list) {
        optionList = list;
    }

    /** 
     * Get the number of 'option' element items.
     * @return count
     */
    public int sizeOptionList() {
        if (optionList == null) {
            optionList = new ArrayList<String>();
        }
        return optionList.size();
    }

    /** 
     * Add a 'option' element item.
     * @param item
     */
    public void addOption(String item) {
        if (optionList == null) {
            optionList = new ArrayList<String>();
        }
        optionList.add(item);
    }

    /** 
     * Get 'option' element item by position.
     * @return item
     * @param index
     */
    public String getOption(int index) {
        if (optionList == null) {
            optionList = new ArrayList<String>();
        }
        return optionList.get(index);
    }

    /** 
     * Remove all 'option' element items.
     */
    public void clearOptionList() {
        if (optionList == null) {
            optionList = new ArrayList<String>();
        }
        optionList.clear();
    }

    /** 
     * Get the 'key' attribute value. A value that can be used with a DataONE service. Key
          values must not contain any characters that need to be URL escaped, and
          should be short and informative. 
          
     * 
     * @return value
     */
    public String getKey() {
        return key;
    }

    /** 
     * Set the 'key' attribute value. A value that can be used with a DataONE service. Key
          values must not contain any characters that need to be URL escaped, and
          should be short and informative. 
          
     * 
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /** 
     * Get the 'description' attribute value. The description of an option, indicating its intended 
            use and impact on a DataONE service invocation.
     * 
     * @return value
     */
    public String getDescription() {
        return description;
    }

    /** 
     * Set the 'description' attribute value. The description of an option, indicating its intended 
            use and impact on a DataONE service invocation.
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
