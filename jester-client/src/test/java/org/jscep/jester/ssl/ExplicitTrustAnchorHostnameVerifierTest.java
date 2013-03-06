package org.jscep.jester.ssl;

import org.junit.Test;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collections;

import static org.jscep.jester.ssl.ExplicitTrustAnchorHostnameVerifier.ID_KP_CMCRA;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExplicitTrustAnchorHostnameVerifierTest {

    public static final String HOSTNAME = "example.org";

    @Test
    public void testExtendedKeyUsage() throws Exception {
        X509Certificate cert = mock(X509Certificate.class);
        when(cert.getExtendedKeyUsage()).thenReturn(Collections.singletonList(ID_KP_CMCRA));

        SSLSession session = mock(SSLSession.class);
        when(session.getPeerCertificates()).thenReturn(new Certificate[]{cert});

        HostnameVerifier verifier = new ExplicitTrustAnchorHostnameVerifier();
        assertTrue("Verifier should allow certificates with extended keyUsage of id_kp", verifier.verify(HOSTNAME, session));
    }

    @Test
    public void testExtendedKeyUsagePeerUnverified() throws Exception {
        SSLSession session = mock(SSLSession.class);
        when(session.getPeerCertificates()).thenThrow(new SSLPeerUnverifiedException(""));

        HostnameVerifier verifier = new ExplicitTrustAnchorHostnameVerifier();
        assertFalse("Verifier should not allow unverified peers", verifier.verify(HOSTNAME, session));
    }

    @Test
    public void testExtendedKeyUsageEmptyCertificateChain() throws Exception {
        SSLSession session = mock(SSLSession.class);
        when(session.getPeerCertificates()).thenReturn(new Certificate[]{});

        HostnameVerifier verifier = new ExplicitTrustAnchorHostnameVerifier();
        assertFalse("Verifier should not allow empty certificate chain", verifier.verify(HOSTNAME, session));
    }

    @Test
    public void testExtendedKeyUsageNonX509Certificate() throws Exception {
        SSLSession session = mock(SSLSession.class);
        when(session.getPeerCertificates()).thenReturn(new Certificate[]{mock(Certificate.class)});

        HostnameVerifier verifier = new ExplicitTrustAnchorHostnameVerifier();
        assertFalse("Verifier should not allow non-X509 certificates", verifier.verify(HOSTNAME, session));
    }

    @Test
    public void testExtendedKeyUsageMalformedX509Certificate() throws Exception {
        X509Certificate cert = mock(X509Certificate.class);
        when(cert.getExtendedKeyUsage()).thenThrow(new CertificateParsingException());

        SSLSession session = mock(SSLSession.class);
        when(session.getPeerCertificates()).thenReturn(new Certificate[]{cert});

        HostnameVerifier verifier = new ExplicitTrustAnchorHostnameVerifier();
        assertFalse("Verifier should not allow malformed X509 certificates", verifier.verify(HOSTNAME, session));
    }
}
