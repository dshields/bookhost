package amplitude.persistence.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the TAG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="TAG"
 */

public abstract class BaseTag implements Serializable {

    public static String REF = "Tag";
    public static String PROP_TAG_DESCRIPTION = "tagDescription";
    public static String PROP_TAG_NAME = "tagName";
    public static String PROP_TAG_ID = "tagId";
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Long tagId;
    // fields
    private java.lang.String tagName;
    private java.lang.String tagDescription;
    // collections
    private java.util.List<amplitude.persistence.hibernate.Song> songs;
    private java.util.Set<amplitude.persistence.hibernate.User> users;
    // constructors
    public BaseTag() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseTag(java.lang.Long tagId) {
        this.setTagId(tagId);
        initialize();
    }

    protected void initialize() {
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="identity"
     * column="TAGID"
     */
    public java.lang.Long getTagId() {
        return tagId;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param tagId the new ID
     */
    public void setTagId(java.lang.Long tagId) {
        this.tagId = tagId;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: TAGNAME
     */
    public java.lang.String getTagName() {
        return tagName;
    }

    /**
     * Set the value related to the column: TAGNAME
     *
     * @param tagName the TAGNAME value
     */
    public void setTagName(java.lang.String tagName) {
        this.tagName = tagName;
    }


    /**
     * Return the value associated with the column: TAGDESCRIPTION
     */
    public java.lang.String getTagDescription() {
        return tagDescription;
    }

    /**
     * Set the value related to the column: TAGDESCRIPTION
     *
     * @param tagDescription the TAGDESCRIPTION value
     */
    public void setTagDescription(java.lang.String tagDescription) {
        this.tagDescription = tagDescription;
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


    /**
     * Return the value associated with the column: users
     */
    public java.util.Set<amplitude.persistence.hibernate.User> getUsers() {
        return users;
    }

    /**
     * Set the value related to the column: users
     *
     * @param users the users value
     */
    public void setUsers(java.util.Set<amplitude.persistence.hibernate.User> users) {
        this.users = users;
    }

    public void addTousers(amplitude.persistence.hibernate.User user) {
        if (null == getUsers())
            setUsers(new java.util.TreeSet<amplitude.persistence.hibernate.User>());
        getUsers().add(user);
    }


    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof amplitude.persistence.hibernate.Tag))
            return false;
        else {
            amplitude.persistence.hibernate.Tag tag = (amplitude.persistence.hibernate.Tag) obj;
            if (null == this.getTagId() || null == tag.getTagId())
                return false;
            else
                return (this.getTagId().equals(tag.getTagId()));
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getTagId())
                return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getTagId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}