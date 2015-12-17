package amplitude.persistence.hibernate.dao;

import amplitude.persistence.hibernate.base.BaseGenreDAO;
import org.hibernate.Session;


public class GenreDAO extends BaseGenreDAO implements amplitude.persistence.hibernate.dao.iface.GenreDAO {

    public GenreDAO() {
    }

    public GenreDAO(Session session) {
        super(session);
    }


}