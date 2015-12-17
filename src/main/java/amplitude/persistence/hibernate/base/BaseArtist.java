package amplitude.persistence.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ARTIST table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="ARTIST"
 */

public abstract class BaseArtist implements Serializable {

    public static String REF = "Artist";
    public static String PROP_ARTIST_NAME = "artistName";
    public static String PROP_ARTIST_ID = "artistId";
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private long artistId;
    // fields
    private java.lang.String artistName;
    // collections
    private java.util.List<amplitude.persistence.hibernate.Album> albums;

    // constructors
    public BaseArtist() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseArtist(long artistId) {
        this.setArtistId(artistId);
        initialize();
    }

    protected void initialize() {
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="identity"
     * column="ARTISTID"
     */
    public long getArtistId() {
        return artistId;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param artistId the new ID
     */
    public void setArtistId(long artistId) {
        this.artistId = artistId;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: ARTISTNAME
     */
    public java.lang.String getArtistName() {
        return artistName;
    }

    /**
     * Set the value related to the column: ARTISTNAME
     *
     * @param artistName the ARTISTNAME value
     */
    public void setArtistName(java.lang.String artistName) {
        this.artistName = artistName;
    }


    /**
     * Return the value associated with the column: albums
     */
    public java.util.List<amplitude.persistence.hibernate.Album> getAlbums() {
        return albums;
    }

    /**
     * Set the value related to the column: albums
     *
     * @param albums the albums value
     */
    public void setAlbums(java.util.List<amplitude.persistence.hibernate.Album> albums) {
        this.albums = albums;
    }

    public void addToalbums(amplitude.persistence.hibernate.Album album) {
        if (null == getAlbums())
            setAlbums(new java.util.ArrayList<amplitude.persistence.hibernate.Album>());
        getAlbums().add(album);
    }


    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof amplitude.persistence.hibernate.Artist))
            return false;
        else {
            amplitude.persistence.hibernate.Artist artist = (amplitude.persistence.hibernate.Artist) obj;
            return (this.getArtistId() == artist.getArtistId());
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            return (int) this.getArtistId();
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}