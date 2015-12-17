package amplitude.persistence.hibernate.dao;

import amplitude.persistence.hibernate.base.BaseTagDAO;
import org.hibernate.Session;


public class TagDAO extends BaseTagDAO implements amplitude.persistence.hibernate.dao.iface.TagDAO {

    public TagDAO() {
    }

    public TagDAO(Session session) {
        super(session);
    }


}