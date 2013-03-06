package org.jscep.jester.ssl;

import org.junit.Test;

import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collections;

import static org.jscep.jester.ssl.ExplicitTrustAnchorTrustStrategy.ID_KP_CMCRA;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExplicitTrustAnchorTrustStrategyTest {

    public static final String EMPTY = "";

    @Test
    public void testExtendedKeyUsage() throws Exception {
        X509Certificate cert = mock(X509Certificate.class);
        when(cert.getExtendedKeyUsage()).thenReturn(Collections.singletonList(ID_KP_CMCRA));
        X509Certificate[] chain = new X509Certificate[] {cert};

        CertificateTrustStrategy strategy = new ExplicitTrustAnchorTrustStrategy();
        assertTrue("Verifier should allow certificates with extended keyUsage of id_kp", strategy.isTrusted(chain, EMPTY));
    }

    @Test
    public void testExtendedKeyUsageEmptyCertificateChain() throws Exception {
        X509Certificate[] chain = new X509Certificate[] {};

        CertificateTrustStrategy strategy = new ExplicitTrustAnchorTrustStrategy();
        assertFalse("Verifier should not allow empty certificate chain", strategy.isTrusted(chain, EMPTY));
    }

    @Test(expected = CertificateException.class)
    public void testExtendedKeyUsageMalformedX509Certificate() throws Exception {
        X509Certificate cert = mock(X509Certificate.class);
        when(cert.getExtendedKeyUsage()).thenThrow(new CertificateParsingException());
        X509Certificate[] chain = new X509Certificate[] {cert};

        CertificateTrustStrategy strategy = new ExplicitTrustAnchorTrustStrategy();
        assertFalse("Verifier should not allow malformed X509 certificates", strategy.isTrusted(chain, EMPTY));
    }
}
