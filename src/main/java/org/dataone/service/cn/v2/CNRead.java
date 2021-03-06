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

import java.io.InputStream;
import java.util.Date;

import org.dataone.service.exceptions.InvalidRequest;
import org.dataone.service.exceptions.InvalidToken;
import org.dataone.service.exceptions.NotAuthorized;
import org.dataone.service.exceptions.NotFound;
import org.dataone.service.exceptions.NotImplemented;
import org.dataone.service.exceptions.ServiceFailure;
import org.dataone.service.types.v1.Checksum;
import org.dataone.service.types.v1.DescribeResponse;
import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.NodeReference;
import org.dataone.service.types.v1.ObjectFormatIdentifier;
import org.dataone.service.types.v1.ObjectList;
import org.dataone.service.types.v1.ObjectLocationList;
import org.dataone.service.types.v1.Session;
import org.dataone.service.types.v1_1.QueryEngineDescription;
import org.dataone.service.types.v1_1.QueryEngineList;
import org.dataone.service.types.v2.SystemMetadata;

/**
 * The DataONE CoordinatingNode Tier1 Read interface.  This defines an
 * implementation interface for Coordinating Nodes that wish to build an
 * implementation that is compliant with the DataONE service definitions.
 *
 * @author Matthew Jones
 */
public interface CNRead 
{
    /**
     * InputStream is the Java native version of D1's OctetStream
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.get
     */
    public InputStream get(Session session, Identifier id)
        throws InvalidToken, ServiceFailure, NotAuthorized, NotFound, NotImplemented;

    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.getSystemMetadata
     */
    public SystemMetadata getSystemMetadata(Session session, Identifier id)
        throws InvalidToken, ServiceFailure, NotAuthorized, NotFound, NotImplemented;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/MN_APIs.html#MN_read.describe
     */
    public DescribeResponse describe(Session session, Identifier id)
    throws InvalidToken, NotAuthorized, NotImplemented, ServiceFailure, NotFound;
    

    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.resolve
     */
    public ObjectLocationList resolve(Session session, Identifier id)
        throws InvalidToken, ServiceFailure, NotAuthorized, NotFound, NotImplemented;
    
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.getChecksum
     */
    public Checksum getChecksum(Session session, Identifier pid)
        throws InvalidToken, ServiceFailure, NotAuthorized, NotFound, NotImplemented;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.listObjects
     */
    public ObjectList listObjects(Session session, Date fromDate, 
            Date toDate, ObjectFormatIdentifier formatId, NodeReference nodeId, Identifier identifier,
            Integer start, Integer count) 
    throws InvalidRequest, InvalidToken, NotAuthorized, NotImplemented, ServiceFailure;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.search
     */
    public ObjectList search(Session session, String queryType, String query)
        throws InvalidToken, ServiceFailure, NotAuthorized, InvalidRequest, 
        NotImplemented;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.query
     */
    public InputStream query(Session session, String queryEngine, String query)
        throws InvalidToken, ServiceFailure, NotAuthorized, InvalidRequest, 
        NotImplemented, NotFound;

    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.getQueryEngineDescription
     */
    public QueryEngineDescription getQueryEngineDescription(Session session, String queryEngine)
        throws InvalidToken, ServiceFailure, NotAuthorized, NotImplemented, NotFound;

    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNRead.listQueryEngines
     */
    public QueryEngineList listQueryEngines(Session session)
        throws InvalidToken, ServiceFailure, NotAuthorized, NotImplemented;
   
}