package org.jscep.jester.servlet;

import org.jscep.jester.io.EntityEncoder;
import org.jscep.jester.EstMediator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/CSRAttrs"})
public class CsrAttributesServlet extends HttpServlet {
    @Inject
    private EstMediator est;
    @Inject
    private EntityEncoder<String[]> encoder;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] attrs = est.getCsrAttributes();
        if (attrs.length == 0) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

            return;
        } else {
            response.setContentType("application/csrattrs");

            encoder.encode(attrs, response.getOutputStream());
        }
    }
}
