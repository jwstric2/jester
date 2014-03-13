package org.jscep.jester.servlet;

import org.jscep.jester.CertificationRequest;
import org.jscep.jester.EstMediator;
import org.jscep.jester.io.EntityDecoder;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/serverkeygen"})
public class KeyGenerationServlet extends HttpServlet {
    public static final String APPLICATION_PKCS8 = "application/pkcs8";
    @Inject
    private EstMediator est;
    @Inject
    private EntityDecoder<CertificationRequest> decoder;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CertificationRequest csr = decoder.decode(request.getInputStream());

        response.getWriter().write(csr.toString());
    }
}
