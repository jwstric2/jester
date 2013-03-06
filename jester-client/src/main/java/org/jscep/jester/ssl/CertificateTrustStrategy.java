package org.jscep.jester.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public interface CertificateTrustStrategy {
    boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException;
}
