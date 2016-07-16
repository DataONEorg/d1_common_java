
package org.dataone.service.types.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/** 
 * An enumerated string value indicating the current
 state of a replica of an object. When an object identified needs to be
 replicated, it is added to the replication task queue and is marked as
 *queued*; a CN will pick up that task and request that it be replicated
 to a MN and marks that it as *requested*; when a MN finishes replicating
 the object, it informs the CN that it is finished and it is marked as
 *completed*. If an MN is unable to complete replication, the
 replication status is marked as *failed*.Periodically a CN checks each replica to be sure it is
 both available and valid (matching checksum with original), and if it is
 either inaccessible or invalid then it marks it as *invalidated*, which
 indicates that the object replication needs to be invoked
 again.The replication process is described in Use Case 09 
 (:doc:`/design/UseCases/09_uc`).
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ReplicationStatus">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="queued"/>
 *     &lt;xs:enumeration value="requested"/>
 *     &lt;xs:enumeration value="completed"/>
 *     &lt;xs:enumeration value="failed"/>
 *     &lt;xs:enumeration value="invalidated"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
@XmlType(name = "ReplicationStatus")
@XmlEnum
public enum ReplicationStatus implements Serializable {

    @XmlEnumValue("queued")
    QUEUED("queued"),
    @XmlEnumValue("requested")
    REQUESTED("requested"),
    @XmlEnumValue("completed")
    COMPLETED("completed"),
    @XmlEnumValue("failed")
    FAILED("failed"),
    @XmlEnumValue("invalidated")
    INVALIDATED("invalidated");
    private final String value;
    
    private static final long serialVersionUID = 10000000;


    // private 
    ReplicationStatus(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static ReplicationStatus convert(String value) {
        for (ReplicationStatus inst : values()) {
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
//    public static ReplicationStatus fromValue(String v) {
//        for (ReplicationStatus c: ReplicationStatus.values()) {
//            if (c.value.equals(v)) {
//                return c;
//            }
//        }
//        throw new IllegalArgumentException(v);
//    }
}
