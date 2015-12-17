/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.servlets;

import amplitude.model.ISong;
import amplitude.system.AmplitudeProperties;
import amplitude.utils.FileProc;
import amplitude.utils.LameProc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

public class PlayServlet extends BaseServlet {

    static final long serialVersionUID = 1;

    public static int getBitRate(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        int bitRate = AmplitudeProperties.getAmplitudeProperties().getDefaultBitRate();
        // Check request then session if nothing in request
        if (request.getParameter("b") != null) {
            bitRate = Integer.parseInt(request.getParameter("b"));
        } else if ((session != null) && session.getAttribute("bitRate") != null) {
            bitRate = ((Integer) session.getAttribute("bitRate")).intValue();
        }
        return bitRate;
    }

    public static void playMP3(String fileName, int bitRate, OutputStream os) throws Exception {
        if (AmplitudeProperties.getAmplitudeProperties().getLameCommand().trim().length() > 0) {
            LameProc p = new LameProc();
            p.setMP3FileName(fileName);
            Hashtable<String, Object> args = new Hashtable<String, Object>();
            args.put("bitRate", new Integer(bitRate));
            p.setBitRate(bitRate);
            p.processMP3Stream(args, os);
            os.close();
        } else {
            FileProc p = new FileProc();
            p.setMP3FileName(fileName);
            Hashtable<String, Object> args = new Hashtable<String, Object>();
            args.put("bitRate", new Integer(bitRate));
            p.processMP3Stream(args, os);
            os.close();
        }
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
        ISong song = null;
        try {
            if (request.getParameter("s") != null) {
                song = getDAO().getSongById(Long.parseLong(request.getParameter("s")));
                fileName = song.getURL().getFile();
                fileName = fileName.substring(1);
            } else {
                return;
            }

            if (java.io.File.separatorChar == '\\') {
                fileName = fileName.replaceAll("/", "\\\\");
            }

            response.addHeader("Content-Disposition", "attachment; filename=" + fileName.substring(fileName.lastIndexOf(java.io.File.separatorChar)));

            int bitRate = getBitRate(request);
            playMP3(fileName, bitRate, os);
        } catch (Exception ex) {
        }
    }

    /*
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        AmplitudeProperties.getAmplitudeProperties();
    }

}