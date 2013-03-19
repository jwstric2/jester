package org.jscep.jester.io;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.X509Certificate;
import java.util.List;
import static org.junit.Assert.*;

public class CaDistributionEncoderStub implements EntityEncoder<X509Certificate[]> {
    public static final byte[] TEST_BYTES = "jester".getBytes();

    public void encode(X509Certificate[] entity, OutputStream out) throws IOException {
        assertEquals(0, entity.length);

        out.write(TEST_BYTES);
    }
}
