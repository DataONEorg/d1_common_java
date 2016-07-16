
package org.dataone.service.types.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/** 
 * The type of this node, which is either *mn* for
 Member Nodes, or *cn* for Coordinating Nodes.
 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="http://ns.dataone.org/service/types/v1" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="NodeType">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="mn"/>
 *     &lt;xs:enumeration value="cn"/>
 *     &lt;xs:enumeration value="Monitor"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
@XmlType(name = "NodeType")
@XmlEnum
public enum NodeType implements Serializable {
   
    @XmlEnumValue("mn")
    MN("mn"),
    @XmlEnumValue("cn")
    CN("cn"),
    @XmlEnumValue("Monitor")
    MONITOR("Monitor");
    private final String value;
    
    private static final long serialVersionUID = 10000000;

//    private 
    NodeType(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static NodeType convert(String value) {
        for (NodeType inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }

    // FROM the JAXB generated classes
//    public String value() {
//        return value;
//    }
//
//    public static NodeType fromValue(String v) {
//        for (NodeType c: NodeType.values()) {
//            if (c.value.equals(v)) {
//                return c;
//            }
//        }
//        throw new IllegalArgumentException(v);
//    }

}
