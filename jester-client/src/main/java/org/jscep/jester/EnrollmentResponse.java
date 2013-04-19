package org.jscep.jester;

import org.joda.time.DateTime;

import java.security.cert.X509Certificate;
import java.util.Date;

public class EnrollmentResponse {
    private final X509Certificate cert;
    private final DateTime retryDate;

    public EnrollmentResponse(X509Certificate cert) {
        this.cert = cert;
        this.retryDate = null;
    }

    public EnrollmentResponse(DateTime retryDate) {
        this.cert = null;
        this.retryDate = retryDate;
    }

    public X509Certificate getCertificate() {
        return cert;
    }

    public DateTime getRetryDate() {
        return retryDate;
    }
}
