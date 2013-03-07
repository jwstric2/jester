package org.jscep.jester.servlet;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.jscep.jester.EstMediator;
import org.jscep.jester.io.EntityEncoder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.X509Certificate;
import java.util.List;

@WebServlet(urlPatterns = {"/CACerts"})
public class CaDistributionServlet extends HttpServlet {
    public static final String PKCS7_CERTS_ONLY = "application/pkcs7-mime;smime-type=certs-only";

    @Inject
    private EstMediator est;
    @Inject
    private EntityEncoder<List<X509Certificate>> certEncoder;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(PKCS7_CERTS_ONLY);
        response.setHeader("Content-Transfer-Encoding", "base64");

        OutputStream out = new Base64OutputStream(response.getOutputStream());
        certEncoder.encode(est.getCaCertificates(), out);
    }
}
