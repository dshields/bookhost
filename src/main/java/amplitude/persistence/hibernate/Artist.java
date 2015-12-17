package amplitude.persistence.hibernate;

import amplitude.model.IAlbum;
import amplitude.model.IArtist;
import amplitude.model.ISong;
import amplitude.persistence.hibernate.base.BaseArtist;
import amplitude.persistence.hibernate.dao.ArtistDAO;

import java.util.ArrayList;
import java.util.List;


public class Artist extends BaseArtist implements IArtist {
    private static final long serialVersionUID = 1L;
    private ArrayList<ISong> allSongs = null;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public Artist() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public Artist(long artistId) {
        super(artistId);
    }

/*[CONSTRUCTOR MARKER END]*/

    /**
     * Constructor for primary key
     */
    public Artist(java.lang.Long artistId) {
        super(artistId);
    }

    public int getAlbumCount() {
        return getAlbums().size();
    }

    synchronized public List<ISong> getSongs() {
        if (allSongs != null)
            return allSongs;
        allSongs = new ArrayList<ISong>();
        for (IAlbum album : getAlbums()) {
            allSongs.addAll(album.getSongs());
        }
        return allSongs;
    }

    public int getTrackCount() {
        return getSongs().size();
    }

    public ArtistDAO getArtistDAO() {
        return AmplitudeDB.getArtistDAO();
    }

    public void save() {
        getArtistDAO().save(this);
    }

    public void saveOrUpdate() {
        getArtistDAO().saveOrUpdate(this);
    }

    public void delete() {
        getArtistDAO().delete(this);
    }


}