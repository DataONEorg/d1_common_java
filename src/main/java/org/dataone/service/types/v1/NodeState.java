
package org.dataone.service.types.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/** 
 * An indicator of the current node accessibility. Nodes
 that are marked *down* are inaccessible for service operations, those
 that are *up* are in the normal accessible state, and *unknown*
 indicates that the state has not been determined yet.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="NodeState">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="up"/>
 *     &lt;xs:enumeration value="down"/>
 *     &lt;xs:enumeration value="unknown"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */

@XmlType(name = "NodeState")
@XmlEnum
public enum NodeState implements Serializable {

    @XmlEnumValue("up")
    UP("up"),
    @XmlEnumValue("down")
    DOWN("down"),
    @XmlEnumValue("unknown")
    UNKNOWN("unknown");
    private final String value;

    private static final long serialVersionUID = 10000000;


    // private 
    NodeState(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static NodeState convert(String value) {
        for (NodeState inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
    
//    public String value() {
//    return value;
//    }
//
//    public static NodeState fromValue(String v) {
//        for (NodeState c: NodeState.values()) {
//            if (c.value.equals(v)) {
//                return c;
//            }
//        }
//        throw new IllegalArgumentException(v);
//    }
}
