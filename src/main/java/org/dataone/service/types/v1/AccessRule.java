
package org.dataone.service.types.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/** 
 * A rule that is used to allow a :term:`subject` to
 perform an action (such as read or write) on an object in DataONE. Rules
 are tuples (subject, permission) specifying which permissions are
 allowed for the subjects(s). If a subject is granted multiple
 permissions, the highest level of access applies. The resource on which
 the access control rules are being applied is determined by the
 containing :term:`SystemMetadata` document, or in the case of methods
 such as :func:`CNAuthorization.setAccessPolicy`, by the :term:`pid` in
 the method parameters.Access control rules are specified by the
 :term:`Origin Member Node` when the object is first registered in
 DataONE. If no rules are specified at that time, then the object is
 deemed to be private and the only user with access to the object (read,
 write, or otherwise) is the :term:`Rights Holder`.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="AccessRule">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="ns:Subject" name="subject" minOccurs="1" maxOccurs="unbounded"/>
 *     &lt;xs:element type="ns:Permission" name="permission" minOccurs="1" maxOccurs="unbounded"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessRule", propOrder = {
    "subject",
    "permission"
})
@XmlRootElement(name = "accessRule")
public class AccessRule implements Serializable
{
    @XmlElement(required = true)
    protected List<Subject> subject = new ArrayList<Subject>();
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected List<Permission> permission = new ArrayList<Permission>();

    private static final long serialVersionUID = 10000001;

    /** 
     * Get the list of 'subject' element items.
     * 
     * @return list
     */
    public List<Subject> getSubjectList() {
        return subject;
    }
    
    /* Same as getSubjectList, but for use with Serializer
       The method will produce a null return if an empty
       subject array has been created, but nothing added
       https://redmine.dataone.org/issues/7422
    */
        public List<Subject> grabSubjectListNullIfEmpty() {
        if (subject != null && subject.isEmpty()) {
                return null;
        }
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

    /** 
     * Get the list of 'permission' element items.
     * 
     * @return list
     */
    public List<Permission> getPermissionList() {
        return permission;
    }

    /* Same as getPermissionList, but for use with Serializer
       The method will produce a null return if an empty
       permission array has been created, but nothing added
       https://redmine.dataone.org/issues/7422
    */
    public List<Permission> grabPermissionListNullIfEmpty() {
        if (permission != null && permission.isEmpty()) {
                return null;
        }
        return permission;
    }
    /** 
     * Set the list of 'permission' element items.
     * 
     * @param list
     */
    public void setPermissionList(List<Permission> list) {
        permission = list;
    }

    /** 
     * Get the number of 'permission' element items.
     * @return count
     */
    public int sizePermissionList() {
        if (permission == null) {
            permission = new ArrayList<Permission>();
        }
        return permission.size();
    }

    /** 
     * Add a 'permission' element item.
     * @param item
     */
    public void addPermission(Permission item) {
        if (permission == null) {
            permission = new ArrayList<Permission>();
        }
        permission.add(item);
    }

    /** 
     * Get 'permission' element item by position.
     * @return item
     * @param index
     */
    public Permission getPermission(int index) {
        if (permission == null) {
            permission = new ArrayList<Permission>();
        }
        return permission.get(index);
    }

    /** 
     * Remove all 'permission' element items.
     */
    public void clearPermissionList() {
        if (permission == null) {
            permission = new ArrayList<Permission>();
        }
        permission.clear();
    }
}
