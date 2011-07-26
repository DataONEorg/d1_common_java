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
package org.dataone.service.util;

import org.dataone.service.*;

/**
 * @author berkley
 * A class to contain constants used in clients 
 */
public class Constants {

    /** HTTP Verb GET */
    public static final String GET = "GET";
    /** HTTP Verb POST */
    public static final String POST = "POST";
    /** HTTP Verb PUT */
    public static final String PUT = "PUT";
    /** HTTP Verb HEAD*/
    public static final String HEAD = "HEAD";
    /** HTTP Verb DELETE */
    public static final String DELETE = "DELETE";
    

    /** Common API resource which handles SystemMetadata operations */
    public static final String RESOURCE_META = "meta";
    /** Common API resource which handles node operations */
    public static final String RESOURCE_NODE = "node";
    /** Common API resource which handles document operations */
    public static final String RESOURCE_OBJECTS = "object";
    
    /** AUTHORIZATION API resource which handles access operations */
    public static final String RESOURCE_ACCESS = "accessRules";
    /** AUTHORIZATION API Resource which handles authorization checks */
    public static final String RESOURCE_AUTHORIZATION = "isAuthorized";
    /** AUTHORIZATION API Resource which handles owner checks */
    public static final String RESOURCE_OWNER = "owner";
    
    /** CORE API Resource which handles object formats operations */
    public static final String RESOURCE_FORMATS = "formats";
    /** CORE API resource which handles logging operations */
    public static final String RESOURCE_LOG = "log";
    
    /** CORE API resource which handles ping monitoring operations */
    public static final String RESOURCE_MONITOR_PING = "monitor/ping";
    /** CORE API resource which handles object monitoring operations */
    public static final String RESOURCE_MONITOR_OBJECT = "monitor/object";
    /** CORE API resource which handles event monitoring operations */
    public static final String RESOURCE_MONITOR_EVENT = "monitor/event";
    /** CORE API resource which handles status monitoring operations */
    public static final String RESOURCE_MONITOR_STATUS = "monitor/status";
    
    /** CORE API resource which handles identifier reserving operations */
    public static final String RESOURCE_RESERVE = "reserve";
    
    
    /** IDENTITY API resource which handles account operations */
    public static final String RESOURCE_ACCOUNTS = "accounts";
    /** IDENTITY API resource which handles account-mapping operations */
    public static final String RESOURCE_ACCOUNT_MAPPING = "accounts/map";
    /** IDENTITY API resource which handles group-related operations */
    public static final String RESOURCE_GROUPS = "groups";
    
    /** READ API resource which handles relationship assertion operations */
    public static final String RESOURCE_RELATIONSHIP = "assertRelation";
    /** READ API resource which handles checksum operations*/
    public static final String RESOURCE_CHECKSUM = "checksum";
    /** READ API resource which handles synchronization error operations*/
    public static final String RESOURCE_ERROR = "error";
    /** READ API resource which handles resolve operations*/
    public static final String RESOURCE_RESOLVE = "resolve";   
    
    
    /** REPLICATE API resource which handles authorization checks */
    public static final String RESOURCE_POLICY = "meta/policy";
    /** REPLICATE API resource which handles authorization checks */
    public static final String RESOURCE_META_REPLICATE = "meta/replication";
    /** REPLICATE API resource which handles authorization checks */
    public static final String RESOURCE_REPLICATE = "replicate";
    /** REPLICATE API resource which handles authorization checks */
    public static final String RESOURCE_NOTIFY = "notify";
    
   
    /** Temporary file directory */
    public static final String TEMP_DIR = "/tmp";
    
    /** Authorization section */
    public static final String PUBLIC_SUBJECT = "public";
    
}