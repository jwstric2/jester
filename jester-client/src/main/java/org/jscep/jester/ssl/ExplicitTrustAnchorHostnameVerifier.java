package org.jscep.jester.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;

public class ExplicitTrustAnchorHostnameVerifier implements HostnameVerifier {
    public static final String ID_KP_CMCRA = "1.3.6.1.5.5.7.3.28";

    public boolean verify(String hostname, SSLSession session) {
        try {
            Certificate[] certs = session.getPeerCertificates();
            if (certs.length == 0) {
                return false;
            }
            if (certs[0] instanceof X509Certificate == false) {
                return false;
            }
            X509Certificate cert = (X509Certificate) certs[0];

            return cert.getExtendedKeyUsage().contains(ID_KP_CMCRA);
        } catch (SSLPeerUnverifiedException e) {
            return false;
        } catch (CertificateParsingException e) {
            return false;
        }
    }
}
