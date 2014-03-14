package org.jscep.jester;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.jscep.jester.io.BouncyCastleSignedDataDecoder;
import org.jscep.jester.io.EntityEncoder;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class EstClientIT {
    @Test
    public void testObtainCaCertificates() throws IOException, EstProtocolException, Exception {
        HttpClient httpClient = getNewHttpClient();
        EstClient estClient = new EstClient(httpClient, new BouncyCastleSignedDataDecoder(), new EntityEncoder<CertificationRequest>() {
            @Override
            public void encode(OutputStream out, CertificationRequest... entity) throws IOException {
                
            }
        }, "localhost:8443");
        assertNotNull(estClient.obtainCaCertificates());
    }

    private HttpClient getNewHttpClient() throws Exception {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), new AllowAllHostnameVerifier());
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();

    }
}
