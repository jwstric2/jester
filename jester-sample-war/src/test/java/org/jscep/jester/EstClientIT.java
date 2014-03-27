package org.jscep.jester;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.jscep.jester.io.BouncyCastleSignedDataDecoder;
import org.jscep.jester.io.EntityEncoder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static org.junit.Assert.assertNotNull;

public class EstClientIT {
    @Test
    public void testObtainCaCertificates() throws IOException, Exception {
        HttpClient httpClient = getNewHttpClient();
        EstClient estClient = new EstClient(httpClient, new BouncyCastleSignedDataDecoder(), new EntityEncoder<CertificationRequest>() {
            @Override
            public void encode(OutputStream out, CertificationRequest... entity) throws IOException {
               out.write(entity[0].getBytes()); 
            }
        }, "localhost:8443");
        assertNotNull(estClient.obtainCaCertificates());
        estClient.enroll(getCertificationRequest());
    }
    
    private CertificationRequest getCertificationRequest() {
        final PEMParser parser = new PEMParser(new InputStreamReader(getClass().getResourceAsStream("/jester.p10")));
        return new CertificationRequest() {
            @Override
            public byte[] getBytes() {
                try {
                    return ((PKCS10CertificationRequest) parser.readObject()).getEncoded();
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        };
    }

    private HttpClient getNewHttpClient() throws Exception {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), new StrictHostnameVerifier());
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();

    }
}
