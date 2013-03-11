package org.jscep.jester;

import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;

public class SampleEstMediator implements EstMediator {
    public List<X509Certificate> getCaCertificates() {
        return Collections.emptyList();
    }

    public List<String> getCsrAttributes() {
        return Collections.emptyList();
    }
}