package amplitude.persistence.hibernate.dao;

import amplitude.persistence.hibernate.base.BaseUserDAO;
import org.hibernate.Session;


public class UserDAO extends BaseUserDAO implements amplitude.persistence.hibernate.dao.iface.UserDAO {

    public UserDAO() {
    }

    public UserDAO(Session session) {
        super(session);
    }


}