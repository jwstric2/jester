package org.jscep.jester.servlet;

import org.jscep.jester.io.EntityEncoder;
import org.jscep.jester.EstMediator;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class CsrAttributesServlet extends HttpServlet {
    public static final String APPLICATION_CSRATTRS = "application/csrattrs";
    @Inject
    private EstMediator est;
    @Inject
    private EntityEncoder<String> encoder;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] attrs = est.getCsrAttributes();
        if (attrs.length == 0) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } else {
            response.setContentType(APPLICATION_CSRATTRS);

            encoder.encode(response.getOutputStream(), attrs);
        }
    }
}
