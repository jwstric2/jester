package org.jscep.jester.io;

import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CMSSignedDataGeneratorProducerTest {
    private CMSSignedDataGeneratorProducer producer;

    @Before
    public void setUp() {
        producer = new CMSSignedDataGeneratorProducer();
    }

    @Test
    public void testCreateCMSSignedDataGenerator() {
        assertThat(producer.createCMSSignedDataGenerator(), isA(CMSSignedDataGenerator.class));
    }
}
