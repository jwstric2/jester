package org.jscep.jester.io;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSAbsentContent;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.util.Store;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class BouncyCastleSignedDataEncoder implements EntityEncoder<X509Certificate> {
    private final Provider<CMSSignedDataGenerator> sdGeneratorProvider;

    @Inject
    public BouncyCastleSignedDataEncoder(Provider<CMSSignedDataGenerator> sdGeneratorProvider) {
        this.sdGeneratorProvider = sdGeneratorProvider;
    }

    public void encode(OutputStream out, X509Certificate... entity) throws IOException {
        CMSSignedDataGenerator signedDataGenerator = sdGeneratorProvider.get();
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
