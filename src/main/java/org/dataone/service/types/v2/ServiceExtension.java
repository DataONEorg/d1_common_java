
package org.dataone.service.types.v2;

import java.io.Serializable;

/** 
 * 
 The goal of Member Node Service Registration (MNSR) is to assist clients in discovery 
 of services exposed by Member Nodes that may be applicable to a particular type of object

 * 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="http://ns.dataone.org/service/types/v2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="ServiceExtension">
 *   &lt;xs:sequence>
 *     &lt;xs:element type="xs:string" name="name"/>
 *     &lt;xs:element type="xs:string" name="type" minOccurs="0"/>
 *     &lt;xs:element type="xs:string" name="description" minOccurs="0"/>
 *     &lt;xs:element type="xs:string" name="connection" minOccurs="0"/>
 *     &lt;xs:element type="ns:FormatList" name="formats" minOccurs="0" maxOccurs="1"/>
 *   &lt;/xs:sequence>
 * &lt;/xs:complexType>
 * </pre>
 */
public class ServiceExtension implements Serializable
{
    private static final long serialVersionUID = 10000000;
    private String name;
    private String type;
    private String description;
    private String connection;
    private FormatList formats;

    /** 
     * Get the 'name' element value. Descriptive human-readable name of the service.
    				
     * 
     * @return value
     */
    public String getName() {
        return name;
    }

    /** 
     * Set the 'name' element value. Descriptive human-readable name of the service.
    				
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Get the 'type' element value. Unique Service Type identifier.
    				
     * 
     * @return value
     */
    public String getType() {
        return type;
    }

    /** 
     * Set the 'type' element value. Unique Service Type identifier.
    				
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** 
     * Get the 'description' element value. Brief human readable description of service to be
                          presented in user interfaces.
    				
     * 
     * @return value
     */
    public String getDescription() {
        return description;
    }

    /** 
     * Set the 'description' element value. Brief human readable description of service to be
                          presented in user interfaces.
    				
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 
     * Get the 'connection' element value. Endpoint where the service is available
    				
     * 
     * @return value
     */
    public String getConnection() {
        return connection;
    }

    /** 
     * Set the 'connection' element value. Endpoint where the service is available
    				
     * 
     * @param connection
     */
    public void setConnection(String connection) {
        this.connection = connection;
    }

    /** 
     * Get the 'formats' element value. A list of formats supported by the service.
    				
     * 
     * @return value
     */
    public FormatList getFormats() {
        return formats;
    }

    /** 
     * Set the 'formats' element value. A list of formats supported by the service.
    				
     * 
     * @param formats
     */
    public void setFormats(FormatList formats) {
        this.formats = formats;
    }
}
