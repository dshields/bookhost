package amplitude.persistence.hibernate;

import amplitude.model.IArtist;
import amplitude.model.ISong;
import amplitude.persistence.hibernate.base.BaseSong;
import amplitude.persistence.hibernate.dao.SongDAO;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;


public class Song extends BaseSong implements ISong {
    private static final long serialVersionUID = 1L;
    private Path file;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public Song() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public Song(long songId) {
        super(songId);
    }

    /*[CONSTRUCTOR MARKER END]*/
    public String getAlbumName() {
        return getAlbum().getAlbumName();
    }

    public IArtist getArtist() {
        return getAlbum().getArtist();
    }

    public String getArtistName() {
        return getArtist().getArtistName();
    }

    public InputStream getStream() throws IOException {
        FileInputStream fis = new FileInputStream(file.toFile());
        BufferedInputStream bis = new BufferedInputStream(fis);
        return bis;
    }

    public String getGenreName() {
        return getGenre().getGenreName();
    }

    public URL getURL() {
        try {
            URL url = new URL(getUrl());
            return url;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void setFile(Path songFile) throws Exception {
        file = songFile;
        setUrl(songFile.toUri().toString());
    }

    public SongDAO getSongDAO() {
        return AmplitudeDB.getSongDAO();
    }

    public void save() {
        getSongDAO().save(this);
    }

    public void saveOrUpdate() {
        getSongDAO().saveOrUpdate(this);
    }

    public void delete() {
        getSongDAO().delete(this);
    }
}