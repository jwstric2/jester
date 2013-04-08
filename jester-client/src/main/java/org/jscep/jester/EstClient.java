package org.jscep.jester;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.jscep.jester.io.EntityDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.cert.X509Certificate;

public class EstClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EstClient.class);
    public static final String WELL_KNOWN_LOCATION = "/.well-known/est";
    private String host;
    private HttpClient httpClient;
    private EntityDecoder<X509Certificate[]> certDecoder;

    public EstClient(HttpClient httpClient, EntityDecoder<X509Certificate[]> certDecoder, String host) {
        this.host = host;
        this.certDecoder = certDecoder;
        this.httpClient = httpClient;
    }

    public X509Certificate[] obtainCaCertificates() throws IOException, EstProtocolException {
        HttpGet get = new HttpGet("http://" + host + WELL_KNOWN_LOCATION + "/CACerts");
        HttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new EstProtocolException("Status code should be 200");
        }

        Header contentType = response.getFirstHeader(HttpHeaders.CONTENT_TYPE);
        if (contentType == null || !contentType.getValue().equals("application/pkcs7-mime")) {
            throw new EstProtocolException("Content type should be application/pkcs7-mime");
        }

        Header contentTransferEncoding = response.getFirstHeader("Content-Transfer-Encoding");
        if (contentTransferEncoding == null || !contentTransferEncoding.getValue().equals("base64")) {
            throw new EstProtocolException("Content transfer encoding should be base64");
        }

        HttpEntity entity = response.getEntity();
        X509Certificate[] certs = certDecoder.decode(new Base64InputStream(entity.getContent()));

        return certs;
    }
}
