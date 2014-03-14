package org.jscep.jester;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;
import org.jscep.jester.io.EntityDecoder;
import org.jscep.jester.io.EntityEncoder;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class EstClientTest {

    private EstClient estClient;
    private X509Certificate[] expectedCerts;
    private HttpResponse httpResponse;
    private StatusLine statusLine;
    private X509Certificate expectedCert;

    @Before
    public void setUp() throws Exception {
        expectedCert = mock(X509Certificate.class);
        expectedCerts = new X509Certificate[] {expectedCert};
        EntityDecoder<X509Certificate[]> certDecoder = mock(EntityDecoder.class);
        EntityEncoder<CertificationRequest> csrEncoder = mock(EntityEncoder.class);
        HttpClient httpClient = mock(HttpClient.class);
        httpResponse = mock(HttpResponse.class);
        HttpEntity httpEntity = mock(HttpEntity.class);
        statusLine = mock(StatusLine.class);
        estClient = new EstClient(httpClient, certDecoder, csrEncoder, "www.example.com");

        when(certDecoder.decode(any(InputStream.class))).thenReturn(expectedCerts);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(httpResponse);
    }

    @Test(expected = EstProtocolException.class)
    public void testIncorrectStatusCode() throws Exception {
        assertArrayEquals(expectedCerts, estClient.obtainCaCertificates());
    }

    @Test(expected = EstProtocolException.class)
    public void testInvalidContentType() throws Exception {
        when(statusLine.getStatusCode()).thenReturn(200);
        assertArrayEquals(expectedCerts, estClient.obtainCaCertificates());
    }

    @Test(expected = EstProtocolException.class)
    public void testInvalidContentTransferEncoding() throws Exception {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_TYPE)).thenReturn(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/pkcs7-mime"));
        assertArrayEquals(expectedCerts, estClient.obtainCaCertificates());
    }

    @Test
    public void testObtainCaCertificates() throws Exception {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_TYPE)).thenReturn(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/pkcs7-mime"));
        when(httpResponse.getFirstHeader("Content-Transfer-Encoding")).thenReturn(new BasicHeader("Content-Transfer-Encoding", "base64"));

        assertArrayEquals(expectedCerts, estClient.obtainCaCertificates());
    }

    @Test(expected = EstProtocolException.class)
    public void testEnrollBadStatusCode() throws Exception {
        CertificationRequest csr = mock(CertificationRequest.class);
        when(statusLine.getStatusCode()).thenReturn(400);

        estClient.enroll(csr);
    }

    @Test(expected = EstProtocolException.class)
    public void testEnrollInvalidContentType() throws Exception {
        CertificationRequest csr = mock(CertificationRequest.class);
        when(statusLine.getStatusCode()).thenReturn(200);

        estClient.enroll(csr);
    }

    @Test(expected = EstProtocolException.class)
    public void testEnrollInvalidContentTransferEncoding() throws Exception {
        CertificationRequest csr = mock(CertificationRequest.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_TYPE)).thenReturn(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/pkcs7-mime"));

        estClient.enroll(csr);
    }

    @Test
    public void testEnroll() throws Exception {
        CertificationRequest csr = mock(CertificationRequest.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_TYPE)).thenReturn(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/pkcs7-mime"));
        when(httpResponse.getFirstHeader("Content-Transfer-Encoding")).thenReturn(new BasicHeader("Content-Transfer-Encoding", "base64"));

        X509Certificate actual = estClient.enroll(csr).getCertificate();
        assertEquals(expectedCert, actual);
    }

    @Test
    public void testEnroll202() throws Exception {
        CertificationRequest csr = mock(CertificationRequest.class);
        when(statusLine.getStatusCode()).thenReturn(202);
        when(httpResponse.getFirstHeader(HttpHeaders.RETRY_AFTER)).thenReturn(new BasicHeader(HttpHeaders.RETRY_AFTER, "120"));

        EnrollmentResponse enrollmentResponse = estClient.enroll(csr);
        assertNull(enrollmentResponse.getCertificate());
        assertNotNull(enrollmentResponse.getRetryDate());
    }

    @Test
    public void testRenew() throws Exception {
        CertificationRequest csr = mock(CertificationRequest.class);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getFirstHeader(HttpHeaders.CONTENT_TYPE)).thenReturn(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/pkcs7-mime"));
        when(httpResponse.getFirstHeader("Content-Transfer-Encoding")).thenReturn(new BasicHeader("Content-Transfer-Encoding", "base64"));

        X509Certificate actual = estClient.renew(csr).getCertificate();
        assertEquals(expectedCert, actual);
    }

    @Test
    public void testRenew202() throws Exception {
        CertificationRequest csr = mock(CertificationRequest.class);
        when(statusLine.getStatusCode()).thenReturn(202);
        when(httpResponse.getFirstHeader(HttpHeaders.RETRY_AFTER)).thenReturn(new BasicHeader(HttpHeaders.RETRY_AFTER, "120"));

        EnrollmentResponse enrollmentResponse = estClient.renew(csr);
        assertNull(enrollmentResponse.getCertificate());
        assertNotNull(enrollmentResponse.getRetryDate());
    }
}
