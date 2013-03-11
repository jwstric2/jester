package org.jscep.jester.servlet;

import org.jscep.jester.EstMediator;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/simpleEnroll", "/simpleReEnroll"})
public class EnrollmentServlet extends HttpServlet {
    @Inject
    private EstMediator est;
}
