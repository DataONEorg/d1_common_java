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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class TemporaryMitigationValveTest {

    private final TemporaryMitigationValve valve = new TemporaryMitigationValve();

    @Test
    public void rejectsPlainTraversal() {
        assertTrue(isSuspicious("/v2/resolve/../etc/passwd"));
    }

    @Test
    public void rejectsSingleEncodedTraversal() {
        assertTrue(isSuspicious("/v2/resolve/%2e%2e%2fetc/passwd"));
    }

    @Test
    public void rejectsDoubleEncodedTraversal() {
        assertTrue(isSuspicious("/v2/resolve/%252e%252e%252fetc/passwd"));
    }

    @Test
    public void rejectsMalformedPercentEncoding() {
        assertTrue(isSuspicious("/v2/resolve/%ZZ"));
    }

    @Test
    public void allowsNormalRequestTarget() {
        assertFalse(isSuspicious("/v2/resolve/abc123?includeMetadata=true"));
    }

    private boolean isSuspicious(String input) {
        try {
            Method method = TemporaryMitigationValve.class.getDeclaredMethod("isSuspicious", String.class);
            method.setAccessible(true);
            return (Boolean) method.invoke(valve, input);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError("Failed to access TemporaryMitigationValve.isSuspicious", e);
        } catch (InvocationTargetException e) {
            throw new AssertionError("Unexpected exception from TemporaryMitigationValve.isSuspicious", e);
        }
    }
}
