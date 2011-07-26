
package org.dataone.service.types.v1;

import java.io.Serializable;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Checksum">
 *   &lt;xs:simpleContent>
 *     &lt;xs:extension base="xs:string">
 *       &lt;xs:attribute type="ns:ChecksumAlgorithm" use="required" name="algorithm"/>
 *     &lt;/xs:extension>
 *   &lt;/xs:simpleContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Checksum implements Serializable
{
    private String value;
    private ChecksumAlgorithm algorithm;

    /** 
     * Get the extension value.
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /** 
     * Set the extension value.
     * 
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** 
     * Get the 'algorithm' attribute value.
     * 
     * @return value
     */
    public ChecksumAlgorithm getAlgorithm() {
        return algorithm;
    }

    /** 
     * Set the 'algorithm' attribute value.
     * 
     * @param algorithm
     */
    public void setAlgorithm(ChecksumAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}