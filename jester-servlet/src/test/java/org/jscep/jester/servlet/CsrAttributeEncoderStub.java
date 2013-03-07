package org.jscep.jester.servlet;

import org.jscep.jester.io.EntityEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CsrAttributeEncoderStub implements EntityEncoder<List<String>> {
    public static final byte[] TEST_BYTES = "jester".getBytes();

    public void encode(List<String> entity, OutputStream out) throws IOException {
        out.write(TEST_BYTES);
    }
}
