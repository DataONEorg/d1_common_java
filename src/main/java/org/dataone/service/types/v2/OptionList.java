
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OptionList", propOrder = {
    "option"
})
@XmlRootElement(name = "optionList")
public class OptionList implements Serializable
{
    private static final long serialVersionUID = 10000000;

    protected List<String> option = new ArrayList<String>();
    @XmlAttribute(name = "key", required = true)
    protected String key;
    @XmlAttribute(name = "description", required = true)
    protected String description;
 

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
        return option;
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
        option = list;
    }

    /** 
     * Get the number of 'option' element items.
     * @return count
     */
    public int sizeOptionList() {
        if (option == null) {
            option = new ArrayList<String>();
        }
        return option.size();
    }

    /** 
     * Add a 'option' element item.
     * @param item
     */
    public void addOption(String item) {
        if (option == null) {
            option = new ArrayList<String>();
        }
        option.add(item);
    }

    /** 
     * Get 'option' element item by position.
     * @return item
     * @param index
     */
    public String getOption(int index) {
        if (option == null) {
            option = new ArrayList<String>();
        }
        return option.get(index);
    }

    /** 
     * Remove all 'option' element items.
     */
    public void clearOptionList() {
        if (option == null) {
            option = new ArrayList<String>();
        }
        option.clear();
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
