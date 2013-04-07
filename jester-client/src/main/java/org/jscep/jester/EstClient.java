package org.jscep.jester;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jscep.jester.io.EntityDecoder;

import java.io.IOException;
import java.security.cert.X509Certificate;

public class EstClient {
    public static final String WELL_KNOWN_LOCATION = "/.well-known/est";
    private String host;
    private HttpClient httpClient;
    private EntityDecoder<X509Certificate[]> certDecoder;

    public EstClient(HttpClient httpClient, EntityDecoder<X509Certificate[]> certDecoder, String host) {
        this.host = host;
        this.certDecoder = certDecoder;
        this.httpClient = httpClient;
    }

    public X509Certificate[] obtainCaCertificates() throws IOException {
        HttpGet get = new HttpGet("http://" + host + WELL_KNOWN_LOCATION + "/CACerts");
        HttpResponse response = httpClient.execute(get);
        HttpEntity entity = response.getEntity();

        return certDecoder.decode(entity.getContent());
    }
}
