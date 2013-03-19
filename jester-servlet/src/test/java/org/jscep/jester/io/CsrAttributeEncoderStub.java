package org.jscep.jester.io;

import org.jscep.jester.io.EntityEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import static org.junit.Assert.*;

public class CsrAttributeEncoderStub implements EntityEncoder<String[]> {
    public static final byte[] TEST_BYTES = "jester".getBytes();

    public void encode(String[] entity, OutputStream out) throws IOException {
        assertEquals(1, entity.length);
        assertEquals("1.2", entity[0]);

        out.write(TEST_BYTES);
    }
}
