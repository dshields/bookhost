package amplitude.persistence.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SONG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="SONG"
 */

public abstract class BaseSong implements Serializable {

    public static String REF = "Song";
    public static String PROP_ALBUM = "album";
    public static String PROP_FILE_CREATE_DATE = "fileCreateDate";
    public static String PROP_GENRE = "genre";
    public static String PROP_LENGTH = "length";
    public static String PROP_URL = "url";
    public static String PROP_SONG_ID = "songId";
    public static String PROP_TRACK_NUMBER = "trackNumber";
    public static String PROP_SONG_NAME = "songName";
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private long songId;
    // fields
    private java.lang.String songName;
    private java.lang.String url;
    private int trackNumber;
    private java.lang.String length;
    private java.util.Date fileCreateDate;
    // many to one
    private amplitude.persistence.hibernate.Genre genre;
    private amplitude.persistence.hibernate.Album album;
    // collections
    private java.util.List<amplitude.persistence.hibernate.Playlist> playlists;
    private java.util.List<amplitude.persistence.hibernate.Tag> tags;
    // constructors
    public BaseSong() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseSong(long songId) {
        this.setSongId(songId);
        initialize();
    }

    protected void initialize() {
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="identity"
     * column="SONGID"
     */
    public long getSongId() {
        return songId;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param songId the new ID
     */
    public void setSongId(long songId) {
        this.songId = songId;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: SONGNAME
     */
    public java.lang.String getSongName() {
        return songName;
    }

    /**
     * Set the value related to the column: SONGNAME
     *
     * @param songName the SONGNAME value
     */
    public void setSongName(java.lang.String songName) {
        this.songName = songName;
    }


    /**
     * Return the value associated with the column: URL
     */
    public java.lang.String getUrl() {
        return url;
    }

    /**
     * Set the value related to the column: URL
     *
     * @param url the URL value
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Return the value associated with the column: TRACKNUMBER
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     * Set the value related to the column: TRACKNUMBER
     *
     * @param trackNumber the TRACKNUMBER value
     */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }


    /**
     * Return the value associated with the column: LENGTH
     */
    public java.lang.String getLength() {
        return length;
    }

    /**
     * Set the value related to the column: LENGTH
     *
     * @param length the LENGTH value
     */
    public void setLength(java.lang.String length) {
        this.length = length;
    }


    /**
     * Return the value associated with the column: FILECREATEDATE
     */
    public java.util.Date getFileCreateDate() {
        return fileCreateDate;
    }

    /**
     * Set the value related to the column: FILECREATEDATE
     *
     * @param fileCreateDate the FILECREATEDATE value
     */
    public void setFileCreateDate(java.util.Date fileCreateDate) {
        this.fileCreateDate = fileCreateDate;
    }


    /**
     * Return the value associated with the column: GENRE
     */
    public amplitude.persistence.hibernate.Genre getGenre() {
        return genre;
    }

    /**
     * Set the value related to the column: GENRE
     *
     * @param genre the GENRE value
     */
    public void setGenre(amplitude.persistence.hibernate.Genre genre) {
        this.genre = genre;
    }


    /**
     * Return the value associated with the column: ALBUMID
     */
    public amplitude.persistence.hibernate.Album getAlbum() {
        return album;
    }

    /**
     * Set the value related to the column: ALBUMID
     *
     * @param album the ALBUMID value
     */
    public void setAlbum(amplitude.persistence.hibernate.Album album) {
        this.album = album;
    }


    /**
     * Return the value associated with the column: playlists
     */
    public java.util.List<amplitude.persistence.hibernate.Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Set the value related to the column: playlists
     *
     * @param playlists the playlists value
     */
    public void setPlaylists(java.util.List<amplitude.persistence.hibernate.Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addToplaylists(amplitude.persistence.hibernate.Playlist playlist) {
        if (null == getPlaylists())
            setPlaylists(new java.util.ArrayList<amplitude.persistence.hibernate.Playlist>());
        getPlaylists().add(playlist);
    }


    /**
     * Return the value associated with the column: Tags
     */
    public java.util.List<amplitude.persistence.hibernate.Tag> getTags() {
        return tags;
    }

    /**
     * Set the value related to the column: Tags
     *
     * @param tags the Tags value
     */
    public void setTags(java.util.List<amplitude.persistence.hibernate.Tag> tags) {
        this.tags = tags;
    }

    public void addToTags(amplitude.persistence.hibernate.Tag tag) {
        if (null == getTags())
            setTags(new java.util.ArrayList<amplitude.persistence.hibernate.Tag>());
        getTags().add(tag);
    }


    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof amplitude.persistence.hibernate.Song))
            return false;
        else {
            amplitude.persistence.hibernate.Song song = (amplitude.persistence.hibernate.Song) obj;
            return (this.getSongId() == song.getSongId());
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            return (int) this.getSongId();
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}