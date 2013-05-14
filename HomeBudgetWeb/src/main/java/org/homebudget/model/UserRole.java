/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "USER_ROLES")
public class UserRole {

    public static enum Authority {
        ADMIN_ROLE, USER_ROLE, MANAGER_ROLE
    };
    
    @Id
    @GeneratedValue
    @Column(name = "USER_ROLE_ID")
    private long userRoleId;

    @Column(name = "AUTHORITY")
    private Authority authority;

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
