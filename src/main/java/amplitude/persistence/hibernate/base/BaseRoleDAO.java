package amplitude.persistence.hibernate.base;

import amplitude.persistence.hibernate.dao.iface.RoleDAO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseRoleDAO extends amplitude.persistence.hibernate.dao._RootDAO {

    public static RoleDAO instance;

    public BaseRoleDAO() {
    }

    // query name references


    public BaseRoleDAO(Session session) {
        super(session);
    }

    /**
     * Return a singleton of the DAO
     */
    public static RoleDAO getInstance() {
        if (null == instance)
            instance = new amplitude.persistence.hibernate.dao.RoleDAO();
        return instance;
    }

    public Class getReferenceClass() {
        return amplitude.persistence.hibernate.Role.class;
    }

    public Order getDefaultOrder() {
        return null;
    }

    /**
     * Cast the object as a amplitude.persistence.hibernate.Role
     */
    public amplitude.persistence.hibernate.Role cast(Object object) {
        return (amplitude.persistence.hibernate.Role) object;
    }

    public amplitude.persistence.hibernate.Role get(java.lang.Long key) {
        return (amplitude.persistence.hibernate.Role) get(getReferenceClass(), key);
    }

    public amplitude.persistence.hibernate.Role get(java.lang.Long key, Session s) {
        return (amplitude.persistence.hibernate.Role) get(getReferenceClass(), key, s);
    }

    public amplitude.persistence.hibernate.Role load(java.lang.Long key) {
        return (amplitude.persistence.hibernate.Role) load(getReferenceClass(), key);
    }

    public amplitude.persistence.hibernate.Role load(java.lang.Long key, Session s) {
        return (amplitude.persistence.hibernate.Role) load(getReferenceClass(), key, s);
    }

    public amplitude.persistence.hibernate.Role loadInitialize(java.lang.Long key, Session s) {
        amplitude.persistence.hibernate.Role obj = load(key, s);
        if (!Hibernate.isInitialized(obj)) {
            Hibernate.initialize(obj);
        }
        return obj;
    }

/* Generic methods */

    /**
     * Return all objects related to the implementation of this DAO with no filter.
     */
    public java.util.List<amplitude.persistence.hibernate.Role> findAll() {
        return super.findAll();
    }

    /**
     * Return all objects related to the implementation of this DAO with no filter.
     */
    public java.util.List<amplitude.persistence.hibernate.Role> findAll(Order defaultOrder) {
        return super.findAll(defaultOrder);
    }

    /**
     * Return all objects related to the implementation of this DAO with no filter.
     * Use the session given.
     *
     * @param s the Session
     */
    public java.util.List<amplitude.persistence.hibernate.Role> findAll(Session s, Order defaultOrder) {
        return super.findAll(s, defaultOrder);
    }

    /**
     * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
     * of the identifier property if the assigned generator is used.)
     *
     * @param role a transient instance of a persistent class
     * @return the class identifier
     */
    public java.lang.Long save(amplitude.persistence.hibernate.Role role) {
        return (java.lang.Long) super.save(role);
    }

    /**
     * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
     * of the identifier property if the assigned generator is used.)
     * Use the Session given.
     *
     * @param role a transient instance of a persistent class
     * @param s    the Session
     * @return the class identifier
     */
    public java.lang.Long save(amplitude.persistence.hibernate.Role role, Session s) {
        return (java.lang.Long) save((Object) role, s);
    }

    /**
     * Either save() or update() the given instance, depending upon the value of its identifier property. By default
     * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
     * identifier property mapping.
     *
     * @param role a transient instance containing new or updated state
     */
    public void saveOrUpdate(amplitude.persistence.hibernate.Role role) {
        saveOrUpdate((Object) role);
    }

    /**
     * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
     * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
     * property mapping.
     * Use the Session given.
     *
     * @param role a transient instance containing new or updated state.
     * @param s    the Session.
     */
    public void saveOrUpdate(amplitude.persistence.hibernate.Role role, Session s) {
        saveOrUpdate((Object) role, s);
    }

    /**
     * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
     * instance with the same identifier in the current session.
     *
     * @param role a transient instance containing updated state
     */
    public void update(amplitude.persistence.hibernate.Role role) {
        update((Object) role);
    }

    /**
     * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
     * instance with the same identifier in the current session.
     * Use the Session given.
     *
     * @param role a transient instance containing updated state
     * @param the  Session
     */
    public void update(amplitude.persistence.hibernate.Role role, Session s) {
        update((Object) role, s);
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     *
     * @param id the instance ID to be removed
     */
    public void delete(java.lang.Long id) {
        delete((Object) load(id));
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     * Use the Session given.
     *
     * @param id the instance ID to be removed
     * @param s  the Session
     */
    public void delete(java.lang.Long id, Session s) {
        delete((Object) load(id, s), s);
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     *
     * @param role the instance to be removed
     */
    public void delete(amplitude.persistence.hibernate.Role role) {
        delete((Object) role);
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     * Use the Session given.
     *
     * @param role the instance to be removed
     * @param s    the Session
     */
    public void delete(amplitude.persistence.hibernate.Role role, Session s) {
        delete((Object) role, s);
    }

    /**
     * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
     * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
     * For example
     * <ul>
     * <li>where a database trigger alters the object state upon insert or update</li>
     * <li>after executing direct SQL (eg. a mass update) in the same session</li>
     * <li>after inserting a Blob or Clob</li>
     * </ul>
     */
    public void refresh(amplitude.persistence.hibernate.Role role, Session s) {
        refresh((Object) role, s);
    }


}