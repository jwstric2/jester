package org.jscep.jester.io;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSAbsentContent;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.util.Store;

import javax.inject.Inject;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class BouncyCastleSignedDataEncoder implements EntityEncoder<X509Certificate[]> {
    private final CMSSignedDataGenerator signedDataGenerator;

    @Inject
    public BouncyCastleSignedDataEncoder(CMSSignedDataGenerator signedDataGenerator) {
        this.signedDataGenerator = signedDataGenerator;
    }

    public void encode(X509Certificate[] entity, OutputStream out) throws IOException {
        try {
            Store store = new JcaCertStore(Arrays.asList(entity));
            signedDataGenerator.addCertificates(store);
            CMSSignedData signedData = signedDataGenerator.generate(new CMSAbsentContent());

            out.write(signedData.getEncoded());
        } catch (CMSException e) {
            throw new IOException(e);
        } catch (CertificateEncodingException e) {
            throw new IOException(e);
        }
    }
}
