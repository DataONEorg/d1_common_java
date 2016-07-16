
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/** 
 * A list of :term:`Subjects`, including both
 :class:`Types.Person` and :class:`Types.Group` entries returned from
 the :func:`CNIdentity.getSubjectInfo` service and
 :func:`CNIdentity.listSubjects` services.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="SubjectInfo">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:Person" name="person" minOccurs="0" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:Group" name="group" minOccurs="0" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectInfo", propOrder = {
    "person",
    "group"
})
@XmlRootElement( name = "subjectInfo" )
public class SubjectInfo implements Serializable {

    protected List<Person> person = new ArrayList<Person>();
    protected List<Group> group = new ArrayList<Group>();

    private static final long serialVersionUID = 10000000;

    /** 
     * Get the list of 'person' element items.
     * 
     * @return list
     */
    public List<Person> getPersonList() {
        return person;
    }

    /** 
     * Set the list of 'person' element items.
     * 
     * @param list
     */
    public void setPersonList(List<Person> list) {
        person = list;
    }

    /** 
     * Get the number of 'person' element items.
     * @return count
     */
    public int sizePersonList() {
        if (person == null) {
            person = new ArrayList<Person>();
        }
        return person.size();
    }

    /** 
     * Add a 'person' element item.
     * @param item
     */
    public void addPerson(Person item) {
        if (person == null) {
            person = new ArrayList<Person>();
        }
        person.add(item);
    }

    /** 
     * Get 'person' element item by position.
     * @return item
     * @param index
     */
    public Person getPerson(int index) {
        if (person == null) {
            person = new ArrayList<Person>();
        }
        return person.get(index);
    }

    /** 
     * Remove all 'person' element items.
     */
    public void clearPersonList() {
        if (person == null) {
            person = new ArrayList<Person>();
        }
        person.clear();
    }

    /** 
     * Get the list of 'group' element items.
     * 
     * @return list
     */
    public List<Group> getGroupList() {
        return group;
    }

    /** 
     * Set the list of 'group' element items.
     * 
     * @param list
     */
    public void setGroupList(List<Group> list) {
        group = list;
    }

    /** 
     * Get the number of 'group' element items.
     * @return count
     */
    public int sizeGroupList() {
        if (group == null) {
            group = new ArrayList<Group>();
        }
        return group.size();
    }

    /** 
     * Add a 'group' element item.
     * @param item
     */
    public void addGroup(Group item) {
        if (group == null) {
            group = new ArrayList<Group>();
        }
        group.add(item);
    }

    /** 
     * Get 'group' element item by position.
     * @return item
     * @param index
     */
    public Group getGroup(int index) {
        if (group == null) {
            group = new ArrayList<Group>();
        }
        return group.get(index);
    }

    /** 
     * Remove all 'group' element items.
     */
    public void clearGroupList() {
        if (group == null) {
            group = new ArrayList<Group>();
        }
        group.clear();
    }
}
