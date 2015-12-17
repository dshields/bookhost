package amplitude.persistence.hibernate.base;

import amplitude.persistence.hibernate.dao.iface.PlaylistDAO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BasePlaylistDAO extends amplitude.persistence.hibernate.dao._RootDAO {

    public static PlaylistDAO instance;

    public BasePlaylistDAO() {
    }

    // query name references


    public BasePlaylistDAO(Session session) {
        super(session);
    }

    /**
     * Return a singleton of the DAO
     */
    public static PlaylistDAO getInstance() {
        if (null == instance)
            instance = new amplitude.persistence.hibernate.dao.PlaylistDAO();
        return instance;
    }

    public Class getReferenceClass() {
        return amplitude.persistence.hibernate.Playlist.class;
    }

    public Order getDefaultOrder() {
        return null;
    }

    /**
     * Cast the object as a amplitude.persistence.hibernate.Playlist
     */
    public amplitude.persistence.hibernate.Playlist cast(Object object) {
        return (amplitude.persistence.hibernate.Playlist) object;
    }

    public amplitude.persistence.hibernate.Playlist get(long key) {
        return (amplitude.persistence.hibernate.Playlist) get(getReferenceClass(), new java.lang.Long(key));
    }

    public amplitude.persistence.hibernate.Playlist get(long key, Session s) {
        return (amplitude.persistence.hibernate.Playlist) get(getReferenceClass(), new java.lang.Long(key), s);
    }

    public amplitude.persistence.hibernate.Playlist load(long key) {
        return (amplitude.persistence.hibernate.Playlist) load(getReferenceClass(), new java.lang.Long(key));
    }

    public amplitude.persistence.hibernate.Playlist load(long key, Session s) {
        return (amplitude.persistence.hibernate.Playlist) load(getReferenceClass(), new java.lang.Long(key), s);
    }

    public amplitude.persistence.hibernate.Playlist loadInitialize(long key, Session s) {
        amplitude.persistence.hibernate.Playlist obj = load(key, s);
        if (!Hibernate.isInitialized(obj)) {
            Hibernate.initialize(obj);
        }
        return obj;
    }

/* Generic methods */

    /**
     * Return all objects related to the implementation of this DAO with no filter.
     */
    public java.util.List<amplitude.persistence.hibernate.Playlist> findAll() {
        return super.findAll();
    }

    /**
     * Return all objects related to the implementation of this DAO with no filter.
     */
    public java.util.List<amplitude.persistence.hibernate.Playlist> findAll(Order defaultOrder) {
        return super.findAll(defaultOrder);
    }

    /**
     * Return all objects related to the implementation of this DAO with no filter.
     * Use the session given.
     *
     * @param s the Session
     */
    public java.util.List<amplitude.persistence.hibernate.Playlist> findAll(Session s, Order defaultOrder) {
        return super.findAll(s, defaultOrder);
    }

    /**
     * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
     * of the identifier property if the assigned generator is used.)
     *
     * @param playlist a transient instance of a persistent class
     * @return the class identifier
     */
    public java.lang.Long save(amplitude.persistence.hibernate.Playlist playlist) {
        return (java.lang.Long) super.save(playlist);
    }

    /**
     * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
     * of the identifier property if the assigned generator is used.)
     * Use the Session given.
     *
     * @param playlist a transient instance of a persistent class
     * @param s        the Session
     * @return the class identifier
     */
    public java.lang.Long save(amplitude.persistence.hibernate.Playlist playlist, Session s) {
        return (java.lang.Long) save((Object) playlist, s);
    }

    /**
     * Either save() or update() the given instance, depending upon the value of its identifier property. By default
     * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
     * identifier property mapping.
     *
     * @param playlist a transient instance containing new or updated state
     */
    public void saveOrUpdate(amplitude.persistence.hibernate.Playlist playlist) {
        saveOrUpdate((Object) playlist);
    }

    /**
     * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
     * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
     * property mapping.
     * Use the Session given.
     *
     * @param playlist a transient instance containing new or updated state.
     * @param s        the Session.
     */
    public void saveOrUpdate(amplitude.persistence.hibernate.Playlist playlist, Session s) {
        saveOrUpdate((Object) playlist, s);
    }

    /**
     * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
     * instance with the same identifier in the current session.
     *
     * @param playlist a transient instance containing updated state
     */
    public void update(amplitude.persistence.hibernate.Playlist playlist) {
        update((Object) playlist);
    }

    /**
     * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
     * instance with the same identifier in the current session.
     * Use the Session given.
     *
     * @param playlist a transient instance containing updated state
     * @param the      Session
     */
    public void update(amplitude.persistence.hibernate.Playlist playlist, Session s) {
        update((Object) playlist, s);
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     *
     * @param id the instance ID to be removed
     */
    public void delete(long id) {
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
    public void delete(long id, Session s) {
        delete((Object) load(id, s), s);
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     *
     * @param playlist the instance to be removed
     */
    public void delete(amplitude.persistence.hibernate.Playlist playlist) {
        delete((Object) playlist);
    }

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     * Use the Session given.
     *
     * @param playlist the instance to be removed
     * @param s        the Session
     */
    public void delete(amplitude.persistence.hibernate.Playlist playlist, Session s) {
        delete((Object) playlist, s);
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
    public void refresh(amplitude.persistence.hibernate.Playlist playlist, Session s) {
        refresh((Object) playlist, s);
    }


}