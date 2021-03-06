
package org.dataone.service.types.v1_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1.1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="QueryField">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="name" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="description" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="xs:string" name="type" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:boolean" name="searchable" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:boolean" name="returnable" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:boolean" name="sortable" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:boolean" name="multivalued" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryField", namespace = "http://ns.dataone.org/service/types/v1.1", propOrder = {
    "name",
    "description",
    "type",
    "searchable",
    "returnable",
    "sortable",
    "multivalued"
})
@XmlRootElement( name = "queryField" )
public class QueryField implements Serializable
{
    private static final long serialVersionUID = 10000000;
    
    @XmlElement(required = true)
    protected String name;
    protected List<String> description = new ArrayList<String>();
    @XmlElement(required = true)
    protected String type;
    protected boolean searchable;
    protected boolean returnable;
    protected boolean sortable;
    protected Boolean multivalued;

    /** 
     * Get the 'name' element value. The name of the field as used programmatically when 
              constructing queries or other rferences to the field.
     * 
     * @return value
     */
    public String getName() {
        return name;
    }

    /** 
     * Set the 'name' element value. The name of the field as used programmatically when 
              constructing queries or other rferences to the field.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Get the list of 'description' element items. An optional, repeatable, brief description of the field that can be
            used to help guide developers or end users in appropriate use of the field. May for 
            example, contain a links to additional documentation.
     * 
     * @return list
     */
    public List<String> getDescriptionList() {
        return description;
    }

    /** 
     * Set the list of 'description' element items. An optional, repeatable, brief description of the field that can be
            used to help guide developers or end users in appropriate use of the field. May for 
            example, contain a links to additional documentation.
     * 
     * @param list
     */
    public void setDescriptionList(List<String> list) {
        description = list;
    }

    /** 
     * Get the number of 'description' element items.
     * @return count
     */
    public int sizeDescriptionList() {
        if (description == null) {
            description = new ArrayList<String>();
        }
        return description.size();
    }

    /** 
     * Add a 'description' element item.
     * @param item
     */
    public void addDescription(String item) {
        if (description == null) {
            description = new ArrayList<String>();
        }
        description.add(item);
    }

    /** 
     * Get 'description' element item by position.
     * @return item
     * @param index
     */
    public String getDescription(int index) {
        if (description == null) {
            description = new ArrayList<String>();
        }
        return description.get(index);
    }

    /** 
     * Remove all 'description' element items.
     */
    public void clearDescriptionList() {
        if (description == null) {
            description = new ArrayList<String>();
        }
        description.clear();
    }

    /** 
     * Get the 'type' element value. The type of the field, expressed in the language peculiar to the 
            query engine being described.
     * 
     * @return value
     */
    public String getType() {
        return type;
    }

    /** 
     * Set the 'type' element value. The type of the field, expressed in the language peculiar to the 
            query engine being described.
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** 
     * Get the 'searchable' element value. Indicates if the field may be used in constructing queries (as opposed 
              to only appearing in results)
     * 
     * @return value
     */
    public boolean isSearchable() {
        return searchable;
    }

    /** 
     * Set the 'searchable' element value. Indicates if the field may be used in constructing queries (as opposed 
              to only appearing in results)
     * 
     * @param searchable
     */
    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    /** 
     * Get the 'returnable' element value. Indicates if the field values may be returned in search results.
     * 
     * @return value
     */
    public boolean isReturnable() {
        return returnable;
    }

    /** 
     * Set the 'returnable' element value. Indicates if the field values may be returned in search results.
     * 
     * @param returnable
     */
    public void setReturnable(boolean returnable) {
        this.returnable = returnable;
    }

    /** 
     * Get the 'sortable' element value. Indicates if the field can be used for sorting results.
     * 
     * @return value
     */
    public boolean isSortable() {
        return sortable;
    }

    /** 
     * Set the 'sortable' element value. Indicates if the field can be used for sorting results.
     * 
     * @param sortable
     */
    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    /** 
     * Get the 'multivalued' element value. Indicates if the field may contain multiple values. Some query engines
            such as SOLR support this capability.
     * 
     * @return value
     */
    public Boolean getMultivalued() {
        return multivalued;
    }

    /** 
     * Set the 'multivalued' element value. Indicates if the field may contain multiple values. Some query engines
            such as SOLR support this capability.
     * 
     * @param multivalued
     */
    public void setMultivalued(Boolean multivalued) {
        this.multivalued = multivalued;
    }
}
