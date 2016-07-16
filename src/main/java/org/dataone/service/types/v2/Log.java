
package org.dataone.service.types.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.dataone.service.types.v1.Slice;

/** 
 * Extends :class:`Types.Log` to represent a collection of 
 :class:`v2_0.Types.LogEntry` elements, used to transfer log information 
 between DataONE components.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:ns1="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Log">
 *   &lt;xs:complexContent>
 *     &lt;xs:extension base="ns1:Slice">
 *       &lt;xs:sequence>
 *         &lt;xs:element type="ns:LogEntry" name="logEntry" minOccurs="0" maxOccurs="unbounded"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:extension>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Log", propOrder = {
    "logEntry"
})
@XmlRootElement(name = "log")
public class Log extends Slice implements Serializable
{
    private static final long serialVersionUID = 10000000;
    protected List<LogEntry> logEntry = new ArrayList<LogEntry>();

    /** 
     * Get the list of 'logEntry' element items.
     * 
     * @return list
     */
    public List<LogEntry> getLogEntryList() {
        return logEntry;
    }

    /** 
     * Set the list of 'logEntry' element items.
     * 
     * @param list
     */
    public void setLogEntryList(List<LogEntry> list) {
        logEntry = list;
    }

    /** 
     * Get the 'count' attribute value. The number of entries in the slice.
     * @return size of wrapped list
     */
    @Override
    public int getCount() {
        if (logEntry == null) {
            logEntry = new ArrayList<LogEntry>();
        }
        return logEntry.size();
    }

    /** 
     * Get the number of 'logEntry' element items.
     * @return count
     */
    public int sizeLogEntryList() {
        if (logEntry == null) {
            logEntry = new ArrayList<LogEntry>();
        }
        return logEntry.size();
    }

    /** 
     * Add a 'logEntry' element item.
     * @param item
     */
    public void addLogEntry(LogEntry item) {
        if (logEntry == null) {
            logEntry = new ArrayList<LogEntry>();
        }
        logEntry.add(item);
    }

    /** 
     * Get 'logEntry' element item by position.
     * @return item
     * @param index
     */
    public LogEntry getLogEntry(int index) {
        if (logEntry == null) {
            logEntry = new ArrayList<LogEntry>();
        }
        return logEntry.get(index);
    }

    /** 
     * Remove all 'logEntry' element items.
     */
    public void clearLogEntryList() {
        if (logEntry == null) {
            logEntry = new ArrayList<LogEntry>();
        }
        logEntry.clear();
    }
}
