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

import java.util.Date;
import java.util.Vector;

public class D1Url {

    private String url;
    private String resource;
    private String baseUrl;
    private Vector<String> pathElements = new Vector<String>();
    private Vector<String> paramV = new Vector<String>();

    public D1Url(String baseUrl, String resource) throws IllegalArgumentException {
        setBaseUrl(baseUrl);
        setResource(resource);
    }

    public D1Url(String baseUrl) throws IllegalArgumentException {
        setBaseUrl(baseUrl);
    }

    public D1Url() {
    	super();
    }
    
    public void setBaseUrl(String baseUrl) throws IllegalArgumentException {
        this.baseUrl = trimAndValidateString(baseUrl);
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setResource(String res) {
        this.resource = res.trim();
    }

    public String getResource() {
        return this.resource;
    }

    /**
     * adds the next path element to the path portion of the URL, encoding unsafe characters.
     * Empty values to the pathElement parameter throws an exception
     * @param pathElement
     * @throws IllegalArgumentException - if pathElement is null or empty
     */
    public void addNextPathElement(String pathElement) throws IllegalArgumentException {

        pathElements.add(EncodingUtilities.encodeUrlPathSegment(trimAndValidateString(pathElement)));
    }

    /**
     * adds a single parameter to the query portion of the URL (not a key-value pair)
     * encoding unsafe characters.  If param is empty or null, nothing is added.
     * @param param
     */
    public void addNonEmptyParam(String param) {
        try {
            paramV.add(EncodingUtilities.encodeUrlQuerySegment(trimAndValidateString(param)));
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }

    /**
     * adds a key value pair to the query portion of the URL, placing '=' character between them,
     * and encoding unsafe characters.
     * If either key or value is empty or null, quietly does not add anything to the url.
     * @param key
     * @param value
     */
    public void addNonEmptyParamPair(String key, String value) {
        try {
            paramV.add(EncodingUtilities.encodeUrlQuerySegment(trimAndValidateString(key)) + "="
                    + EncodingUtilities.encodeUrlQuerySegment(trimAndValidateString(value)));
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }

    /**
     * convenience method for adding date object to query parameters.  The date is converted to
     * GMT and serialized as a string.  If date is null, nothing is added to the URL.
     * @param key
     * @param date
     * @throws IllegalArgumentException
     */
    public void addDateParamPair(String key, Date dateLocalTime) {
        if (dateLocalTime != null) {
            // XXX should be using serializeDatetoUTC, but MNs at this point are not
            // fully ISO 8601 compliant, and this should work...
            String dateString = DateTimeMarshaller.serializeDateToUTC(dateLocalTime);
            paramV.add(EncodingUtilities.encodeUrlQuerySegment(trimAndValidateString(key)) + "="
                    + EncodingUtilities.encodeUrlQuerySegment(trimAndValidateString(dateString)));
        }
    }

    /**
     * adds a key value pair to the query portion of the URL, placing '=' character between them,
     * and encoding unsafe characters.
     * If either key or value is empty or null, quietly does not add anything to the url.
     * @param key
     * @param integer
     */
    public void addNonEmptyParamPair(String key, Integer integer) {
        if (integer != null) {
            paramV.add(EncodingUtilities.encodeUrlQuerySegment(trimAndValidateString(key)) + "="
                    + EncodingUtilities.encodeUrlQuerySegment(trimAndValidateString(integer.toString())));
        }
    }


    /**
     * Method for adding query params that bypasses encoding. Use sparingly, as it is a bit unsafe,
     * but useful if the choice of params is too much to put in a method signature.
     * @param param
     */
    public void addPreEncodedNonEmptyQueryParams(String param) {
        try {
            paramV.add(trimAndValidateString(param));
        } catch (IllegalArgumentException e) {
            // do nothing
        }
    }

    public String getUrl() {
        assembleUrl();
        return this.url;
    }

    public String toString() {
        return getUrl();
    }

    protected void assembleUrl() {
        url = baseUrl == null ? "" : baseUrl;
        url += joinToUrlWith("/", resource);
        for (int i = 0; i < pathElements.size(); i++) {
            url += joinToUrlWith("/", pathElements.get(i));
        }
        if (paramV.size() > 0) {
            this.url.replaceFirst("/$", "");  // remove trailing slash if necessary
            url += joinToUrlWith("?", paramV.get(0));
        }
        for (int i = 1; i < paramV.size(); i++) {
            url += joinToUrlWith("&", paramV.get(i));
        }
    }
    
    public String getAssembledQueryString() {
    	String queryParams = "";
    	if (paramV.size() > 0) {
    		queryParams += paramV.get(0);
    		for (int i = 1; i < paramV.size(); i++) {
    			queryParams += "&";
    			queryParams += paramV.get(i);
    		}  		
    	}
    	return queryParams;
    }

    protected static String trimAndValidateString(String s) throws IllegalArgumentException {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("the string '" + s + "' cannot be null or empty or only whitespace");
        }
        return s.trim();
    }

    
    /**
     * use this method to avoid inadvertent duplication of the joining character
     * The most common case is when a baseUrl contains a trailing slash and you
     * want to append another path elements.
     * @param joiner
     * @param string
     * @return
     */
    protected String joinToUrlWith(String joiner, String string) {
        if (string == null) {
            return "";
        }
        String joined = null;
        if (this.url.endsWith(joiner)) {
            if (string.startsWith(joiner)) {
                joined = (String) string.subSequence(1, string.length());
            } else {
                joined = string;
            }
        } else if (string.startsWith(joiner)) {
            joined = string;
        } else {
            joined = joiner + string;
        }
        return joined;
    }
}
