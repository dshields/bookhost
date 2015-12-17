package amplitude.persistence.hibernate.dao;

import amplitude.persistence.hibernate.base.BasePlaylistDAO;
import org.hibernate.Session;


public class PlaylistDAO extends BasePlaylistDAO implements amplitude.persistence.hibernate.dao.iface.PlaylistDAO {

    public PlaylistDAO() {
    }

    public PlaylistDAO(Session session) {
        super(session);
    }


}