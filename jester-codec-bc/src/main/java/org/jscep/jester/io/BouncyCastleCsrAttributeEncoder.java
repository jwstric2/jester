package org.jscep.jester.io;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.util.encoders.Hex;
import org.jscep.jester.io.EntityEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class BouncyCastleCsrAttributeEncoder implements EntityEncoder<List<String>> {
    public void encode(List<String> attrs, OutputStream out) throws IOException {
        ASN1Encodable[] oids = new ASN1ObjectIdentifier[attrs.size()];
        for (int i = 0; i < attrs.size(); i++) {
            oids[i] = new ASN1ObjectIdentifier(attrs.get(i));
        }
        DERSequence seq = new DERSequence(oids);
        out.write(seq.getEncoded("DER"));
    }
}
