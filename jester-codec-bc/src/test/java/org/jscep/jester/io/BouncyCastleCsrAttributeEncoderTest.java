package org.jscep.jester.io;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

public class BouncyCastleCsrAttributeEncoderTest {
    private EntityEncoder<String> encoder;

    @Before
    public void setUp() {
        encoder = new BouncyCastleCsrAttributeEncoder();
    }

    @Test
    public void testEmptyList() throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        encoder.encode(bOut, new String[0]);

        byte[] actual = bOut.toByteArray();
        // Tag 0x30 Length 0x00
        byte[] expected = new byte[] {0x30, 0x00};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSingletonList() throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        encoder.encode(bOut, new String[] {"1.2"});

        byte[] actual = bOut.toByteArray();
        // Tag 0x30 Length 0x03
        // Tag 0x06 Length 0x01 Value 42 (1 x 40 + 2)
        byte[] expected = new byte[] {0x30, 0x03, 0x06, 0x01, 0x2a};

        assertArrayEquals(expected, actual);
    }
}
