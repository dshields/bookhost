/*
 * (c) Copyright 2002 Dorwin T. Shields, Jr.
 * All Rights Reserved.
 */
package amplitude.servlets;

import amplitude.model.IAlbum;
import amplitude.model.IAmplitudeDb;
import amplitude.model.IArtist;
import amplitude.persistence.hibernate.DBLoader;
import amplitude.persistence.hibernate.DBUtils;
import amplitude.persistence.hibernate.HibernateSessionFactory;
import amplitude.persistence.hibernate.dao._RootDAO;
import amplitude.system.AmplitudeProperties;
import amplitude.system.AmplitudeSystem;
import amplitude.utils.JSONUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class SongServlet extends BaseServlet {

    static final long serialVersionUID = 1001;
    static IAmplitudeDb dao = null;

    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("searchtext") != null) {
            search(request, response, request.getParameter("searchtext"));
        } else if (request.getParameter("artist") == null && request.getParameter("album") == null) {
            showSongPage(request, response);
        } else if (request.getParameter("artist") != null) {
            showArtist(Long.parseLong(request.getParameter("artist")), request, response);
        } else if (request.getParameter("album") != null) {
            showAlbum(Long.parseLong(request.getParameter("album")), request, response);
        }
        response.flushBuffer();
    }

    public void search(HttpServletRequest request, HttpServletResponse response, String search) throws ServletException, IOException {
        search = search.toLowerCase();
        List<? extends IArtist> artists = getDAO().findArtists(search);
        if (artists == null || artists.isEmpty())
            return;
        JSONArray jsonArtists = JSONUtil.createJSONArtistArray(artists);
        JSONObject json = new JSONObject();
        json.put("artists", jsonArtists);
        response.addHeader("X-JSON", "true");
        response.getWriter().write(jsonArtists.toString());
    }

    public void showSongPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/songs.html");
        if (dispatcher != null)
            dispatcher.forward(request, response);
    }

    private void showArtist(long l, HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<? extends IArtist> artists = null;
        if (l == -1) {
            artists = getDAO().getAllArtists();
        } else {
            artists = Collections.<IArtist>singletonList(getDAO().getArtistById(l));
        }
        if (artists == null || artists.isEmpty())
            return;
        JSONArray jsonArtists = JSONUtil.createJSONArtistArray(artists);
        JSONObject json = new JSONObject();
        json.put("artists", jsonArtists);
        response.addHeader("X-JSON", "true");
        response.getWriter().write(jsonArtists.toString());
    }

    private void showAlbum(long l, HttpServletRequest request, HttpServletResponse response) throws IOException {
        IAlbum album = getDAO().getAlbumById(l);
        if (album == null)
            return;
        JSONArray jsonArtists = JSONUtil.createJSONArtistArray(Collections.<IArtist>singletonList(album.getArtist()));
        JSONObject json = new JSONObject();
        json.put("artists", jsonArtists);
        response.addHeader("X-JSON", "true");
        response.getWriter().write(jsonArtists.toString());
    }

    /*
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext ctx = config.getServletContext();
        try {
            AmplitudeSystem.initialize();
            dao = AmplitudeSystem.getAmplitudeDB();
            if (dao.databaseExists()) {
                DBUtils.initialize(false);
            }
            ctx.setAttribute("dao", dao);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException("couldn't load mp3 files", ex);
        }
    }

}