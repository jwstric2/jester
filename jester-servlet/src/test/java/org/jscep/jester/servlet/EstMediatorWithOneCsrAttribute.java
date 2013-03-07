package org.jscep.jester.servlet;

import org.jscep.jester.EstMediator;

import static java.util.Collections.singletonList;
import java.util.List;

public class EstMediatorWithOneCsrAttribute implements EstMediator {
    public List<String> getCsrAttributes() {
        return singletonList("1.2");
    }
}
