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

package org.dataone.service.cn.v1;

import org.dataone.service.exceptions.BaseException;
import org.dataone.service.exceptions.InvalidRequest;
import org.dataone.service.exceptions.InvalidToken;
import org.dataone.service.exceptions.NotAuthorized;
import org.dataone.service.exceptions.NotFound;
import org.dataone.service.exceptions.NotImplemented;
import org.dataone.service.exceptions.ServiceFailure;
import org.dataone.service.exceptions.VersionMismatch;
import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.NodeReference;
import org.dataone.service.types.v1.Replica;
import org.dataone.service.types.v1.ReplicationPolicy;
import org.dataone.service.types.v1.ReplicationStatus;
import org.dataone.service.types.v1.Session;
import org.dataone.service.types.v1.Subject;

/**
 * The DataONE CoordinatingNode Tier4 Replication interface.  This defines an
 * implementation interface for Coordinating Nodes that wish to build an
 * implementation that is compliant with the DataONE service definitions.
 *
 * @author Matthew Jones
 */
public interface CNReplication {

    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.setReplicationStatus
     */
	public boolean setReplicationStatus(Identifier pid, 
		NodeReference nodeRef, ReplicationStatus status, BaseException failure) 
		throws ServiceFailure, NotImplemented, InvalidToken, NotAuthorized, 
		InvalidRequest, NotFound;

    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.setReplicationPolicy
     */
    public boolean setReplicationPolicy(Identifier pid, 
        ReplicationPolicy policy, long serialVersion) 
        throws NotImplemented, NotFound, NotAuthorized, ServiceFailure, 
        InvalidRequest, InvalidToken, VersionMismatch;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.isNodeAuthorized
     */
    public boolean isNodeAuthorized(Subject targetNodeSubject, Identifier pid)
        throws NotImplemented, NotAuthorized, InvalidToken, ServiceFailure, 
        NotFound, InvalidRequest;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.updateReplicationMetadata
     */
    public boolean updateReplicationMetadata(Identifier pid, Replica replicaMetadata, long serialVersion)
        throws NotImplemented, NotAuthorized, ServiceFailure, 
        NotFound, InvalidRequest, InvalidToken, VersionMismatch;
    
    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.deleteReplicationMetadata
     */
    public boolean deleteReplicationMetadata(Identifier pid, NodeReference nodeId, long serialVersion) 
  		throws InvalidToken, InvalidRequest, ServiceFailure, NotAuthorized, NotFound, NotImplemented, VersionMismatch;

//////    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.setReplicationStatus
     */
    @Deprecated
	public boolean setReplicationStatus(Session session, Identifier pid, 
		NodeReference nodeRef, ReplicationStatus status, BaseException failure) 
		throws ServiceFailure, NotImplemented, InvalidToken, NotAuthorized, 
		InvalidRequest, NotFound;

    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.setReplicationPolicy
     */
    @Deprecated
	public boolean setReplicationPolicy(Session session, Identifier pid, 
        ReplicationPolicy policy, long serialVersion) 
        throws NotImplemented, NotFound, NotAuthorized, ServiceFailure, 
        InvalidRequest, InvalidToken, VersionMismatch;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.isNodeAuthorized
     */
    @Deprecated
	public boolean isNodeAuthorized(Session originatingNodeSession, 
        Subject targetNodeSubject, Identifier pid)
        throws NotImplemented, NotAuthorized, InvalidToken, ServiceFailure, 
        NotFound, InvalidRequest;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.updateReplicationMetadata
     */
    @Deprecated
	public boolean updateReplicationMetadata(Session targetNodeSession, 
        Identifier pid, Replica replicaMetadata, long serialVersion)
        throws NotImplemented, NotAuthorized, ServiceFailure, 
        NotFound, InvalidRequest, InvalidToken, VersionMismatch;
    
    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNReplication.deleteReplicationMetadata
     */
    @Deprecated
	public boolean deleteReplicationMetadata(Session session, Identifier pid, NodeReference nodeId, long serialVersion) 
  		throws InvalidToken, InvalidRequest, ServiceFailure, NotAuthorized, NotFound, NotImplemented, VersionMismatch;

    
}
