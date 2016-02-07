/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.servlets;

import amplitude.utils.FileProc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

public class PlayServlet extends BaseServlet {

    static final long serialVersionUID = 1;

    public static void playMP3(String fileName, OutputStream os) throws Exception {
        FileProc p = new FileProc();
        p.setMP3FileName(fileName);
        Hashtable<String, Object> args = new Hashtable<String, Object>();
        p.processMP3Stream(os);
        os.close();
    }

    /*
     * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
     */
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Play
        response.setContentType("audio/mpeg");
        // response.setContentLength(99999999);
        OutputStream os = response.getOutputStream();
        String fileName = null;
        try {
            fileName = getCurrentSongFileName();

            response.addHeader("Content-Disposition", "attachment; filename=" + fileName.substring(fileName.lastIndexOf(java.io.File.separatorChar)));

            playMP3(fileName, os);
        } catch (Exception ex) {
        }
    }

    private String getCurrentSongFileName() {
        File file = new File(System.getProperty("book"));
        if(!file.exists())
        {
            throw new RuntimeException("Book not found: " + System.getProperty("book"));
        }
        return file.getAbsolutePath();
    }

    /*
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

}