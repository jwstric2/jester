package org.jscep.jester;

import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.List;

public interface EstMediator {
    List<X509Certificate> getCaCertificates();
    List<String> getCsrAttributes();
}
