/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.servlets;

import amplitude.model.IAlbum;
import amplitude.model.IArtist;
import amplitude.model.ISong;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SelectServlet extends BaseServlet {

    final static long serialVersionUID = 1;

    private String makeM3UFromSongList(List<? extends ISong> songs) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#EXTM3U\n");
        for (ISong song : songs) {
            buffer.append("#EXTINF:" + song.getLength() + "," + song.getArtistName() + " - " + song.getSongName() + "\n");
            buffer.append(getPlayURL() + song.getSongId() + "\n");
        }
        return buffer.toString();
    }

    private String getM3U(String type, long id) throws Exception {
        if (type.equalsIgnoreCase("song")) {
            return makeM3UFromSongList(Collections.singletonList(getDAO().getSongById(id)));
        } else if (type.equalsIgnoreCase("album")) {
            IAlbum album = getDAO().getAlbumById(id);
            return makeM3UFromSongList(album.getSongs());
        } else if (type.equalsIgnoreCase("artist")) {
            IArtist artist = getDAO().getArtistById(id);
            return makeM3UFromSongList(artist.getSongs());
        }
        throw new Exception("Invalid type");
    }

    /*
     * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
     */
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // return a m3u
        try {
            String type = request.getParameter("type");
            long id = Long.parseLong(request.getParameter("id"));
            response.setContentType("audio/x-mpegurl");
            response.getWriter().print(getM3U(type, id));
        } catch (Exception ex) {
            throw new ServletException("Error while processing playlist", ex);
        }
    }

    /*
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // ServletContext ctx = config.getServletContext();
    }

}