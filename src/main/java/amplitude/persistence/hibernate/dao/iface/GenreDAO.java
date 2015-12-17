package amplitude.persistence.hibernate.dao.iface;

public interface GenreDAO {
    public amplitude.persistence.hibernate.Genre get(java.lang.Integer key);

    public amplitude.persistence.hibernate.Genre load(java.lang.Integer key);

    public java.util.List<amplitude.persistence.hibernate.Genre> findAll();


    /**
     * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
     * of the identifier property if the assigned generator is used.)
     *
     * @param genre a transient instance of a persistent class
     * @return the class identifier
     */
    public java.lang.Integer save(amplitude.persistence.hibernate.Genre genre);

    /**
     * Either save() or update() the given instance, depending upon the value of its identifier property. By default
     * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
     * identifier property mapping.
     *
     * @param genre a transient instance containing new or updated state
     */
    public void saveOrUpdate(amplitude.persistence.hibernate.Genre genre);

    /**
     * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
     * instance with the same identifier in the current session.
     *
     * @param genre a transient instance containing updated state
     */
    public void update(amplitude.persistence.hibernate.Genre genre);

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     *
     * @param id the instance ID to be removed
     */
    public void delete(java.lang.Integer id);

    /**
     * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
     * Session or a transient instance with an identifier associated with existing persistent state.
     *
     * @param genre the instance to be removed
     */
    public void delete(amplitude.persistence.hibernate.Genre genre);


}