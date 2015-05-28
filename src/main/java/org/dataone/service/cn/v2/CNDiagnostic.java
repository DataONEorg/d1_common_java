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

import org.dataone.service.exceptions.IdentifierNotUnique;
import org.dataone.service.exceptions.InsufficientResources;
import org.dataone.service.exceptions.InvalidRequest;
import org.dataone.service.exceptions.InvalidSystemMetadata;
import org.dataone.service.exceptions.InvalidToken;
import org.dataone.service.exceptions.NotAuthorized;
import org.dataone.service.exceptions.NotImplemented;
import org.dataone.service.exceptions.ServiceFailure;
import org.dataone.service.exceptions.UnsupportedMetadataType;
import org.dataone.service.exceptions.UnsupportedType;
import org.dataone.service.types.v1.Session;
import org.dataone.service.types.v1.SubjectInfo;
import org.dataone.service.types.v2.SystemMetadata;

/**
 * The DataONE CoordinatingNode Tier1 Diagnostic interface.  This defines an
 * implementation interface for Coordinating Nodes that wish to provide
 * a number of diagnostic calls.
 */
public interface CNDiagnostic {

    public SubjectInfo echoCredentials(Session session) throws NotImplemented, ServiceFailure,
            InvalidToken;

    public SystemMetadata echoSystemMetadata(Session session, SystemMetadata sysmeta)
            throws NotImplemented, ServiceFailure, NotAuthorized, InvalidToken, InvalidRequest,
            IdentifierNotUnique, InvalidSystemMetadata;

    public InputStream echoIndexedObject(Session session, String queryEngine,
            SystemMetadata sysmeta, InputStream object) throws NotImplemented, ServiceFailure,
            NotAuthorized, InvalidToken, InvalidRequest, InvalidSystemMetadata, UnsupportedType,
            UnsupportedMetadataType, InsufficientResources;

}
