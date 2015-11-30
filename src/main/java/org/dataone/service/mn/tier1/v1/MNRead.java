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

package org.dataone.service.mn.tier1.v1;

import java.io.InputStream;
import java.util.Date;

import org.dataone.service.exceptions.InsufficientResources;
import org.dataone.service.exceptions.InvalidRequest;
import org.dataone.service.exceptions.InvalidToken;
import org.dataone.service.exceptions.NotAuthorized;
import org.dataone.service.exceptions.NotFound;
import org.dataone.service.exceptions.NotImplemented;
import org.dataone.service.exceptions.ServiceFailure;
import org.dataone.service.exceptions.SynchronizationFailed;

import org.dataone.service.types.v1.ObjectFormatIdentifier;
import org.dataone.service.types.v1.Session;
import org.dataone.service.types.v1.SystemMetadata;
// this is the one type that must be manually copied when creating a new version
import org.dataone.service.types.v1.DescribeResponse;
import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.Checksum;
import org.dataone.service.types.v1.ObjectList;


/**
 * The DataONE Member Node Tier 1 Read interface.  This defines an
 * implementation interface for Member Nodes that wish to build an
 * implementation that is compliant with the DataONE service definitions.
 *
 * @author Matthew Jones
 */
public interface MNRead {

    /**
     * InputStream is the Java native version of D1's OctetStream
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.listObjects
     *
     */
    public InputStream get(Identifier pid)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, 
        NotFound, InsufficientResources;


    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.getSystemMetadata
     */
    public SystemMetadata getSystemMetadata(Identifier pid)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, NotFound;


    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.describe
     */
    public DescribeResponse describe(Identifier pid)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, NotFound;
    

    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.getChecksum
     */
    public Checksum getChecksum(Identifier pid, String checksumAlgorithm)
    throws InvalidRequest, InvalidToken, NotAuthorized, NotImplemented, ServiceFailure,
         NotFound;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.listObjects
     */
    public ObjectList listObjects(Date fromDate, 
            Date toDate, ObjectFormatIdentifier formatid, Boolean replicaStatus,
            Integer start, Integer count) 
    throws InvalidRequest, InvalidToken, NotAuthorized, NotImplemented, ServiceFailure;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.synchronizationFailed
     */
    public boolean synchronizationFailed(SynchronizationFailed message)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MNRead.getReplica
     */
    public InputStream getReplica(Identifier pid)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, NotFound,
    InsufficientResources;
 
    
    ///////
    
    /**
     * InputStream is the Java native version of D1's OctetStream
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.listObjects
     *
     */
    @Deprecated
    public InputStream get(Session session, Identifier pid)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, 
        NotFound, InsufficientResources;


    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.getSystemMetadata
     */
    @Deprecated
    public SystemMetadata getSystemMetadata(Session session, Identifier pid)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, NotFound;


    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.describe
     */
    @Deprecated
    public DescribeResponse describe(Session session, Identifier pid)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, NotFound;
    

    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.getChecksum
     */
    @Deprecated
    public Checksum getChecksum(Session session, Identifier pid, String checksumAlgorithm)
    throws InvalidRequest, InvalidToken, NotAuthorized, NotImplemented, ServiceFailure,
         NotFound;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.listObjects
     */
    @Deprecated
    public ObjectList listObjects(Session session, Date fromDate, 
            Date toDate, ObjectFormatIdentifier formatid, Boolean replicaStatus,
            Integer start, Integer count) 
    throws InvalidRequest, InvalidToken, NotAuthorized, NotImplemented, ServiceFailure;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.synchronizationFailed
     */
    @Deprecated
    public boolean synchronizationFailed(Session session, SynchronizationFailed message)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MNRead.getReplica
     */
	@Deprecated
    public InputStream getReplica(Session session, Identifier pid)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, NotFound,
    InsufficientResources;
    
    
}
