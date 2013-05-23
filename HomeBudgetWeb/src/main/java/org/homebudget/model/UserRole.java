/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "USER_ROLE")
public class UserRole {

	public enum Role {

	    ADMIN_ROLE("ROLE_ADMIN", 3), MANAGER_ROLE("ROLE_MANAGER", 2) , USER_ROLE("ROLE_USER", 1), ANONYMOUS_ROLE("ROLE_ANONYMOUS", 0);

	    private final int order;
	    private final String roleName;

	    private Role(final String roleName, final int order) {
	        this.roleName = roleName;
	        this.order = order;
	    }

	    public int order() {
	        return order;
	    }

	    public String roleName() {
	        return roleName;
	    }
	};
    
    @Id
    @GeneratedValue
    @Column(name = "USER_ROLE_ID")
    private long userRoleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE_TYPE")
    private Role userRole;
    

	public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
