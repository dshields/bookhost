/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.servlets;

import amplitude.model.IAmplitudeDb;

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

    public IAmplitudeDb getDAO() {
        return (IAmplitudeDb) getServletContext().getAttribute("dao");
    }

    public String getPath() {
        String path = currentRequest.getScheme() + "://" + currentRequest.getServerName();
        if (currentRequest.getServerPort() != 80) {
            path += ":" + currentRequest.getServerPort();
        }
        path += currentRequest.getContextPath();
        return path;
    }

    public String getPlayURL() {
        return getPath() + "/play?s=";
    }

    abstract protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        currentRequest = request;
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (WebHelper.isFirstRun() && WebHelper.requestIsLocal(request) && !request.getServletPath().equals("/setup")) {
            response.sendRedirect("firstrun.jsp");
        } else if (request.getSession(false) == null) {
            handleRequest(request, response);
            //response.sendRedirect(response.encodeRedirectURL("logon.jsp?continue="+request.getContextPath()));
        } else {
            handleRequest(request, response);
        }
    }

}