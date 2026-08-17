package org.dataone.mimemultipart;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.protocol.http.mock.MockHttpSession;
import org.apache.wicket.protocol.http.mock.MockServletContext;
import org.junit.Test;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test the MultipartRequestResolver class
 */
public class MultipartRequestResolverTest {
    private static final String ID_KEY = "id";
    private static final String ID_VALUE = "test_MultipartRequestResolver";
    private static final String QUERY_KEY = "query";
    private static final String SPECIAL_STRING = "测试";

    private static String contentType;

    /**
     * Test the resolveMultipart method to handle a multipart parameter with a special character
     * value
     */
    @Test
    public void testResolveMultipart() throws Exception {
        HttpServletRequest request = generateMultiPartServletRequest();
        MultipartRequestResolver resolver = new MultipartRequestResolver();
        MultipartRequest multipartRequest = resolver.resolveMultipart(request);
        Map<String, List<String>> multipartParams = multipartRequest.getMultipartParameters();
        assertEquals(2, multipartParams.size());
        String id = multipartParams.get(ID_KEY).get(0);
        assertEquals(ID_VALUE, id);
        String query = multipartParams.get(QUERY_KEY).get(0);
        assertEquals(SPECIAL_STRING, query);
    }

    /**
     * Generate a multipart servlet request
     * @return the multipart servlet request
     * @throws Exception
     */
    private HttpServletRequest generateMultiPartServletRequest() throws Exception {
        ServletInputStream inputStream = buildMultipartStream();
        MockServletContext context = new MockServletContext(null, "/");
        MockHttpServletRequest request =
            new MockHttpServletRequest(null, new MockHttpSession(context), context) {
                @Override
                public String getContentType() {
                    return contentType;
                }
                @Override
                public ServletInputStream getInputStream() {
                    return inputStream;
                }
            };
        return request;
    }

    /**
     * Build a multipart servlet input stream with the special characters
     * @return a ServletInputStream object
     * @throws Exception
     */
    private ServletInputStream buildMultipartStream() throws Exception {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        StringBody pidBody = new StringBody(ID_VALUE, ContentType.TEXT_PLAIN);
        builder.addPart(ID_KEY, pidBody);
        StringBody queryBody = new StringBody(SPECIAL_STRING, ContentType.TEXT_PLAIN.withCharset(
            StandardCharsets.UTF_8));
        builder.addPart(QUERY_KEY, queryBody);
        HttpEntity entity = builder.build();
        contentType = entity.getContentType().getValue();
        // Serialize request body
        ByteArrayOutputStream requestContent = new ByteArrayOutputStream();
        entity.writeTo(requestContent);
        ByteArrayInputStream requestInput = new ByteArrayInputStream(requestContent.toByteArray());
        ServletInputStream objectInputStream = new WrappingServletInputStream(requestInput);
        return objectInputStream;
    }
}

/**
 * A wrapping class to convert an InputStream object to a ServletInputStream object
 */
class WrappingServletInputStream extends ServletInputStream {
    private final InputStream sourceStream;

    /**
     * Create a DelegatingServletInputStream for the given source stream.
     * @param sourceStream the source stream (never <code>null</code>)
     */
    public  WrappingServletInputStream(InputStream sourceStream) {

        this.sourceStream = sourceStream;
    }

    /**
     * Return the underlying source stream (never <code>null</code>).
     */
    public final InputStream getSourceStream() {
        return this.sourceStream;
    }

    public int read() throws IOException {
        return this.sourceStream.read();
    }

    public void close() throws IOException {
        super.close();
        this.sourceStream.close();
    }


    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }

}
