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