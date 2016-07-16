
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
/** 
 * Portion of an :class:`Types.ObjectLocationList`
 indicating the node from which the object can be retrieved. The
 principal information on each location is found in the *nodeIdentifier*,
 all other fields are provided for convenience, but could also be looked
 up from the :class:`Types.NodeList` information obtained from 
 :func:`CNCore.listNodes`.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ObjectLocation">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:NodeReference" name="nodeIdentifier" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="baseURL" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="version" minOccurs="1" maxOccurs="unbounded"/>
 *     &lt;xs:element type="xs:string" name="url" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:int" name="preference" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectLocation", propOrder = {
    "nodeIdentifier",
    "baseURL",
    "version",
    "url",
    "preference"
})
@XmlRootElement(name = "objectLocation")
public class ObjectLocation implements Serializable {

    @XmlElement(required = true)
    protected NodeReference nodeIdentifier;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String baseURL;
    @XmlElement(required = true)
    protected List<String> version  = new ArrayList<String>();
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String url;
    protected Integer preference;

    private static final long serialVersionUID = 10000000;



    /** 
     * Get the 'nodeIdentifier' element value. Identifier of the :class:`Types.Node` (the same
            identifier used in the node registry for identifying the node).
            
     * 
     * @return value
     */
    public NodeReference getNodeIdentifier() {
        return nodeIdentifier;
    }

    /** 
     * Set the 'nodeIdentifier' element value. Identifier of the :class:`Types.Node` (the same
            identifier used in the node registry for identifying the node).
            
     * 
     * @param nodeIdentifier
     */
    public void setNodeIdentifier(NodeReference nodeIdentifier) {
        this.nodeIdentifier = nodeIdentifier;
    }

    /** 
     * Get the 'baseURL' element value. The current base URL (the *baseURL* element from
            the :class:`Types.Node` record) for services implemented on the
            target node. Used with service version to construct a URL for
            service calls to this node. Note that complete information on
            services available on a Node is available from the
            :func:`CNCore.listNodes` service. 
     * 
     * @return value
     */
    public String getBaseURL() {
        return baseURL;
    }

    /** 
     * Set the 'baseURL' element value. The current base URL (the *baseURL* element from
            the :class:`Types.Node` record) for services implemented on the
            target node. Used with service version to construct a URL for
            service calls to this node. Note that complete information on
            services available on a Node is available from the
            :func:`CNCore.listNodes` service. 
     * 
     * @param baseURL
     */
    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    /** 
     * Get the list of 'version' element items. The version of services implemented on the node.
            Used with base url to construct a URL for service calls to this
            node. Note that complete information on services available on a Node
            is available from the :func:`CNCore.listNodes` service.
            
     * 
     * @return list
     */
    public List<String> getVersionList() {
        return version;
    }

    /** 
     * Set the list of 'version' element items. The version of services implemented on the node.
            Used with base url to construct a URL for service calls to this
            node. Note that complete information on services available on a Node
            is available from the :func:`CNCore.listNodes` service.
            
     * 
     * @param list
     */
    public void setVersionList(List<String> list) {
        version = list;
    }

    /** 
     * Get the number of 'version' element items.
     * @return count
     */
    public int sizeVersionList() {
        if (version == null) {
            version = new ArrayList<String>();
        }
        return version.size();
    }

    /** 
     * Add a 'version' element item.
     * @param item
     */
    public void addVersion(String item) {
        if (version == null) {
            version = new ArrayList<String>();
        }
        version.add(item);
    }

    /** 
     * Get 'version' element item by position.
     * @return item
     * @param index
     */
    public String getVersion(int index) {
        if (version == null) {
            version = new ArrayList<String>();
        }
        return version.get(index);
    }

    /** 
     * Remove all 'version' element items.
     */
    public void clearVersionList() {
        if (version == null) {
            version = new ArrayList<String>();
        }
        version.clear();
    }

    /** 
     * Get the 'url' element value. The full (absolute) URL that can be used to
            retrieve the object using the get() method of the rest
            interface.For example, if identifier was "ABX154", and the
            node had a base URL of ``http://mn1.dataone.org/mn`` then the value
            would be 
            ``http://mn1.dataone.org/mn/v1/object/ABX154``
     * 
     * @return value
     */
    public String getUrl() {
        return url;
    }

    /** 
     * Set the 'url' element value. The full (absolute) URL that can be used to
            retrieve the object using the get() method of the rest
            interface.For example, if identifier was "ABX154", and the
            node had a base URL of ``http://mn1.dataone.org/mn`` then the value
            would be 
            ``http://mn1.dataone.org/mn/v1/object/ABX154``
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 
     * Get the 'preference' element value. A weighting parameter that provides a hint to the
            caller for the relative preference for nodes from which the content
            should be retrieved. Higher values have higher preference.
            
     * 
     * @return value
     */
    public Integer getPreference() {
        return preference;
    }

    /** 
     * Set the 'preference' element value. A weighting parameter that provides a hint to the
            caller for the relative preference for nodes from which the content
            should be retrieved. Higher values have higher preference.
            
     * 
     * @param preference
     */
    public void setPreference(Integer preference) {
        this.preference = preference;
    }
}
