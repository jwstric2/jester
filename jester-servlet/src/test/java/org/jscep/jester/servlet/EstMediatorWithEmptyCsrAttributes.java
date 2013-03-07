package org.jscep.jester.servlet;

import org.jscep.jester.EstMediator;

import java.util.Collections;
import java.util.List;

public class EstMediatorWithEmptyCsrAttributes implements EstMediator {
    public List<String> getCsrAttributes() {
        return Collections.emptyList();
    }
}
