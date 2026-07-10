/**
 * This work was created by participants in the DataONE project, and is
 * jointly copyrighted by participating institutions in DataONE. For 
 * more information on DataONE, see our web site at http://dataone.org.
 *
 *   Copyright 2026
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

package org.dataone.security;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import javax.servlet.ServletException;

import org.apache.catalina.Valve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

/**
 * Temporary mitigation Valve to handle GHSA-95v2-fvxr-qg83-style path confusion/bypass attempts.
 * Added 2026-07-10.
 * Enable via the <Valve> element under <Host ...> in server.xml, e.g.:
 *     <Valve className="org.dataone.security.TemporaryMitigationValve" />
 */
public class TemporaryMitigationValve extends ValveBase {

    private static final int MAX_DECODE_ROUNDS = 3;

    // deny patterns
    private static final Pattern SUSPICIOUS = Pattern.compile(
        "(?i)(\\.{2}|%2e|%2f|%5c|\\\\|/\\./|/\\.\\./|%252e|%252f|%255c)"
    );

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        String uri = safe(request.getRequestURI());
        String qs = request.getQueryString();
        String target = (qs == null) ? uri : (uri + "?" + qs);

        if (isSuspicious(target)) {
            response.sendError(400, "Malformed request target");
            return;
        }

        Valve next = getNext();
        if (next != null) {
            next.invoke(request, response);
        }
    }

    private boolean isSuspicious(String input) {
        String current = input;
        for (int i = 0; i < MAX_DECODE_ROUNDS; i++) {
            if (SUSPICIOUS.matcher(current).find()) {
                return true;
            }
            String decoded = decodeOnce(current);
            if (decoded.equals(current)) {
                break;
            }
            current = decoded;
        }
        return SUSPICIOUS.matcher(current).find();
    }

    private String decodeOnce(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            // Invalid %-encoding => treat as suspicious
            return "%BAD_ENCODING%";
        }
    }

    private String safe(String v) {
        return v == null ? "" : v;
    }
}