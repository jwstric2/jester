package org.jscep.jester.servlet;

import org.jscep.jester.EstMediator;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/serverKeyGen"})
public class KeyGenerationServlet extends HttpServlet {
    public static final String APPLICATION_PKCS8 = "application/pkcs8";
    @Inject
    private EstMediator est;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
