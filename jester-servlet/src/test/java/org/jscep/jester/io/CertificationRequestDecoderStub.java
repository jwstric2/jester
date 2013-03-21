package org.jscep.jester.io;

import org.jscep.jester.CertificationRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertEquals;

public class CertificationRequestDecoderStub implements EntityDecoder<CertificationRequest> {
    public static final byte[] TEST_BYTES = "jester".getBytes();

    public CertificationRequest decode(InputStream in) throws IOException {
        return new CertificationRequest() {
            @Override
            public byte[] getBytes() {
                return TEST_BYTES;
            }
        };
    }
}
