package amplitude.persistence.hibernate;

import amplitude.persistence.hibernate.base.BaseUser;


public class User extends BaseUser {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public User() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public User(java.lang.Long userId) {
        super(userId);
    }

/*[CONSTRUCTOR MARKER END]*/


}