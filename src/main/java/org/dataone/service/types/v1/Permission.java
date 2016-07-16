
package org.dataone.service.types.v1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**                                                                                                                                                                                    
 * <p>Java class for Permission.                                                                                                                                                       
 * A string value indicating the set of actions that can
 * be performed on a resource as specified in an access policy. The set of
 * permissions include the ability to read a resource (*read*), modify a
 * resource (*write*), and to change the set of access control policies for
 * a resource (*changePermission*). Permission levels are cumulative, in
 * that write permission implicitly grants read access, and
 * changePermission permission implicitly grants write access (and
 * therefore read as well). If a subject is granted multiple permissions,
 * the highest level of access applies.                                                                                                                                                
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.                                                                                        
 * <p>                                                                                                                                                                                 
 * <pre>                                                                                                                                                                               
 * &lt;simpleType name="Permission">                                                                                                                                                   
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">                                                                                                                  
 *     &lt;enumeration value="read"/>                                                                                                                                                  
 *     &lt;enumeration value="write"/>                                                                                                                                                 
 *     &lt;enumeration value="changePermission"/>                                                                                                                                      
 *   &lt;/restriction>                                                                                                                                                                 
 * &lt;/simpleType>                                                                                                                                                                    
 * </pre>                                                                                                                                                                              
 *                                                                                                                                                                                     
 */
@XmlType(name = "Permission")
@XmlEnum
public enum Permission implements Serializable {
    @XmlEnumValue("read")
    READ("read"),
    @XmlEnumValue("write")
    WRITE("write"),
    @XmlEnumValue("changePermission")
    CHANGE_PERMISSION("changePermission");
    private final String value;

    private static final long serialVersionUID = 10000000;

    // the jaxb generated classes didn't privatize the constructor
    // private 
    Permission(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static Permission convert(String value) {
        for (Permission inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
    
//  FROM THE jaxb generated classes - not sure if they are needed.
//    public String value() {
//        return value;
//    }
//
//    public static Permission fromValue(String v) {
//        for (Permission c: Permission.values()) {
//            if (c.value.equals(v)) {
//                return c;
//            }
//        }
//        throw new IllegalArgumentException(v);
//    }
}
