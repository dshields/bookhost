/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract public class BaseServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -5160885560618053380L;
    HttpServletRequest currentRequest;

    abstract protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        currentRequest = request;
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false) == null) {
            handleRequest(request, response);
            //response.sendRedirect(response.encodeRedirectURL("logon.jsp?continue="+request.getContextPath()));
        } else {
            handleRequest(request, response);
        }
    }

}