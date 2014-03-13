package org.jscep.jester.servlet;

import org.jscep.jester.CertificationRequest;
import org.jscep.jester.EstMediator;
import org.jscep.jester.io.EntityDecoder;
import org.jscep.jester.io.EntityEncoder;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.cert.X509Certificate;

@WebServlet(urlPatterns = {"/simpleenroll", "/simplereenroll"})
public class EnrollmentServlet extends HttpServlet {
    public static final String APPLICATION_PKCS7_MIME_SMIME_CERTS_ONLY = "application/pkcs7-mime;smime-type=certs-only";
    @Inject
    private EstMediator est;
    @Inject
    private EntityDecoder<CertificationRequest> decoder;
    @Inject
    private EntityEncoder<X509Certificate> encoder;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CertificationRequest csr = decoder.decode(request.getInputStream());

        try {
            response.setContentType(APPLICATION_PKCS7_MIME_SMIME_CERTS_ONLY);
            X509Certificate certificate = est.enroll(csr);
            encoder.encode(certificate, response.getOutputStream());

        } catch (IOException e) {
            response.sendError(500);
            response.getWriter().write(e.getMessage());
            response.getWriter().close();

            return;
        }

        // 202
        // Retry-After

        // 400
    }
}
