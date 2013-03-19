package org.jscep.jester;

import org.jscep.jester.EstMediator;

import static java.util.Collections.singletonList;
import java.util.List;

public class EstMediatorStubWithOneCsrAttribute extends EstMediatorStubTemplate {
    public String[] getCsrAttributes() {
        return new String[] {"1.2"};
    }
}
