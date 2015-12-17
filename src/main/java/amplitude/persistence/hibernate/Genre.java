package amplitude.persistence.hibernate;

import amplitude.persistence.hibernate.base.BaseGenre;


public class Genre extends BaseGenre {
    private static final long serialVersionUID = 1L;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public Genre() {
        super();
    }

    /**
     * Constructor for primary key
     */
    public Genre(java.lang.Integer genreId) {
        super(genreId);
    }

/*[CONSTRUCTOR MARKER END]*/


}