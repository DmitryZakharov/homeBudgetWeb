package org.homebudget.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

/**
 * @author dza
 * 
 */
@Entity(name = "USER_DETAILS")
public class UserDetails {

   @Id
   @Column(name = "USER_ID")
   @GeneratedValue
   private long id;

   // @NotNull
   @Column(name = "USER_USERNAME")
   private String username;

   @Column(name = "USER_NAME")
   private String fname;

   @Column(name = "USER_SURNAME")
   private String sname;

   // @NotNull
   @Column(name = "PASSWORD")
   private String password;

   @Transient
   private String confpassword;

   @ManyToMany(cascade = CascadeType.ALL)
   @JoinTable(name = "USER_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ROLE_ID"))
   private Set<UserRole> roles = new HashSet<UserRole>();

   @NotNull
   @Email
   @Column(name = "EMAIL")
   private String email;

   @Column(name = "DATE_OF_BIRTH")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date birthday;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private BinaryResource userPic;

   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
   @JoinColumn(name = "METADATA_ID")
   private UserMetadata metadata;
   
   /**
    * Default constructor
    */
   public UserDetails() {
      this.metadata = new UserMetadata();
   }

   /**
    * Constructor
    * 
    * @param role
    *           user role
    */
   public UserDetails(UserRole.Role role) {

      UserRole aUserRole = new UserRole(role);
      getRoles().add(aUserRole);
   }

   public long getId() {

      return id;
   }

   public void setId(long id) {

      this.id = id;
   }

   public String getUsername() {

      return username;
   }

   public void setUsername(String username) {

      this.username = username;
   }

   public String getFname() {

      return fname;
   }

   public void setFname(String fname) {

      this.fname = fname;
   }

   public String getSname() {

      return sname;
   }

   public void setSname(String sname) {

      this.sname = sname;
   }

   public Date getBirthday() {

      return birthday;
   }

   public void setBirthday(Date birthday) {

      this.birthday = birthday;
   }

   public String getPassword() {

      return password;
   }

   public void setPassword(String password) {

      this.password = password;
   }

   public String getConfpassword() {

      return confpassword;
   }

   public void setConfpassword(String confpassword) {

      this.confpassword = confpassword;
   }

   public String getEmail() {

      return email;
   }

   public void setEmail(String email) {

      this.email = email;
   }

   public Set<UserRole> getRoles() {

      return roles;
   }

   public void setRoles(Set<UserRole> roles) {

      this.roles = roles;
   }

   public void addUserRole(UserRole role) {

      this.roles.add(role);
   }

   public BinaryResource getUserPic() {
      return userPic;
   }

   public void setUserPic(BinaryResource userPic) {
      this.userPic = userPic;
   }

   
   public UserMetadata getMetadata() {
   
      return metadata;
   }

   
   public void setMetadata(UserMetadata metadata) {
   
      this.metadata = metadata;
   }



}
