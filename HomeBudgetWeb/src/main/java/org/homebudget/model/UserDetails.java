package org.homebudget.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity(name = "USER_DETAILS")
public class UserDetails {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    private long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_SURNAME")
    private String userSurname;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private int enabled;

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date userBirthday;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
