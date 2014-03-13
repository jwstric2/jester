package org.jscep.jester.io;

import java.io.IOException;
import java.io.OutputStream;

public interface EntityEncoder<T> {
    void encode(OutputStream out, T... entity) throws IOException;
}
