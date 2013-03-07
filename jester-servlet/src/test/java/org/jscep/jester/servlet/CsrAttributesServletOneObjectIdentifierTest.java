package org.jscep.jester.servlet;

import org.apache.commons.codec.binary.Base64;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jscep.jester.EstMediator;
import org.jscep.jester.io.EntityEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CsrAttributesServletOneObjectIdentifierTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(CsrAttributesServlet.class)
                .addClasses(EstMediator.class, EstMediatorWithOneCsrAttribute.class)
                .addClasses(EntityEncoder.class, CsrAttributeEncoderStub.class);
    }

    @Inject
    private CsrAttributesServlet servlet;

    @Test
    public void testTransferEncoding() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStreamSpy(bOut));

        servlet.doGet(request, response);
        verify(response).setHeader("Content-Transfer-Encoding", "base64");
    }

    @Test
    public void testContentType() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStreamSpy(bOut));

        servlet.doGet(request, response);
        verify(response).setContentType("application/csrattrs");
    }

    @Test
    public void testOutput() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStreamSpy(bOut));

        servlet.doGet(request, response);
        byte[] actual = Base64.decodeBase64(bOut.toByteArray());

        assertArrayEquals(CsrAttributeEncoderStub.TEST_BYTES, actual);
    }
}
