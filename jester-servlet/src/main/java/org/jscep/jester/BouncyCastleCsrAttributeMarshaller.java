package org.jscep.jester;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class BouncyCastleCsrAttributeMarshaller implements EntityMarshaller<List<String>> {
    public void write(List<String> attrs, OutputStream out) throws IOException {
        ASN1Encodable[] oids = new ASN1ObjectIdentifier[attrs.size()];
        for (int i = 0; i < attrs.size(); i++) {
            oids[i] = new ASN1ObjectIdentifier(attrs.get(i));
        }
        DERSequence seq = new DERSequence(oids);
        out.write(seq.getEncoded());
    }

    public List<String> read(InputStream in) throws IOException {
        return null;
    }
}
