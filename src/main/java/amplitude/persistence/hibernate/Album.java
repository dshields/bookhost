package amplitude.persistence.hibernate;

import amplitude.model.IAlbum;
import amplitude.persistence.hibernate.base.BaseAlbum;
import amplitude.persistence.hibernate.dao.AlbumDAO;


public class Album extends BaseAlbum implements IAlbum {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public Album() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public Album(long albumId) {
        super(albumId);
    }

/*[CONSTRUCTOR MARKER END]*/

    public String getAlbumYear() {
        return "1900";
    }

    public AlbumDAO getAlbumDAO() {
        return AmplitudeDB.getAlbumDAO();
    }

    public void save() {
        getAlbumDAO().save(this);
    }

    public void saveOrUpdate() {
        getAlbumDAO().saveOrUpdate(this);
    }

    public void delete() {
        getAlbumDAO().delete(this);
    }
}