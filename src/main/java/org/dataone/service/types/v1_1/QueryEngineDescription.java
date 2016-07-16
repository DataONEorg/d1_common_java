
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
 * Describes a query engine that can be used to search content on the node. 
 Query engines may be general purpose or specialized for particular communities or domains.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1.1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="QueryEngineDescription">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="queryEngineVersion" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="querySchemaVersion" minOccurs="0" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="name" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="additionalInfo" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:QueryField" name="queryField" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryEngineDescription", namespace = "http://ns.dataone.org/service/types/v1.1", propOrder = {
    "queryEngineVersion",
    "querySchemaVersion",
    "name",
    "additionalInfo",
    "queryField"
})
@XmlRootElement(name = "queryEngineDescription")
public class QueryEngineDescription implements Serializable
{
    private static final long serialVersionUID = 10000000;
    
    @XmlElement(required = true)
    protected String queryEngineVersion;
    protected String querySchemaVersion;
    @XmlElement(required = true)
    protected String name;
    protected List<String> additionalInfo = new ArrayList<String>();
    protected List<QueryField> queryField = new ArrayList<QueryField>();

    /** 
     * Get the 'queryEngineVersion' element value. The version of the underlying query engine. Used by clients to determine possible
            compatibility concerns or features available.
     * 
     * @return value
     */
    public String getQueryEngineVersion() {
        return queryEngineVersion;
    }

    /** 
     * Set the 'queryEngineVersion' element value. The version of the underlying query engine. Used by clients to determine possible
            compatibility concerns or features available.
     * 
     * @param queryEngineVersion
     */
    public void setQueryEngineVersion(String queryEngineVersion) {
        this.queryEngineVersion = queryEngineVersion;
    }

    /** 
     * Get the 'querySchemaVersion' element value. Version of the schema in use by the query engine, e.g. "1.0.1"
     * 
     * @return value
     */
    public String getQuerySchemaVersion() {
        return querySchemaVersion;
    }

    /** 
     * Set the 'querySchemaVersion' element value. Version of the schema in use by the query engine, e.g. "1.0.1"
     * 
     * @param querySchemaVersion
     */
    public void setQuerySchemaVersion(String querySchemaVersion) {
        this.querySchemaVersion = querySchemaVersion;
    }

    /** 
     * Get the 'name' element value. The full, human readable name of the query engine. For example: 
              "Apache SOLR"
     * 
     * @return value
     */
    public String getName() {
        return name;
    }

    /** 
     * Set the 'name' element value. The full, human readable name of the query engine. For example: 
              "Apache SOLR"
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Get the list of 'additionalInfo' element items. An optional human readable description of the query engine. This can be 
              used to describe any special capabilities or intended uses for the query engine. For example, 
              a query engine may be tuned to suit a particular audience or domain as opposed to providing 
              a general purpose discovery mechanism.This field may also contain links to additional information about the query engine, 
            such as documentation for the search syntax provided by the query engine implemntors.
     * 
     * @return list
     */
    public List<String> getAdditionalInfoList() {
        return additionalInfo;
    }

    /** 
     * Set the list of 'additionalInfo' element items. An optional human readable description of the query engine. This can be 
              used to describe any special capabilities or intended uses for the query engine. For example, 
              a query engine may be tuned to suit a particular audience or domain as opposed to providing 
              a general purpose discovery mechanism.This field may also contain links to additional information about the query engine, 
            such as documentation for the search syntax provided by the query engine implemntors.
     * 
     * @param list
     */
    public void setAdditionalInfoList(List<String> list) {
        additionalInfo = list;
    }

    /** 
     * Get the number of 'additionalInfo' element items.
     * @return count
     */
    public int sizeAdditionalInfoList() {
        if (additionalInfo == null) {
            additionalInfo = new ArrayList<String>();
        }
        return additionalInfo.size();
    }

    /** 
     * Add a 'additionalInfo' element item.
     * @param item
     */
    public void addAdditionalInfo(String item) {
        if (additionalInfo == null) {
            additionalInfo = new ArrayList<String>();
        }
        additionalInfo.add(item);
    }

    /** 
     * Get 'additionalInfo' element item by position.
     * @return item
     * @param index
     */
    public String getAdditionalInfo(int index) {
        if (additionalInfo == null) {
            additionalInfo = new ArrayList<String>();
        }
        return additionalInfo.get(index);
    }

    /** 
     * Remove all 'additionalInfo' element items.
     */
    public void clearAdditionalInfoList() {
        if (additionalInfo == null) {
            additionalInfo = new ArrayList<String>();
        }
        additionalInfo.clear();
    }

    /** 
     * Get the list of 'queryField' element items. A list of query fields supported by the query engine.
     * 
     * @return list
     */
    public List<QueryField> getQueryFieldList() {
        return queryField;
    }

    /** 
     * Set the list of 'queryField' element items. A list of query fields supported by the query engine.
     * 
     * @param list
     */
    public void setQueryFieldList(List<QueryField> list) {
        queryField = list;
    }

    /** 
     * Get the number of 'queryField' element items.
     * @return count
     */
    public int sizeQueryFieldList() {
        if (queryField == null) {
            queryField = new ArrayList<QueryField>();
        }
        return queryField.size();
    }

    /** 
     * Add a 'queryField' element item.
     * @param item
     */
    public void addQueryField(QueryField item) {
        if (queryField == null) {
            queryField = new ArrayList<QueryField>();
        }
        queryField.add(item);
    }

    /** 
     * Get 'queryField' element item by position.
     * @return item
     * @param index
     */
    public QueryField getQueryField(int index) {
        if (queryField == null) {
            queryField = new ArrayList<QueryField>();
        }
        return queryField.get(index);
    }

    /** 
     * Remove all 'queryField' element items.
     */
    public void clearQueryFieldList() {
        if (queryField == null) {
            queryField = new ArrayList<QueryField>();
        }
        queryField.clear();
    }
}
