package org.jscep.jester.io;

import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CMSSignedDataGeneratorProviderTest {
    private CMSSignedDataGeneratorProvider producer;

    @Before
    public void setUp() {
        producer = new CMSSignedDataGeneratorProvider();
    }

    @Test
    public void testCreateCMSSignedDataGenerator() {
        assertThat(producer.get(), isA(CMSSignedDataGenerator.class));
    }
}
