
package org.dataone.service.types.v2;

import java.io.Serializable;
import org.dataone.service.types.v1.Identifier;

/** 
 * System metadata in DataONE APIs version 2.0 extends
 the :class:`types.SystemMetadata` definition of version 1.x by adding 
 :term:`seriesId`, :term:`charset`, :term:`mediaType`, and 
 :term:`fileName` elements. Each of these are optional, so Version 1.x 
 System Metadata is compatible with Version 2.x, though not vice-versa.

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SystemMetadata">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns1:SystemMetadata">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns1:Identifier" name="seriesId" minOccurs="0"/>
 *         &lt;xs:element type="ns:MediaType" name="mediaType" minOccurs="0"/>
 *         &lt;xs:element type="xs:string" name="fileName" minOccurs="0"/>
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
    private MediaType mediaType;
    private String fileName;

    /** 
     * Get the 'seriesId' element value. The :term:`seriesId` is a unique Unicode 
                    string that can be used to identify an object revision chain 
                    in DataONE. When included, the seriesId can be used to 
                    reference the latest version of and object in the revision 
                    chain. API methods that rely on byte fixity (e.g. for content 
                    replication) will not accept seriesId in place of discrete 
                    identifiers.The values used for seriesId must be unique 
                    within DataONE and cannot be the same as the :term:`primary 
                    identifier` of an object. The same encoding rules used for identifier 
                    values apply to seriesId values.
     * 
     * @return value
     */
    public Identifier getSeriesId() {
        return seriesId;
    }

    /** 
     * Set the 'seriesId' element value. The :term:`seriesId` is a unique Unicode 
                    string that can be used to identify an object revision chain 
                    in DataONE. When included, the seriesId can be used to 
                    reference the latest version of and object in the revision 
                    chain. API methods that rely on byte fixity (e.g. for content 
                    replication) will not accept seriesId in place of discrete 
                    identifiers.The values used for seriesId must be unique 
                    within DataONE and cannot be the same as the :term:`primary 
                    identifier` of an object. The same encoding rules used for identifier 
                    values apply to seriesId values.
     * 
     * @param seriesId
     */
    public void setSeriesId(Identifier seriesId) {
        this.seriesId = seriesId;
    }

    /** 
     * Get the 'mediaType' element value. When specified, indicates the IANA Media 
                      Type (aka MIME-Type) of the object. When specified, this 
                      value overrides the default value specified in the version 
                      2.0 ObjectFormat structure. The value should include the 
                      media type and subtype (e.g. text/csv). The mediaType value 
                      is not case sensitive.The purpose of this value is to provide 
                        more detailed information about the specific media type 
                        of the associated object than may be available through 
                        the associated ObjectFormat.
                      When specified, the mediaType value here 
                        should be used in preference to the value recorded in the 
                        referenced :class:`ObjectFormat`.
                      This value SHOULD be set by the content 
                      creator. It MAY be set by any receiving agent if the value 
                      is not already set, the value in the ObjectFormat is less 
                      specific, and a correct value is specified elsewhere such 
                      as by a HTTP Content-Type parameter.
                      This value may only be changed to correct 
                        an erroneous entry.
     * 
     * @return value
     */
    public MediaType getMediaType() {
        return mediaType;
    }

    /** 
     * Set the 'mediaType' element value. When specified, indicates the IANA Media 
                      Type (aka MIME-Type) of the object. When specified, this 
                      value overrides the default value specified in the version 
                      2.0 ObjectFormat structure. The value should include the 
                      media type and subtype (e.g. text/csv). The mediaType value 
                      is not case sensitive.The purpose of this value is to provide 
                        more detailed information about the specific media type 
                        of the associated object than may be available through 
                        the associated ObjectFormat.
                      When specified, the mediaType value here 
                        should be used in preference to the value recorded in the 
                        referenced :class:`ObjectFormat`.
                      This value SHOULD be set by the content 
                      creator. It MAY be set by any receiving agent if the value 
                      is not already set, the value in the ObjectFormat is less 
                      specific, and a correct value is specified elsewhere such 
                      as by a HTTP Content-Type parameter.
                      This value may only be changed to correct 
                        an erroneous entry.
     * 
     * @param mediaType
     */
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    /** 
     * Get the 'fileName' element value. Optional tough recommended value that 
                      provides a suggested file name for the object. Values should
                      conform to cross platform file naming conventions.
                    This value SHOULD be set by the content 
                      creator.This value MAY be set by any receiving agent
                      Changing the value is discouraged once set, unless by the 
                      authoritative Member Node of content owner.
                    
     * 
     * @return value
     */
    public String getFileName() {
        return fileName;
    }

    /** 
     * Set the 'fileName' element value. Optional tough recommended value that 
                      provides a suggested file name for the object. Values should
                      conform to cross platform file naming conventions.
                    This value SHOULD be set by the content 
                      creator.This value MAY be set by any receiving agent
                      Changing the value is discouraged once set, unless by the 
                      authoritative Member Node of content owner.
                    
     * 
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
