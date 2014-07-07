
package org.dataone.service.types.v2;

import java.io.Serializable;
import org.dataone.service.types.v1.Identifier;

/** 
 *  System metadata (often referred to as
 :term:`sysmeta`) is the information used by DataONE to track and manage
 objects across the distributed Coordinating and Member Nodes of the
 network. System metadata documents contain low level information (e.g.
 size, type, owner, access control rules) about managed objects such as
 science data, science metadata, and resource map objects and the
 relationships between objects (e.g. *obsoletes* and
 *obsoletedBy*). The information is maintained dynamically by
 Coordinating Nodes and is mutable in that it reflects the current state
 of an object in the system. Initial properties of system metadata are
 generated by clients and Member Nodes. After object synchronization, the
 Coordinating Nodes hold authoritative copies of system metadata. Mirror
 copies of system metadata are maintained at each of the Coordinating
 nodes.  System metadata are considered operational
 information needed to run DataONE, and can be read by all Coordinating
 Nodes and Member Nodes in the course of service provision. In order to
 reduce issues with third-party tracking of data status information,
 users can read system metadata for an object if they have the access
 rights to read the corresponding object which a system metadata record
 describes.  System Metadata elements are partitioned into two
 classes: metadata elements that must be provided by client software to
 the DataONE system, and elements that are generated by DataONE itself in
 the course of managing objects. 
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SystemMetadata">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns1:SystemMetadata">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns1:Identifier" name="seriesId"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class SystemMetadata extends org.dataone.service.types.v1.SystemMetadata
    implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private Identifier seriesId;

    /** 
     * Get the 'seriesId' element value. The :term:`seriesId` is a unique Unicode string
    			          that can be used to identify an object revision chain in DataONE.
    			          When included, the seriesId can be used to reference the latest version of 
    			          and object in the revision chain. API methods that rely on byte fixity 
    			          (e.g. for content replication) will not accept seriesId in place of discrete identifiers.
    			          The values used for seriesId must be unique within DataONE and cannot be the same as the 
    			          primary identifier of an object. The same encoding rules used for identifier values apply 
    			          to seriesId values.
     * 
     * @return value
     */
    public Identifier getSeriesId() {
        return seriesId;
    }

    /** 
     * Set the 'seriesId' element value. The :term:`seriesId` is a unique Unicode string
    			          that can be used to identify an object revision chain in DataONE.
    			          When included, the seriesId can be used to reference the latest version of 
    			          and object in the revision chain. API methods that rely on byte fixity 
    			          (e.g. for content replication) will not accept seriesId in place of discrete identifiers.
    			          The values used for seriesId must be unique within DataONE and cannot be the same as the 
    			          primary identifier of an object. The same encoding rules used for identifier values apply 
    			          to seriesId values.
     * 
     * @param seriesId
     */
    public void setSeriesId(Identifier seriesId) {
        this.seriesId = seriesId;
    }
}