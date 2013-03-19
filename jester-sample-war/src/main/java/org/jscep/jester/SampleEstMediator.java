package org.jscep.jester;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class SampleEstMediator implements EstMediator {
    public X509Certificate[] getCaCertificates() {
        try {
            KeyStore store = KeyStore.getInstance("JKS");
            store.load(getClass().getResourceAsStream("/jester.jks"), "jester".toCharArray());

            List<X509Certificate> certificates = new ArrayList<X509Certificate>();

            Enumeration<String> aliases = store.aliases();
            while (aliases.hasMoreElements()) {
                Certificate[] certs = store.getCertificateChain(aliases.nextElement());
                for (Certificate cert : certs) {
                    certificates.add((X509Certificate) cert);
                }
            }

            return certificates.toArray(new X509Certificate[certificates.size()]);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getCsrAttributes() {
        return new String[0];
    }
}