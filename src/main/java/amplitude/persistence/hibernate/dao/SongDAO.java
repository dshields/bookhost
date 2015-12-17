package amplitude.persistence.hibernate.dao;

import amplitude.persistence.hibernate.base.BaseSongDAO;
import org.hibernate.Session;


public class SongDAO extends BaseSongDAO implements amplitude.persistence.hibernate.dao.iface.SongDAO {

    public SongDAO() {
    }

    public SongDAO(Session session) {
        super(session);
    }


}