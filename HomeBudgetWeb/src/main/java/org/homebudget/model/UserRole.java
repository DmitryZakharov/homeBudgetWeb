/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "USER_ROLES")
public class UserRole {

    @Column(name = "USER_ROLE_ID")
    @Id
    private long userRoleId;
    
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserDetails user;
    
    @Column(name="AUTHORITY")
    private String authority;

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    
}
