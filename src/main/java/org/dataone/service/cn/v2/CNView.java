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

import org.dataone.service.exceptions.InvalidRequest;
import org.dataone.service.exceptions.InvalidToken;
import org.dataone.service.exceptions.NotAuthorized;
import org.dataone.service.exceptions.NotFound;
import org.dataone.service.exceptions.NotImplemented;
import org.dataone.service.exceptions.ServiceFailure;
import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.Session;
import org.dataone.service.types.v2.OptionList;

/**
 * The DataONE Coordinating Node View interface for rendering metadata 
 * in user-friendly format[s] as described by the listing of available views.
 *
 * @author leinfelder
 */
public interface CNView 
{   
    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNView.view
     */
    public InputStream view(Session session, String theme, Identifier id)
        throws InvalidToken, ServiceFailure, NotAuthorized, InvalidRequest, 
        NotImplemented, NotFound;

    /**
     * @see https://purl.dataone.org/architecturev2/apis/CN_APIs.html#CNView.listViews
     */
    public OptionList listViews()
        throws InvalidToken, ServiceFailure, NotAuthorized, InvalidRequest, NotImplemented;
    
}
