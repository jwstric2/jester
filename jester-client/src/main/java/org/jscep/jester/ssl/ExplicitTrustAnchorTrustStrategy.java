package org.jscep.jester.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ExplicitTrustAnchorTrustStrategy implements CertificateTrustStrategy {
    protected static final String ID_KP_CMCRA = "1.3.6.1.5.5.7.3.28";

    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain.length == 0) {
            return false;
        }

        return chain[0].getExtendedKeyUsage().contains(ID_KP_CMCRA);
    }
}
