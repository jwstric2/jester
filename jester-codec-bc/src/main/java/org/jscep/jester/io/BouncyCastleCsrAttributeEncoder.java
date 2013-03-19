package org.jscep.jester.io;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;

import java.io.IOException;
import java.io.OutputStream;

public class BouncyCastleCsrAttributeEncoder implements EntityEncoder<String[]> {
    public void encode(String[] attrs, OutputStream out) throws IOException {
        ASN1Encodable[] oids = new ASN1ObjectIdentifier[attrs.length];
        for (int i = 0; i < attrs.length; i++) {
            oids[i] = new ASN1ObjectIdentifier(attrs[i]);
        }
        DERSequence seq = new DERSequence(oids);
        out.write(seq.getEncoded("DER"));
    }
}
