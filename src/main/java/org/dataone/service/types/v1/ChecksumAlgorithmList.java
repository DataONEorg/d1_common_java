
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/** 
 * Represents a list of :term:`checksum`
 algorithms.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ChecksumAlgorithmList">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="algorithm" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChecksumAlgorithmList", propOrder = {
    "algorithm"
})
@XmlRootElement(name = "checksumAlgorithmList")
public class ChecksumAlgorithmList implements Serializable
{
    @XmlElement(required = true)
    protected List<String> algorithm = new ArrayList<String>();

    private static final long serialVersionUID = 10000000;


    /** 
     * Get the list of 'algorithm' element items.
     * 
     * @return list
     */
    public List<String> getAlgorithmList() {
        return algorithm;
    }

    /** 
     * Set the list of 'algorithm' element items.
     * 
     * @param list
     */
    public void setAlgorithmList(List<String> list) {
        algorithm = list;
    }

    /** 
     * Get the number of 'algorithm' element items.
     * @return count
     */
    public int sizeAlgorithmList() {
        if (algorithm == null) {
            algorithm = new ArrayList<String>();
        }
        return algorithm.size();
    }

    /** 
     * Add a 'algorithm' element item.
     * @param item
     */
    public void addAlgorithm(String item) {
        if (algorithm == null) {
            algorithm = new ArrayList<String>();
        }
        algorithm.add(item);
    }

    /** 
     * Get 'algorithm' element item by position.
     * @return item
     * @param index
     */
    public String getAlgorithm(int index) {
        if (algorithm == null) {
            algorithm = new ArrayList<String>();
        }
        return algorithm.get(index);
    }

    /** 
     * Remove all 'algorithm' element items.
     */
    public void clearAlgorithmList() {
        if (algorithm == null) {
            algorithm = new ArrayList<String>();
        }
        algorithm.clear();
    }
}
