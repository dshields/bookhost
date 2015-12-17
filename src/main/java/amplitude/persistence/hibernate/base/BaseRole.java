package amplitude.persistence.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ROLE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="ROLE"
 */

public abstract class BaseRole implements Serializable {

    public static String REF = "Role";
    public static String PROP_ROLE_DESCRIPTION = "roleDescription";
    public static String PROP_ROLE_NAME = "roleName";
    public static String PROP_ROLE_ID = "roleId";
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Long roleId;
    // fields
    private java.lang.String roleName;
    private java.lang.String roleDescription;
    // collections
    private java.util.Set<amplitude.persistence.hibernate.User> users;

    // constructors
    public BaseRole() {
        initialize();
    }
    /**
     * Constructor for primary key
     */
    public BaseRole(java.lang.Long roleId) {
        this.setRoleId(roleId);
        initialize();
    }

    protected void initialize() {
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="identity"
     * column="ROLEID"
     */
    public java.lang.Long getRoleId() {
        return roleId;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param roleId the new ID
     */
    public void setRoleId(java.lang.Long roleId) {
        this.roleId = roleId;
        this.hashCode = Integer.MIN_VALUE;
    }


    /**
     * Return the value associated with the column: ROLENAME
     */
    public java.lang.String getRoleName() {
        return roleName;
    }

    /**
     * Set the value related to the column: ROLENAME
     *
     * @param roleName the ROLENAME value
     */
    public void setRoleName(java.lang.String roleName) {
        this.roleName = roleName;
    }


    /**
     * Return the value associated with the column: ROLEDESCRIPTION
     */
    public java.lang.String getRoleDescription() {
        return roleDescription;
    }

    /**
     * Set the value related to the column: ROLEDESCRIPTION
     *
     * @param roleDescription the ROLEDESCRIPTION value
     */
    public void setRoleDescription(java.lang.String roleDescription) {
        this.roleDescription = roleDescription;
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
        if (!(obj instanceof amplitude.persistence.hibernate.Role))
            return false;
        else {
            amplitude.persistence.hibernate.Role role = (amplitude.persistence.hibernate.Role) obj;
            if (null == this.getRoleId() || null == role.getRoleId())
                return false;
            else
                return (this.getRoleId().equals(role.getRoleId()));
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getRoleId())
                return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getRoleId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString() {
        return super.toString();
    }


}