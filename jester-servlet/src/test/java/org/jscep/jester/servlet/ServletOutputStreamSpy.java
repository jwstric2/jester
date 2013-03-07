package org.jscep.jester.servlet;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ServletOutputStreamSpy extends ServletOutputStream {
    private OutputStream collector;

    public ServletOutputStreamSpy(OutputStream collector) {
        this.collector = collector;
    }

    @Override
    public void write(int b) throws IOException {
        collector.write(b);
    }
}
