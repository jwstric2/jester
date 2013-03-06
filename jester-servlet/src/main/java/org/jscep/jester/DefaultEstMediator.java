package org.jscep.jester;

import javax.enterprise.inject.Default;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DefaultEstMediator implements EstMediator {
    public List<String> getCsrAttributes() {
        return Arrays.asList("1.3.6.1.1.1.1.22", "1.2.840.113549.1.9.7", "1.3.132.0.4", "2.16.840.1.101.3.4.2.2");
    }
}
