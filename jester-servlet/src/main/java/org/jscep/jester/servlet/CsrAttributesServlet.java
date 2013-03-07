package org.jscep.jester.servlet;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.jscep.jester.io.EntityEncoder;
import org.jscep.jester.EstMediator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet(urlPatterns = {"/CSRAttrs"})
public class CsrAttributesServlet extends HttpServlet {
    @Inject
    private EstMediator est;
    @Inject
    private EntityEncoder<List<String>> csrAttrEncoder;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> attrs = est.getCsrAttributes();
        if (attrs.size() == 0) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

            return;
        } else {
            response.setHeader("Content-Transfer-Encoding", "base64");
            response.setContentType("application/csrattrs");

            OutputStream out = new Base64OutputStream(response.getOutputStream());
            csrAttrEncoder.encode(attrs, out);
        }
    }
}
