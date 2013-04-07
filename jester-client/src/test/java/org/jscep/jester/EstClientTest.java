package org.jscep.jester;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.jscep.jester.io.EntityDecoder;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.security.cert.X509Certificate;

import static org.mockito.Mockito.*;

public class EstClientTest {
    @Test
    public void testObtainCaCertificates() throws Exception {
        X509Certificate[] certs = new X509Certificate[0];

        EntityDecoder<X509Certificate[]> certDecoder = mock(EntityDecoder.class);
        when(certDecoder.decode(any(InputStream.class))).thenReturn(certs);
        HttpClient httpClient = mock(HttpClient.class);
        EstClient estClient = new EstClient(httpClient, certDecoder, "www.example.com");
        HttpGet get = new HttpGet("http://www.example.com" + EstClient.WELL_KNOWN_LOCATION + "/CACerts");

        HttpResponse httpResponse = mock(HttpResponse.class);
        HttpEntity httpEntity = mock(HttpEntity.class);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(httpResponse);

        assertEquals(certs, estClient.obtainCaCertificates());
    }
}
