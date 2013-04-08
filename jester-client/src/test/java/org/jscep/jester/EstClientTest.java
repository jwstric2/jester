package org.jscep.jester;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;
import org.jscep.jester.io.EntityDecoder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;

import static org.mockito.Mockito.*;

public class EstClientTest {

    private EstClient estClient;
    private X509Certificate[] expected;
    private HttpClient httpClient;
    private HttpResponse httpResponse;
    private HttpEntity httpEntity;
    private EntityDecoder<X509Certificate[]> certDecoder;
    private StatusLine statusLine;

    @Before
    public void setUp() throws Exception {
        expected = new X509Certificate[0];
        certDecoder = mock(EntityDecoder.class);
        httpClient = mock(HttpClient.class);
        httpResponse = mock(HttpResponse.class);
        httpEntity = mock(HttpEntity.class);
        statusLine = mock(StatusLine.class);
        estClient = new EstClient(httpClient, certDecoder, "www.example.com");

        when(certDecoder.decode(any(InputStream.class))).thenReturn(expected);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(httpResponse);
    }

    @Test(expected = EstProtocolException.class)
    public void testIncorrectStatusCode() throws Exception {
        assertArrayEquals(expected, estClient.obtainCaCertificates());
    }

    @Test(expected = EstProtocolException.class)
    public void testInvalidContentType() throws Exception {
        when(statusLine.getStatusCode()).thenReturn(200);
        assertArrayEquals(expected, estClient.obtainCaCertificates());
    }

    @Test(expected = EstProtocolException.class)
    public void testInvalidContentTransferEncoding() throws Exception {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_TYPE)).thenReturn(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/pkcs7-mime"));
        assertArrayEquals(expected, estClient.obtainCaCertificates());
    }

    @Test
    public void testObtainCaCertificates() throws Exception {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_TYPE)).thenReturn(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/pkcs7-mime"));
        when(httpResponse.getFirstHeader("Content-Transfer-Encoding")).thenReturn(new BasicHeader("Content-Transfer-Encoding", "base64"));

        assertArrayEquals(expected, estClient.obtainCaCertificates());
    }
}
