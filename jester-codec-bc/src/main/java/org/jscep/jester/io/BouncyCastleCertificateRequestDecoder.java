package org.jscep.jester.io;

import org.bouncycastle.jce.provider.PEMUtil;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.jscep.jester.CertificationRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BouncyCastleCertificateRequestDecoder implements EntityDecoder<CertificationRequest> {
    @Override
    public CertificationRequest decode(InputStream in) throws IOException {
        PEMParser parser = new PEMParser(new InputStreamReader(in));
        PKCS10CertificationRequest csr = (PKCS10CertificationRequest) parser.readObject();
        if (csr == null) {
            throw new IOException("Failed to parse CSR");
        }
        final byte[] bytes = csr.getEncoded();

        return new CertificationRequest() {
            @Override
            public byte[] getBytes() {
                return bytes;
            }
        };
    }
}
