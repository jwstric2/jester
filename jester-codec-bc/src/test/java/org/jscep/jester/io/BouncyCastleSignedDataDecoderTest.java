package org.jscep.jester.io;

import org.apache.commons.codec.binary.Base64InputStream;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.*;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BouncyCastleSignedDataDecoderTest {
    private EntityDecoder<X509Certificate[]> decoder;

    @Before
    public void setUp() {
        decoder = new BouncyCastleSignedDataDecoder();
    }

    @Test
    public void testExampleBody() throws Exception {
        X509Certificate[] certs = decoder.decode(new Base64InputStream(getClass().getResourceAsStream("/cacerts.msg")));

        assertEquals("Expected four certificates", 4, certs.length);
    }
}
