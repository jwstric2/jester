package org.jscep.jester;

import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;

public class EstMediatorStubTemplate implements EstMediator {
    public X509Certificate[] getCaCertificates() {
        return new X509Certificate[0];
    }

    public String[] getCsrAttributes() {
        return new String[0];
    }

    @Override
    public X509Certificate enroll(CertificationRequest csr) {
        return null;
    }
}
