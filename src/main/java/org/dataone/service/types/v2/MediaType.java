
package org.dataone.service.types.v2;

import java.io.Serializable;

/** 
 * Value drawn from the value space of IANA Media Types (
 http://www.iana.org/assignments/media-types/media-types.xhtml ). When 
 specified, indicates the IANA Media Type (aka MIME-Type) of the object. 
 The value should include the media type and subtype (e.g. text/csv). The 
 mediaType value is not case sensitive.
 Any required media type parameters must be provided, and
 optional parameters may be specified. There are no explicit constraints 
 on the name of attributes or their values, however they SHOULD conform
 to media type optional and required parameters as specified in the 
 respective media type RFC.

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="MediaType">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string">
 *       &lt;xs:anyAttribute processContents="lax" namespace="##any"/>
 *     &lt;/xs:extension>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class MediaType implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private String string;

    /** 
     * Get the 'MediaType' complexType value.
     * 
     * @return value
     */
    public String getString() {
        return string;
    }

    /** 
     * Set the 'MediaType' complexType value.
     * 
     * @param string
     */
    public void setString(String string) {
        this.string = string;
    }
}
