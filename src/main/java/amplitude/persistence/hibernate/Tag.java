package amplitude.persistence.hibernate;

import amplitude.persistence.hibernate.base.BaseTag;
import amplitude.persistence.hibernate.dao.TagDAO;


public class Tag extends BaseTag {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public Tag() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public Tag(java.lang.Long tagId) {
        super(tagId);
    }

/*[CONSTRUCTOR MARKER END]*/

    public TagDAO getTagDAO() {
        return AmplitudeDB.getTagDAO();
    }

    public void save() {
        getTagDAO().save(this);
    }

    public void saveOrUpdate() {
        getTagDAO().saveOrUpdate(this);
    }

    public void delete() {
        getTagDAO().delete(this);
    }

}