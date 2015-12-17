package amplitude.persistence.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the USER table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="USER"
 */

public abstract class BaseUser implements Serializable {

    public static String REF = "User";
    public static String PROP_PASSWORD = "password";
    public static String PROP_USER_ID = "userId";
    public static String PROP_USER_NAME = "userName";
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Long userId;
    // fields
    private java.lang.String userName;
    private java.lang.String password;
    // collections
    private java.util.Set<amplitude.persistence.hibernate.Role> roles;
    private java.util.Set<amplitude.persistence.hibernate.Tag> tags;
    // constructors
    public BaseUser() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseUser(java.lang.Long userId) {
        this.setUserId(userId);
        initialize();
    }

    protected void initialize() {
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="identity"
     * column="USERID"
     */
    public java.lang.Long getUserId() {
        return userId;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param userId the new ID
     */
    public void setUserId(java.lang.Long userId) {
        this.userId = userId;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: USERNAME
     */
    public java.lang.String getUserName() {
        return userName;
    }

    /**
     * Set the value related to the column: USERNAME
     *
     * @param userName the USERNAME value
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Return the value associated with the column: PASSWORD
     */
    public java.lang.String getPassword() {
        return password;
    }

    /**
     * Set the value related to the column: PASSWORD
     *
     * @param password the PASSWORD value
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Return the value associated with the column: roles
     */
    public java.util.Set<amplitude.persistence.hibernate.Role> getRoles() {
        return roles;
    }

    /**
     * Set the value related to the column: roles
     *
     * @param roles the roles value
     */
    public void setRoles(java.util.Set<amplitude.persistence.hibernate.Role> roles) {
        this.roles = roles;
    }

    public void addToroles(amplitude.persistence.hibernate.Role role) {
        if (null == getRoles())
            setRoles(new java.util.TreeSet<amplitude.persistence.hibernate.Role>());
        getRoles().add(role);
    }


    /**
     * Return the value associated with the column: tags
     */
    public java.util.Set<amplitude.persistence.hibernate.Tag> getTags() {
        return tags;
    }

    /**
     * Set the value related to the column: tags
     *
     * @param tags the tags value
     */
    public void setTags(java.util.Set<amplitude.persistence.hibernate.Tag> tags) {
        this.tags = tags;
    }

    public void addTotags(amplitude.persistence.hibernate.Tag tag) {
        if (null == getTags())
            setTags(new java.util.TreeSet<amplitude.persistence.hibernate.Tag>());
        getTags().add(tag);
    }


    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof amplitude.persistence.hibernate.User))
            return false;
        else {
            amplitude.persistence.hibernate.User user = (amplitude.persistence.hibernate.User) obj;
            if (null == this.getUserId() || null == user.getUserId())
                return false;
            else
                return (this.getUserId().equals(user.getUserId()));
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getUserId())
                return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getUserId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}