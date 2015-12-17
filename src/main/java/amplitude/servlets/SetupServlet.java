/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.servlets;

import amplitude.model.IAmplitudeDb;
import amplitude.persistence.hibernate.AmplitudeDB;
import amplitude.persistence.hibernate.DBUtils;
import amplitude.system.AmplitudeProperties;
import amplitude.system.AmplitudeSystem;
import amplitude.system.IAmplitudeProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetupServlet extends BaseServlet {

    static final long serialVersionUID = 1001;
    static IAmplitudeDb dao = null;

    @SuppressWarnings("deprecation")
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final DBUtils utils = new DBUtils();
            IAmplitudeProperties props = AmplitudeProperties.getAmplitudeProperties();
            props.setSongDirectory(request.getParameter("directory"));
            if (!AmplitudeSystem.getAmplitudeDB().databaseExists()) {
                DBUtils.initialize(true);
                getServletContext().setAttribute("dao", AmplitudeSystem.getAmplitudeDB());
                utils.loadDatabase();
            }
        } catch (Exception ex) {
            Logger.getLogger(this.getClass()).log(Priority.ERROR, "While performing initial setup: ", ex);
        }
        response.sendRedirect("songs");
    }

    /*
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

}