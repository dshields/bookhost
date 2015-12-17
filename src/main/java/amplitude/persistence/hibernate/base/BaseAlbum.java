package amplitude.persistence.hibernate.base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public abstract class BaseAlbum implements Serializable {

    private int hashCode = Integer.MIN_VALUE;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long albumId;
    private java.lang.String albumName;
    private java.lang.String year;
    private int trackCount;
    // many to one
    private amplitude.persistence.hibernate.Artist artist;
    // collections
    private java.util.List<amplitude.persistence.hibernate.Song> songs;

    // constructors
    public BaseAlbum() {
    }

    /**
     * Constructor for primary key
     */
    public BaseAlbum(long albumId) {
        this.setAlbumId(albumId);
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="identity"
     * column="ALBUMID"
     */
    public long getAlbumId() {
        return albumId;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param albumId the new ID
     */
    public void setAlbumId(long albumId) {
        this.albumId = albumId;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: ALBUMNAME
     */
    public java.lang.String getAlbumName() {
        return albumName;
    }

    /**
     * Set the value related to the column: ALBUMNAME
     *
     * @param albumName the ALBUMNAME value
     */
    public void setAlbumName(java.lang.String albumName) {
        this.albumName = albumName;
    }


    /**
     * Return the value associated with the column: YEAR
     */
    public java.lang.String getYear() {
        return year;
    }

    /**
     * Set the value related to the column: YEAR
     *
     * @param year the YEAR value
     */
    public void setYear(java.lang.String year) {
        this.year = year;
    }


    /**
     * Return the value associated with the column: TRACKCOUNT
     */
    public int getTrackCount() {
        return trackCount;
    }

    /**
     * Set the value related to the column: TRACKCOUNT
     *
     * @param trackCount the TRACKCOUNT value
     */
    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }


    /**
     * Return the value associated with the column: ARTISTID
     */
    public amplitude.persistence.hibernate.Artist getArtist() {
        return artist;
    }

    /**
     * Set the value related to the column: ARTISTID
     *
     * @param artist the ARTISTID value
     */
    public void setArtist(amplitude.persistence.hibernate.Artist artist) {
        this.artist = artist;
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
        if (!(obj instanceof amplitude.persistence.hibernate.Album))
            return false;
        else {
            amplitude.persistence.hibernate.Album album = (amplitude.persistence.hibernate.Album) obj;
            return (this.getAlbumId() == album.getAlbumId());
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            return (int) this.getAlbumId();
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}