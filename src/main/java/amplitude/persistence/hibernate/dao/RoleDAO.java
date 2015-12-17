package amplitude.persistence.hibernate.dao;

import amplitude.persistence.hibernate.base.BaseRoleDAO;
import org.hibernate.Session;


public class RoleDAO extends BaseRoleDAO implements amplitude.persistence.hibernate.dao.iface.RoleDAO {

    public RoleDAO() {
    }

    public RoleDAO(Session session) {
        super(session);
    }


}