package org.jscep.jester.io;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSAbsentContent;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.util.Store;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BouncyCastleSignedDataDecoder implements EntityDecoder<X509Certificate[]> {
    @Override
    public X509Certificate[] decode(InputStream in) throws IOException {
        List<X509Certificate> certs = new ArrayList<X509Certificate>();
        try {
            CMSSignedData signedData = new CMSSignedData(in);
            JcaX509CertificateConverter converter = new JcaX509CertificateConverter();

            Collection<?> certHolders = signedData.getCertificates().getMatches(null);

            for (Object certHolder : certHolders) {
                certs.add(converter.getCertificate((X509CertificateHolder) certHolder));
            }
        } catch (CMSException e) {
            throw new IOException(e);
        } catch (CertificateException e) {
            throw new IOException(e);
        }
        return certs.toArray(new X509Certificate[certs.size()]);
    }
}
