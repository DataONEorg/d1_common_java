
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
/** 
 *  A list of :term:`Subjects` used for identity/group
 management
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SubjectList">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:Subject" name="subject" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectList", propOrder = {
    "subject"
})
@XmlSeeAlso({
    ServiceMethodRestriction.class
})
@XmlRootElement(name = "subjectList")
public class SubjectList implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private List<Subject> subject = new ArrayList<Subject>();

    /** 
     * Get the list of 'subject' element items.
     * 
     * @return list
     */
    public List<Subject> getSubjectList() {
        return subject;
    }

    /** 
     * Set the list of 'subject' element items.
     * 
     * @param list
     */
    public void setSubjectList(List<Subject> list) {
        subject = list;
    }

    /** 
     * Get the number of 'subject' element items.
     * @return count
     */
    public int sizeSubjectList() {
        if (subject == null) {
            subject = new ArrayList<Subject>();
        }
        return subject.size();
    }

    /** 
     * Add a 'subject' element item.
     * @param item
     */
    public void addSubject(Subject item) {
        if (subject == null) {
            subject = new ArrayList<Subject>();
        }
        subject.add(item);
    }

    /** 
     * Get 'subject' element item by position.
     * @return item
     * @param index
     */
    public Subject getSubject(int index) {
        if (subject == null) {
            subject = new ArrayList<Subject>();
        }
        return subject.get(index);
    }

    /** 
     * Remove all 'subject' element items.
     */
    public void clearSubjectList() {
        if (subject == null) {
            subject = new ArrayList<Subject>();
        }
        subject.clear();
    }
}
