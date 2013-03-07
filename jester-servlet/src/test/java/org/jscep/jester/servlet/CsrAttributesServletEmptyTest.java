package org.jscep.jester.servlet;

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
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(Arquillian.class)
public class CsrAttributesServletEmptyTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClass(CsrAttributesServlet.class)
                .addClasses(EstMediator.class, EstMediatorWithEmptyCsrAttributes.class)
                .addClasses(EntityEncoder.class, CsrAttributeEncoderStub.class);
    }

    @Inject
    private CsrAttributesServlet servlet;

    @Test
    public void testNoContent() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        servlet.doGet(request, response);
        verify(response).setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
