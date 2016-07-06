
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class AccessPolicy implements Serializable
{
    private static final long serialVersionUID = 10000001;
    private List<AccessRule> allowList = new ArrayList<AccessRule>();

    /** 
     * Get the list of 'allow' element items.
     * 
     * @return list
     */
    public List<AccessRule> getAllowList() {
        return allowList;
    }
    
    /* Same as getAllowList, but for use with Serializer
       The method will produce a null return if an empty
       allow rule array has been created, but nothing added
       https://redmine.dataone.org/issues/7422
    */
    public List<AccessRule> grabAllowListNullIfEmpty() {
        if (allowList != null && allowList.isEmpty()) {
                return null;
        }
        return allowList;
    }
    /** 
     * Set the list of 'allow' element items.
     * 
     * @param list
     */
    public void setAllowList(List<AccessRule> list) {
        allowList = list;
    }

    /** 
     * Get the number of 'allow' element items.
     * @return count
     */
    public int sizeAllowList() {
        if (allowList == null) {
            allowList = new ArrayList<AccessRule>();
        }
        return allowList.size();
    }

    /** 
     * Add a 'allow' element item.
     * @param item
     */
    public void addAllow(AccessRule item) {
        if (allowList == null) {
            allowList = new ArrayList<AccessRule>();
        }
        allowList.add(item);
    }

    /** 
     * Get 'allow' element item by position.
     * @return item
     * @param index
     */
    public AccessRule getAllow(int index) {
        if (allowList == null) {
            allowList = new ArrayList<AccessRule>();
        }
        return allowList.get(index);
    }

    /** 
     * Remove all 'allow' element items.
     */
    public void clearAllowList() {
        if (allowList == null) {
            allowList = new ArrayList<AccessRule>();
        }
        allowList.clear();
    }
}
