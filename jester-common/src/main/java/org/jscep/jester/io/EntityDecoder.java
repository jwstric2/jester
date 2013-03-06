package org.jscep.jester.io;

import java.io.IOException;
import java.io.InputStream;

public interface EntityDecoder<T> {
    T decode(InputStream in) throws IOException;
}
