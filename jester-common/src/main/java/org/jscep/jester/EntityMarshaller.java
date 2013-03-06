package org.jscep.jester;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface EntityMarshaller<T> {
    void write(T entity, OutputStream out) throws IOException;
    T read(InputStream in) throws IOException;
}
