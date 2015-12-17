package amplitude.persistence.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the PLAYLIST table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="PLAYLIST"
 */

public abstract class BasePlaylist implements Serializable {

    public static String REF = "Playlist";
    public static String PROP_PLAYLIST_ID = "playlistId";
    public static String PROP_PLAYLIST_NAME = "playlistName";
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private long playlistId;
    // fields
    private java.lang.String playlistName;
    // collections
    private java.util.List<amplitude.model.ISong> songs;

    // constructors
    public BasePlaylist() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BasePlaylist(long playlistId) {
        this.setPlaylistId(playlistId);
        initialize();
    }

    protected void initialize() {
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="identity"
     * column="PLAYLISTD"
     */
    public long getPlaylistId() {
        return playlistId;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param playlistId the new ID
     */
    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: PLAYLISTNAME
     */
    public java.lang.String getPlaylistName() {
        return playlistName;
    }

    /**
     * Set the value related to the column: PLAYLISTNAME
     *
     * @param playlistName the PLAYLISTNAME value
     */
    public void setPlaylistName(java.lang.String playlistName) {
        this.playlistName = playlistName;
    }


    /**
     * Return the value associated with the column: songs
     */
    public java.util.List<amplitude.model.ISong> getSongs() {
        return songs;
    }

    /**
     * Set the value related to the column: songs
     *
     * @param songs the songs value
     */
    public void setSongs(java.util.List<amplitude.model.ISong> songs) {
        this.songs = songs;
    }


    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof amplitude.persistence.hibernate.Playlist))
            return false;
        else {
            amplitude.persistence.hibernate.Playlist playlist = (amplitude.persistence.hibernate.Playlist) obj;
            return (this.getPlaylistId() == playlist.getPlaylistId());
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            return (int) this.getPlaylistId();
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}