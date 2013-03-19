package org.jscep.jester.io;

import org.bouncycastle.cms.CMSSignedDataGenerator;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

public class CMSSignedDataGeneratorProducer {
    @Produces @Default
    public CMSSignedDataGenerator createCMSSignedDataGenerator() {
        return new CMSSignedDataGenerator();
    }
}
