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
 */

package org.dataone.service.cn.v2;

import org.dataone.service.exceptions.InvalidRequest;
import org.dataone.service.exceptions.InvalidToken;
import org.dataone.service.exceptions.NotAuthorized;
import org.dataone.service.exceptions.NotFound;
import org.dataone.service.exceptions.NotImplemented;
import org.dataone.service.exceptions.ServiceFailure;
import org.dataone.service.exceptions.IdentifierNotUnique;

import org.dataone.service.types.v2.Node;
import org.dataone.service.types.v1.NodeReference;
import org.dataone.service.types.v1.Session;

/**
 * The DataONE CoordinatingNode Tier2 Registration interface.  This defines an
 * implementation interface for Coordinating Nodes that wish to build an
 * implementation that is compliant with the DataONE service definitions.
 *
 * @author Matthew Jones
 */
public interface CNRegister {

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRegister.updateNodeCapabilities
     */
    public boolean updateNodeCapabilities(Session session, NodeReference nodeid, 
        Node node) throws NotImplemented, NotAuthorized, 
        ServiceFailure, InvalidRequest, NotFound, InvalidToken;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRegister.register
     */
    public NodeReference register(Session session, Node node)
        throws NotImplemented, NotAuthorized, ServiceFailure, InvalidRequest, 
        InvalidToken, IdentifierNotUnique;
    
    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRegister.getNodeCapabilities
     */
    public Node getNodeCapabilities(NodeReference nodeid) throws NotImplemented, 
        ServiceFailure, InvalidRequest, NotFound;

}
