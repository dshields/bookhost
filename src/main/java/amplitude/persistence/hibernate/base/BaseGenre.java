package amplitude.persistence.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the GENRE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="GENRE"
 */

public abstract class BaseGenre implements Serializable {

    public static String REF = "Genre";
    public static String PROP_GENRE_ID = "genreId";
    public static String PROP_GENRE_NAME = "genreName";
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer genreId;
    // fields
    private java.lang.String genreName;
    // collections
    private java.util.List<amplitude.persistence.hibernate.Song> songs;

    // constructors
    public BaseGenre() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseGenre(java.lang.Integer genreId) {
        this.setGenreId(genreId);
        initialize();
    }

    protected void initialize() {
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="identity"
     * column="GENREID"
     */
    public java.lang.Integer getGenreId() {
        return genreId;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param genreId the new ID
     */
    public void setGenreId(java.lang.Integer genreId) {
        this.genreId = genreId;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: GENRENAME
     */
    public java.lang.String getGenreName() {
        return genreName;
    }

    /**
     * Set the value related to the column: GENRENAME
     *
     * @param genreName the GENRENAME value
     */
    public void setGenreName(java.lang.String genreName) {
        this.genreName = genreName;
    }


    /**
     * Return the value associated with the column: songs
     */
    public java.util.List<amplitude.persistence.hibernate.Song> getSongs() {
        return songs;
    }

    /**
     * Set the value related to the column: songs
     *
     * @param songs the songs value
     */
    public void setSongs(java.util.List<amplitude.persistence.hibernate.Song> songs) {
        this.songs = songs;
    }

    public void addTosongs(amplitude.persistence.hibernate.Song song) {
        if (null == getSongs())
            setSongs(new java.util.ArrayList<amplitude.persistence.hibernate.Song>());
        getSongs().add(song);
    }


    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof amplitude.persistence.hibernate.Genre))
            return false;
        else {
            amplitude.persistence.hibernate.Genre genre = (amplitude.persistence.hibernate.Genre) obj;
            if (null == this.getGenreId() || null == genre.getGenreId())
                return false;
            else
                return (this.getGenreId().equals(genre.getGenreId()));
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getGenreId())
                return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getGenreId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}