package amplitude.persistence.hibernate.dao;

import amplitude.persistence.hibernate.base.BaseAlbumDAO;
import org.hibernate.Session;


public class AlbumDAO extends BaseAlbumDAO implements amplitude.persistence.hibernate.dao.iface.AlbumDAO {

    public AlbumDAO() {
    }

    public AlbumDAO(Session session) {
        super(session);
    }


}