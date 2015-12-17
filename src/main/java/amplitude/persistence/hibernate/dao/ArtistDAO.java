package amplitude.persistence.hibernate.dao;

import amplitude.persistence.hibernate.base.BaseArtistDAO;
import org.hibernate.Session;


public class ArtistDAO extends BaseArtistDAO implements amplitude.persistence.hibernate.dao.iface.ArtistDAO {

    public ArtistDAO() {
    }

    public ArtistDAO(Session session) {
        super(session);
    }


}