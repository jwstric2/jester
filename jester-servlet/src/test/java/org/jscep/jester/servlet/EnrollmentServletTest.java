package org.jscep.jester.servlet;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jscep.jester.EstMediator;
import org.jscep.jester.EstMediatorStubTemplate;
import org.jscep.jester.io.CaDistributionEncoderStub;
import org.jscep.jester.io.CertificateEncoderStub;
import org.jscep.jester.io.CertificationRequestDecoderStub;
import org.jscep.jester.io.EntityEncoder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Arquillian.class)
public class EnrollmentServletTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(EnrollmentServlet.class)
                .addClass(CertificationRequestDecoderStub.class)
                .addClass(CertificateEncoderStub.class)
                .addClasses(EstMediator.class, EstMediatorStubTemplate.class);
    }

    @Inject
    private EnrollmentServlet servlet;

    @Test
    public void testContentType() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(new ServletOutputStreamSpy(bOut));

        servlet.doPost(request, response);

        verify(response).setContentType(EnrollmentServlet.APPLICATION_PKCS7_MIME);
    }

    @Test
    public void testServerError() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(bOut);

        when(response.getOutputStream()).thenThrow(new IOException("capture message"));
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(response).sendError(500);
        assertEquals("capture message", new String(bOut.toByteArray(), "ASCII"));
    }
}
