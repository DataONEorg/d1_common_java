
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;



/** 
 * A set of rules that specifies as a whole the allowable
 permissions that a given user, group, or system has for accessing a
 resource, including data, metadata, resource map, and service resources.
 An access policy consists of a sequence of allow rules that grant
 permissions to principals, which can be individual users, groups of
 users, symbolic users, or systems and services.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="AccessPolicy">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:AccessRule" name="allow" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessPolicy", propOrder = {
    "allow"
})
@XmlRootElement(name = "accessPolicy")
public class AccessPolicy implements Serializable
{
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(org.dataone.service.util.AccessRuleMarshallingAdapter.class)
    protected List<AccessRule> allow = new ArrayList<AccessRule>();

    /** 
     * Get the list of 'allow' element items.
     * 
     * @return list
     */
    public List<AccessRule> getAllowList() {
        return allow;
    }
    
    /* Same as getAllowList, but for use with Serializer
       The method will produce a null return if an empty
       allow rule array has been created, but nothing added
       https://redmine.dataone.org/issues/7422
    */
    public List<AccessRule> grabAllowListNullIfEmpty() {
        if (allow != null && allow.isEmpty()) {
                return null;
        }
        return allow;
    }
    /** 
     * Set the list of 'allow' element items.
     * 
     * @param list
     */
    public void setAllowList(List<AccessRule> list) {
        allow = list;
    }

    /** 
     * Get the number of 'allow' element items.
     * @return count
     */
    public int sizeAllowList() {
        if (allow == null) {
            allow = new ArrayList<AccessRule>();
        }
        return allow.size();
    }

    /** 
     * Add a 'allow' element item.
     * @param item
     */
    public void addAllow(AccessRule item) {
        if (allow == null) {
            allow = new ArrayList<AccessRule>();
        }
        allow.add(item);
    }

    /** 
     * Get 'allow' element item by position.
     * @return item
     * @param index
     */
    public AccessRule getAllow(int index) {
        if (allow == null) {
            allow = new ArrayList<AccessRule>();
        }
        return allow.get(index);
    }

    /** 
     * Remove all 'allow' element items.
     */
    public void clearAllowList() {
        if (allow == null) {
            allow = new ArrayList<AccessRule>();
        }
        allow.clear();
    }
}
