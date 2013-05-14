package org.homebudget.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Email;

@Entity(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue
	private long userId;

	@Column(name = "USER_USERNAME")
	private String userUsername;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_SURNAME")
	private String userSurname;

	@Column(name = "PASSWORD")
	private String password;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "USER_ROLE_ID")
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	@Column(name = "ENABLED")
	private int enabled;

	@Email
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DATE_OF_BIRTH")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date userBirthday;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
        
        public void addUserRole(UserRole uRole){
            this.userRoles.add(uRole);
        }

}
