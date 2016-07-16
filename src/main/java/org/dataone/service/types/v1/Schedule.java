
package org.dataone.service.types.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/** 
 * The schedule on which :term:`synchronization` will run
 for a particular node. Syntax for each time slot follows the syntax
 conventions defined by the Quartz Scheduler
 (http://www.quartz-scheduler.org/api/2.1.0/org/quartz/CronExpression.html)

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Schedule">
 *   &lt;xs:attribute type="xs:string" use="required" name="hour"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="mday"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="min"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="mon"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="sec"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="wday"/>
 *   &lt;xs:attribute type="xs:string" use="required" name="year"/>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Schedule")
public class Schedule implements Serializable {

    @XmlAttribute(name = "hour", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String hour;
    @XmlAttribute(name = "mday", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mday;
    @XmlAttribute(name = "min", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String min;
    @XmlAttribute(name = "mon", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mon;
    @XmlAttribute(name = "sec", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sec;
    @XmlAttribute(name = "wday", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String wday;
    @XmlAttribute(name = "year", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String year;

    private static final long serialVersionUID = 10000000;


    /** 
     * Get the 'hour' attribute value.
     * 
     * @return value
     */
    public String getHour() {
        return hour;
    }

    /** 
     * Set the 'hour' attribute value.
     * 
     * @param hour
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /** 
     * Get the 'mday' attribute value.
     * 
     * @return value
     */
    public String getMday() {
        return mday;
    }

    /** 
     * Set the 'mday' attribute value.
     * 
     * @param mday
     */
    public void setMday(String mday) {
        this.mday = mday;
    }

    /** 
     * Get the 'min' attribute value.
     * 
     * @return value
     */
    public String getMin() {
        return min;
    }

    /** 
     * Set the 'min' attribute value.
     * 
     * @param min
     */
    public void setMin(String min) {
        this.min = min;
    }

    /** 
     * Get the 'mon' attribute value.
     * 
     * @return value
     */
    public String getMon() {
        return mon;
    }

    /** 
     * Set the 'mon' attribute value.
     * 
     * @param mon
     */
    public void setMon(String mon) {
        this.mon = mon;
    }

    /** 
     * Get the 'sec' attribute value.
     * 
     * @return value
     */
    public String getSec() {
        return sec;
    }

    /** 
     * Set the 'sec' attribute value.
     * 
     * @param sec
     */
    public void setSec(String sec) {
        this.sec = sec;
    }

    /** 
     * Get the 'wday' attribute value.
     * 
     * @return value
     */
    public String getWday() {
        return wday;
    }

    /** 
     * Set the 'wday' attribute value.
     * 
     * @param wday
     */
    public void setWday(String wday) {
        this.wday = wday;
    }

    /** 
     * Get the 'year' attribute value.
     * 
     * @return value
     */
    public String getYear() {
        return year;
    }

    /** 
     * Set the 'year' attribute value.
     * 
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }
}
