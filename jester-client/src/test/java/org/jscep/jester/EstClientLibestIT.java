package org.jscep.jester;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.jscep.jester.io.BouncyCastleSignedDataDecoder;
import org.jscep.jester.io.EntityEncoder;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * Test again libest (https://github.com/cisco/libest)
 * 
 * See http://ec2-54-204-91-178.compute-1.amazonaws.com/
 */
public class EstClientLibestIT {
    @Test
    public void testObtainCaCertificates() throws IOException, Exception {
        EstClient estClient = new EstClient(httpClient(), new BouncyCastleSignedDataDecoder(), new EntityEncoder<CertificationRequest>() {
            @Override
            public void encode(OutputStream out, CertificationRequest... entity) throws IOException {
               out.write(entity[0].getBytes()); 
            }
        }, "ec2-54-204-91-178.compute-1.amazonaws.com:8443");
        X509Certificate[] explicitTaDatabase = estClient.obtainCaCertificates(); // 4.1.1
    }
    
    private CertificationRequest getCertificationRequest() {
        final PEMParser parser = new PEMParser(new InputStreamReader(getClass().getResourceAsStream("/jester.p10")));
        return new CertificationRequest() {
            @Override
            public byte[] getBytes() {
                try {
                    byte[] bytes = ((PKCS10CertificationRequest) parser.readObject()).getEncoded();
                    System.out.println(Arrays.toString(bytes));
                    
                    return bytes;
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        };
    }

    private HttpClient httpClient() throws Exception {
        return HttpClients
                .custom()
                .setSSLSocketFactory(sslConnectionSocketFactory())
                .setDefaultRequestConfig(requestConfig())
                .setDefaultCredentialsProvider(credentialsProvider())
                .build();
    }

    private SSLConnectionSocketFactory sslConnectionSocketFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new SSLConnectionSocketFactory(sslContext(), new StrictHostnameVerifier());
    }

    private SSLContext sslContext() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return new SSLContextBuilder()
            .useProtocol("TLSv1.2")
            .loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            })
            .build();
    }

    private RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .build();
    }

    private BasicCredentialsProvider credentialsProvider() {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope("ec2-54-204-91-178.compute-1.amazonaws.com", 8443), 
                new UsernamePasswordCredentials("estuser", "estpwd"));
        return credentialsProvider;
    }

    private KeyStore getKeyStore() throws Exception {
        KeyStore store = KeyStore.getInstance("JKS");
        store.load(getClass().getResourceAsStream("/jester.jks"), "jester".toCharArray());
        
        return store;
    }
}
