package org.jscep.jester.io;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
import org.jscep.jester.CertificationRequest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

public class BouncyCastleCertificateRequestDecoderTest {
    private EntityDecoder<CertificationRequest> decoder;

    @Before
    public void setUp() {
        decoder = new BouncyCastleCertificateRequestDecoder();
    }

    @Test(expected = IOException.class)
    public void testInvalidContent() throws IOException {
        ByteArrayInputStream bIn = new ByteArrayInputStream(new byte[0]);
        decoder.decode(bIn);
    }

    @Test
    public void testIsCertificationRequest() throws IOException {
        CertificationRequest csr = decoder.decode(getClass().getResourceAsStream("/jester.p10"));
        PKCS10CertificationRequest pkcs10 = new PKCS10CertificationRequest(csr.getBytes());

        assertThat(pkcs10.getSubject().toString(), containsString("Jester"));
    }
}
