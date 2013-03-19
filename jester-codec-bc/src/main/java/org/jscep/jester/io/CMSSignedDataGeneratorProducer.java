package org.jscep.jester.io;

import org.bouncycastle.cms.CMSSignedDataGenerator;

import javax.enterprise.inject.Produces;

public class CMSSignedDataGeneratorProducer {
    @Produces
    public CMSSignedDataGenerator createCMSSignedDataGenerator() {
        return new CMSSignedDataGenerator();
    }
}
