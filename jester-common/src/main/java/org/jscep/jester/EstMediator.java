package org.jscep.jester;

import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.List;

public interface EstMediator {
    X509Certificate[] getCaCertificates();
    String[] getCsrAttributes();
    X509Certificate enroll(CertificationRequest csr);
}
