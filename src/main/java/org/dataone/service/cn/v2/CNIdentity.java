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

import org.dataone.service.exceptions.IdentifierNotUnique;
import org.dataone.service.exceptions.InvalidCredentials;
import org.dataone.service.exceptions.InvalidRequest;
import org.dataone.service.exceptions.InvalidToken;
import org.dataone.service.exceptions.NotAuthorized;
import org.dataone.service.exceptions.NotFound;
import org.dataone.service.exceptions.NotImplemented;
import org.dataone.service.exceptions.ServiceFailure;

import org.dataone.service.types.v1.Group;
import org.dataone.service.types.v1.Person;
import org.dataone.service.types.v1.Session;
import org.dataone.service.types.v1.Subject;
import org.dataone.service.types.v1.SubjectInfo;

/**
 * The DataONE CoordinatingNode Tier2 Identity interface.  This defines an
 * implementation interface for Coordinating Nodes that wish to build an
 * implementation that is compliant with the DataONE service definitions.
 *
 * @author Matthew Jones
 */
public interface CNIdentity {

    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.registerAccount
     */
    public Subject registerAccount(Session session, Person person) 
        throws ServiceFailure, NotAuthorized, IdentifierNotUnique, InvalidCredentials, 
        NotImplemented, InvalidRequest, InvalidToken;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.updateAccount
     */
    public Subject updateAccount(Session session, Person person) 
    	throws ServiceFailure, NotAuthorized, InvalidCredentials, 
        NotImplemented, InvalidRequest, InvalidToken, NotFound;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.verifyAccount
     */
    public boolean verifyAccount(Session session, Subject subject) 
        throws ServiceFailure, NotAuthorized, NotImplemented, InvalidToken, 
        InvalidRequest, NotFound;

    /** 
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.getSubjectInfo
     */
    public SubjectInfo getSubjectInfo(Session session, Subject subject)
        throws ServiceFailure, NotAuthorized, NotImplemented, NotFound, InvalidToken;
  
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.listSubjects
     */
    public SubjectInfo listSubjects(Session session, String query, String status, Integer start, 
        Integer count) throws InvalidRequest, ServiceFailure, InvalidToken, NotAuthorized, 
        NotImplemented;
        
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.mapIdentity
     */
    public boolean mapIdentity(Session session, Subject primarySubject, Subject secondarySubject) 
        throws ServiceFailure, InvalidToken, NotAuthorized, NotFound, 
        NotImplemented, InvalidRequest, IdentifierNotUnique;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.requestMapIdentity
     */
    public boolean requestMapIdentity(Session session, Subject subject) 
        throws ServiceFailure, InvalidToken, NotAuthorized, NotFound, 
        NotImplemented, InvalidRequest, IdentifierNotUnique;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.confirmMapIdentity
     */
    public boolean confirmMapIdentity(Session session, Subject subject) 
        throws ServiceFailure, InvalidToken, NotAuthorized, NotFound, 
        NotImplemented;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.getPendingMapIdentity
     */
    public SubjectInfo getPendingMapIdentity(Session session, Subject subject) 
        throws ServiceFailure, InvalidToken, NotAuthorized, NotFound, 
        NotImplemented;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.denyMapIdentity
     */
    public boolean denyMapIdentity(Session session, Subject subject) 
        throws ServiceFailure, InvalidToken, NotAuthorized, NotFound, 
        NotImplemented;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.removeMapIdentity
     */
    public boolean removeMapIdentity(Session session, Subject subject) 
        throws ServiceFailure, InvalidToken, NotAuthorized, NotFound, 
        NotImplemented;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.createGroup
     */
    public Subject createGroup(Session session, Group group) 
        throws ServiceFailure, InvalidToken, NotAuthorized, NotImplemented, IdentifierNotUnique, InvalidRequest;
    
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNIdentity.updateGroup
     */
    public boolean updateGroup(Session session, Group group) 
        throws ServiceFailure, InvalidToken, NotAuthorized, NotFound, NotImplemented, InvalidRequest;
 

}
