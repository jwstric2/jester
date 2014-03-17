package org.jscep.jester.servlet;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.jscep.jester.CertificationRequest;
import org.jscep.jester.EstMediator;
import org.jscep.jester.io.EntityDecoder;
import org.jscep.jester.io.EntityEncoder;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.cert.X509Certificate;

@Singleton
public class EnrollmentServlet extends HttpServlet {
    public static final String APPLICATION_PKCS7_MIME = "application/pkcs7-mime";
    @Inject
    private EstMediator est;
    @Inject
    private EntityDecoder<CertificationRequest> decoder;
    @Inject
    private EntityEncoder<X509Certificate> encoder;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CertificationRequest csr = decoder.decode(new Base64InputStream(request.getInputStream()));

        try {
            response.setContentType(APPLICATION_PKCS7_MIME);
            response.addHeader("Content-Transfer-Encoding", "base64");
            X509Certificate certificate = est.enroll(csr);
            Base64OutputStream bOut = new Base64OutputStream(response.getOutputStream());
            encoder.encode(bOut, certificate);
            bOut.flush();
            bOut.close();

        } catch (IOException e) {
            response.sendError(500);
            response.getWriter().write(e.getMessage());
            response.getWriter().close();

        }

        // 202
        // Retry-After

        // 400
    }
}
