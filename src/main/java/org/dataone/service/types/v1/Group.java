
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
 * Group represents metadata about a :term:`Subject` that
 represents a collection of other Subjects. Groups provide a convenient
 mechanism to express access rules for certain roles that are not
 necessarily tied to particular :term:`principals` over
 time.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Group">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:Subject" name="subject" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="xs:string" name="groupName" minOccurs="1" maxOccurs="1"/>
 *     &lt;xs:element type="ns:Subject" name="hasMember" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:Subject" name="rightsHolder" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Group", propOrder = {
    "subject",
    "groupName",
    "hasMember",
    "rightsHolder"
})
@XmlRootElement(name = "group")
public class Group implements Serializable
{
    @XmlElement(required = true)
    protected Subject subject;
    @XmlElement(required = true)
    protected String groupName;
    protected List<Subject> hasMember = new ArrayList<Subject>();
    @XmlElement(required = true)
    protected List<Subject> rightsHolder = new ArrayList<Subject>();

    private static final long serialVersionUID = 10000000;


    /** 
     * Get the 'subject' element value. The unique, immutable identifier of the
            :term:`group`. Group subjects must not be reused, and so they are
            both immutable and can not be deleted from the DataONE
            system.
     * 
     * @return value
     */
    public Subject getSubject() {
        return subject;
    }

    /** 
     * Set the 'subject' element value. The unique, immutable identifier of the
            :term:`group`. Group subjects must not be reused, and so they are
            both immutable and can not be deleted from the DataONE
            system.
     * 
     * @param subject
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /** 
     * Get the 'groupName' element value. The name of the Group.
     * 
     * @return value
     */
    public String getGroupName() {
        return groupName;
    }

    /** 
     * Set the 'groupName' element value. The name of the Group.
     * 
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /** 
     * Get the list of 'hasMember' element items. A :term:`Subject` that is a member of this
              group, expressed using the unique identifier for that
              Subject.
     * 
     * @return list
     */
    public List<Subject> getHasMemberList() {
        return hasMember;
    }

    /** 
     * Set the list of 'hasMember' element items. A :term:`Subject` that is a member of this
              group, expressed using the unique identifier for that
              Subject.
     * 
     * @param list
     */
    public void setHasMemberList(List<Subject> list) {
        hasMember = list;
    }

    /** 
     * Get the number of 'hasMember' element items.
     * @return count
     */
    public int sizeHasMemberList() {
        if (hasMember == null) {
            hasMember = new ArrayList<Subject>();
        }
        return hasMember.size();
    }

    /** 
     * Add a 'hasMember' element item.
     * @param item
     */
    public void addHasMember(Subject item) {
        if (hasMember == null) {
            hasMember = new ArrayList<Subject>();
        }
        hasMember.add(item);
    }

    /** 
     * Get 'hasMember' element item by position.
     * @return item
     * @param index
     */
    public Subject getHasMember(int index) {
        if (hasMember == null) {
            hasMember = new ArrayList<Subject>();
        }
        return hasMember.get(index);
    }

    /** 
     * Remove all 'hasMember' element items.
     */
    public void clearHasMemberList() {
        if (hasMember == null) {
            hasMember = new ArrayList<Subject>();
        }
        hasMember.clear();
    }

    /** 
     * Get the list of 'rightsHolder' element items. Represents the list of owners of this :term:`group`.
          All groups are readable by anyone in the DataONE system, but can only
          be modified by subjects listed in *rightsHolder* fields. Designation
          as a :term:`rightsHolder` allows the subject, or their equivalent
          identities, to make changes to the mutable properties of the group,
          including its name, membership list and rights holder list. The
          subject of the group itself is immutable. 
     * 
     * @return list
     */
    public List<Subject> getRightsHolderList() {
        return rightsHolder;
    }

    /** 
     * Set the list of 'rightsHolder' element items. Represents the list of owners of this :term:`group`.
          All groups are readable by anyone in the DataONE system, but can only
          be modified by subjects listed in *rightsHolder* fields. Designation
          as a :term:`rightsHolder` allows the subject, or their equivalent
          identities, to make changes to the mutable properties of the group,
          including its name, membership list and rights holder list. The
          subject of the group itself is immutable. 
     * 
     * @param list
     */
    public void setRightsHolderList(List<Subject> list) {
        rightsHolder = list;
    }

    /** 
     * Get the number of 'rightsHolder' element items.
     * @return count
     */
    public int sizeRightsHolderList() {
        if (rightsHolder == null) {
            rightsHolder = new ArrayList<Subject>();
        }
        return rightsHolder.size();
    }

    /** 
     * Add a 'rightsHolder' element item.
     * @param item
     */
    public void addRightsHolder(Subject item) {
        if (rightsHolder == null) {
            rightsHolder = new ArrayList<Subject>();
        }
        rightsHolder.add(item);
    }

    /** 
     * Get 'rightsHolder' element item by position.
     * @return item
     * @param index
     */
    public Subject getRightsHolder(int index) {
        if (rightsHolder == null) {
            rightsHolder = new ArrayList<Subject>();
        }
        return rightsHolder.get(index);
    }

    /** 
     * Remove all 'rightsHolder' element items.
     */
    public void clearRightsHolderList() {
        if (rightsHolder == null) {
            rightsHolder = new ArrayList<Subject>();
        }
        rightsHolder.clear();
    }
}
