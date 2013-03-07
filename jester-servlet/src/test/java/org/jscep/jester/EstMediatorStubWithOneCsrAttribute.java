package org.jscep.jester;

import org.jscep.jester.EstMediator;

import static java.util.Collections.singletonList;
import java.util.List;

public class EstMediatorStubWithOneCsrAttribute extends EstMediatorStubTemplate {
    public List<String> getCsrAttributes() {
        return singletonList("1.2");
    }
}
