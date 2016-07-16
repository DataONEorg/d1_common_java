
package org.dataone.service.types.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/** 
 * The controlled list of events that are logged, which
 will include *create*, *update*, *delete*, *read*, *replicate*,
 *synchronization_failed* and *replication_failed*
 events.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="Event">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="create"/>
 *     &lt;xs:enumeration value="read"/>
 *     &lt;xs:enumeration value="update"/>
 *     &lt;xs:enumeration value="delete"/>
 *     &lt;xs:enumeration value="replicate"/>
 *     &lt;xs:enumeration value="synchronization_failed"/>
 *     &lt;xs:enumeration value="replication_failed"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
@XmlType(name = "Event")
@XmlEnum
public enum Event implements Serializable {

    @XmlEnumValue("create")
    CREATE("create"),
    @XmlEnumValue("read")
    READ("read"),
    @XmlEnumValue("update")
    UPDATE("update"),
    @XmlEnumValue("delete")
    DELETE("delete"),
    @XmlEnumValue("replicate")
    REPLICATE("replicate"),
    @XmlEnumValue("synchronization_failed")
    SYNCHRONIZATION_FAILED("synchronization_failed"),
    @XmlEnumValue("replication_failed")
    REPLICATION_FAILED("replication_failed");
    private final String value;

    private static final long serialVersionUID = 10000000;

    // private 
    Event(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static Event convert(String value) {
        for (Event inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
    
//    public String value() {
//        return value;
//    }
//
//    public static Event fromValue(String v) {
//        for (Event c: Event.values()) {
//            if (c.value.equals(v)) {
//                return c;
//            }
//        }
//        throw new IllegalArgumentException(v);
//    }
}
