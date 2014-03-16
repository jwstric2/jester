package org.jscep.jester;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import org.jscep.jester.io.*;
import org.jscep.jester.servlet.CaDistributionServlet;
import org.jscep.jester.servlet.CsrAttributesServlet;
import org.jscep.jester.servlet.EnrollmentServlet;
import org.jscep.jester.servlet.KeyGenerationServlet;

import java.security.cert.X509Certificate;

public class JesterGuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            public void configureServlets() {
                serve("/cacerts").with(CaDistributionServlet.class);
                serve("/simpleenroll").with(EnrollmentServlet.class);
                serve("/simplereenroll").with(EnrollmentServlet.class);
                serve("/serverkeygen").with(KeyGenerationServlet.class);
                serve("/csrattrs").with(CsrAttributesServlet.class);

                bind(CaDistributionServlet.class).asEagerSingleton();
                bind(EnrollmentServlet.class).asEagerSingleton();
                bind(KeyGenerationServlet.class).asEagerSingleton();
                bind(CsrAttributesServlet.class).asEagerSingleton();
                
                bind(EstMediator.class).to(SampleEstMediator.class);
                bind(new TypeLiteral<EntityEncoder<X509Certificate>>(){}).to(BouncyCastleSignedDataEncoder.class);
                bind(new TypeLiteral<EntityEncoder<String>>(){}).to(BouncyCastleCsrAttributeEncoder.class);
                bind(new TypeLiteral<EntityDecoder<CertificationRequest>>(){}).to(BouncyCastleCertificateRequestDecoder.class);
            }
        });
    }
}
