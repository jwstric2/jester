package org.jscep.jester.io;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertEquals;

public class CertificateEncoderStub implements EntityEncoder<X509Certificate> {
    public static final byte[] TEST_BYTES = "jester".getBytes();

    public void encode(OutputStream out, X509Certificate... entity) throws IOException {
        out.write(TEST_BYTES);
    }
}
