package org.dataone.service.types.v2.util;

import org.dataone.service.types.v2.Node;
import org.dataone.service.types.v2.NodeList;
import org.dataone.service.types.v1.Service;
import org.dataone.service.types.v2.SystemMetadata;

public class AuthUtils extends org.dataone.service.types.v1.util.AuthUtils {

    
    /**
     * the CN is the authority for systemMetadata updates only for v1 MNs that
     * implement MNStorage services.  This method is used primarily by the CN
     * and ITK clients to know which node and methods to use to update SystemMetadata.
     * (It is assumed that a MemberNode knows if it acts as an authority or not.)
     * 
     * @param nodelist
     * @param systemMetadata
     * @return true if the authoritativeMN in the systemMetadata is the authority.
     *    Otherwise, the CN is the authority.
     */
    public static boolean isCNAuthorityForSystemMetadataUpdate(NodeList nodelist, SystemMetadata systemMetadata) {
        Node authMN = NodelistUtil.findNode(nodelist, systemMetadata.getAuthoritativeMemberNode());

        boolean v1MNStorageAvailable = false;

        for (Service service: authMN.getServices().getServiceList())
        {   
            if (service.getAvailable()) {
                if (service.getVersion().equalsIgnoreCase("v1")) {
                    if (service.getName().equalsIgnoreCase("MNStorage")) {
                        v1MNStorageAvailable = true;
                    }
                } 
                else if ( service.getVersion().matches("^[v|V](\\d+)$")) {
                    // there is a greater-than v1 service running
                    return false;
                }
            }
        }
        return v1MNStorageAvailable;
    }

}
