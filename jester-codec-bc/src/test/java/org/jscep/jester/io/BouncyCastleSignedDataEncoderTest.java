package org.jscep.jester.io;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BouncyCastleSignedDataEncoderTest {
    private EntityEncoder<X509Certificate[]> encoder;

    @Before
    public void setUp() {
        CMSSignedDataGenerator signedDataGenerator = new CMSSignedDataGenerator();
        encoder = new BouncyCastleSignedDataEncoder(signedDataGenerator);
    }

    @Test
    public void testEmptyList() throws Exception {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        encoder.encode(new X509Certificate[0], bOut);

        assertArrayEquals(Base64.decode("MIAGCSqGSIb3DQEHAqCAMIACAQExADCABgkqhkiG9w0BBwEAADEAAAAAAAAA"), bOut.toByteArray());
    }

    @Test(expected = IOException.class)
    public void testThrowCmsException() throws Exception {
        CMSSignedDataGenerator signedDataGenerator = mock(CMSSignedDataGenerator.class);
        EntityEncoder<X509Certificate[]> encoder = new BouncyCastleSignedDataEncoder(signedDataGenerator);

        when(signedDataGenerator.generate(any(CMSTypedData.class))).thenThrow(new CMSException(""));

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        encoder.encode(new X509Certificate[0], bOut);
    }

    @Test
    public void testKnownCertificate() throws Exception {
        char[] password = "changeit".toCharArray();
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(getClass().getResourceAsStream("/jester.jks"), password);
        X509Certificate cert = (X509Certificate) keyStore.getCertificate("mykey");

        X509Certificate[] certs = new X509Certificate[] {cert};

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        encoder.encode(certs, bOut);

        byte[] bytes = bOut.toByteArray();
        CMSSignedData signedData = new CMSSignedData(bytes);
        Store store = signedData.getCertificates();

        Collection<?> matchingCerts = store.getMatches(null);
        assertEquals(1, matchingCerts.size());

        X509CertificateHolder certHolder = (X509CertificateHolder) matchingCerts.iterator().next();
        JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
        assertEquals(cert, certConverter.getCertificate(certHolder));
    }
}
