package org.jscep.jester;

import org.junit.Test;

import static org.junit.Assert.*;
import java.security.cert.X509Certificate;
import java.util.Date;

import static org.mockito.Mockito.*;

public class EnrollmentResponseTest {
    @Test
    public void testGetCertificate() {
        X509Certificate expected = mock(X509Certificate.class);
        EnrollmentResponse response = new EnrollmentResponse(expected);

        assertEquals(expected, response.getCertificate());
    }

    @Test
    public void testGetCertificateDateIsNull() {
        X509Certificate expected = mock(X509Certificate.class);
        EnrollmentResponse response = new EnrollmentResponse(expected);

        assertNull(response.getRetryDate());
    }

    @Test
    public void testGetRetryDate() {
        Date expected = new Date();
        EnrollmentResponse response = new EnrollmentResponse(expected);

        assertEquals(expected, response.getRetryDate());
    }

    @Test
    public void testGetRetryDateCertificateIsNull() {
        Date expected = new Date();
        EnrollmentResponse response = new EnrollmentResponse(expected);

        assertNull(response.getCertificate());
    }
}
