package org.jscep.jester.io;

import java.io.IOException;
import java.io.OutputStream;

public interface EntityEncoder<T> {
    void encode(T entity, OutputStream out) throws IOException;
}
