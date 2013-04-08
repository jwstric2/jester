package org.jscep.jester;

import org.junit.Test;
import static org.junit.Assert.*;

public class EstProtocolExceptionTest {
    @Test
    public void testGetMessage() {
        String expected = "message";
        EstProtocolException e = new EstProtocolException(expected);

        assertEquals("Expected message to be equal", expected, e.getMessage());
    }
}
