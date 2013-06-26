/**
 * This work was created by participants in the DataONE project, and is
 * jointly copyrighted by participating institutions in DataONE. For 
 * more information on DataONE, see our web site at http://dataone.org.
 *
 *   Copyright ${year}
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 * 
 * $Id$
 */

package org.dataone.service.types.v1.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dataone.configuration.Settings;
import org.dataone.service.types.v1.Node;
import org.dataone.service.types.v1.NodeState;
import org.dataone.service.types.v1.NodeType;
import org.dataone.service.types.v1.Service;
import org.dataone.service.types.v1.ServiceMethodRestriction;
import org.dataone.service.types.v1.Subject;

/**
 *
 * Methods for interpreting ServiceMethodRestriction rules
 *
 * @author leinfelder
 *
 */
public class ServiceMethodRestrictionUtil {
	
	private static final Logger logger = Logger.getLogger(ServiceMethodRestrictionUtil.class);

	/**
	 * Interprets the CN's ServiceMethodRestriction for a given Subject+serviceName+methodName
	 * @param subject the subject that may be allowed to invoke the service method
	 * @param nodeList the nodelist to use as reference for the restrictions
	 * @param serviceName the name of the CN service
	 * @param methodName the name of the method having the restriction rules
	 * @return true if allowed, otherwise false
	 */
    public static boolean isMethodAllowed(Subject subject, List<Node> nodeList, String serviceName, String methodName) {
    	// checks if we are allowed to call this method -- should be restricted to "admins" and other whitelisted identities
        boolean isAllowed = false;
        if (subject != null) {
	        List<String> admins = getCnAdministrativeList(nodeList, serviceName, methodName);
	        isAllowed = admins.contains(subject.getValue());
        }
        return isAllowed;
    }
    
    /**
     * Returns an array of subjects listed as CN's in the nodelist or in a properties file.
     * If the ServiceMethodRestriction list of the given method is set, 
     * then those subjects act as administrators as well.
     * 
     * @author waltz
     * @param nodeList
     * @param serviceName 
     * @param methodName 
     * 
     * @return List<String>
     */
    public static List<String> getCnAdministrativeList(List<Node> nodeList, String serviceName, String methodName) {
        List<String> administrators = new ArrayList<String>();

        List<String> administratorsProperties = Settings.getConfiguration().getList("cn.administrators");
        if (administrators != null) {
            for (String administrator : administratorsProperties) {
                logger.debug("AdminList entry " + administrator);
                administrators.add(administrator);
            }
        }

        for (Node node: nodeList) {
            if (node.getType().equals(NodeType.CN) && node.getState().equals(NodeState.UP)) {
                for (Subject adminstrativeSubject : node.getSubjectList()) {
                     administrators.add(adminstrativeSubject.getValue());
                }
                List<Service> cnServices = node.getServices().getServiceList();
                for (Service service : cnServices) {
                    if (service.getName().equalsIgnoreCase(serviceName)) {
                        if ((service.getRestrictionList() != null)
                                && !service.getRestrictionList().isEmpty()) {
                            List<ServiceMethodRestriction> serviceMethodRestrictionList = service.getRestrictionList();
                            for (ServiceMethodRestriction serviceMethodRestriction : serviceMethodRestrictionList) {
                                if (serviceMethodRestriction.getMethodName().equalsIgnoreCase(methodName)) {
                                    if (serviceMethodRestriction.getSubjectList() != null) {
                                           for (Subject administrator : serviceMethodRestriction.getSubjectList()) {
                                                logger.debug("Adding ServiceMethodRestriction entry for: " + administrator);
                                                administrators.add(administrator.getValue());
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return administrators;
    }
}
