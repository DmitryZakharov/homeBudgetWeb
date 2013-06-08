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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Immutable;

@Entity(name = "USER_ROLE")
@Immutable
@Table(name="USER_ROLE", uniqueConstraints={@UniqueConstraint(columnNames = "USER_ROLE_TYPE")})
public class UserRole {

   public enum Role {

      ADMIN_ROLE("ROLE_ADMIN", 3), MANAGER_ROLE("ROLE_MANAGER", 2), USER_ROLE("ROLE_USER", 1), ANONYMOUS_ROLE(
            "ROLE_ANONYMOUS", 0);

      private final int order;

      private final String name;

      private Role(final String name, final int order) {

         this.name = name;
         this.order = order;
      }

      public int order() {

         return order;
      }

      public String getName() {

         return name;
      }

   };

   @Id
   @GeneratedValue
   @Column(name = "USER_ROLE_ID")
   private long id;

   @Enumerated(EnumType.STRING)
   @Column(name = "USER_ROLE_TYPE", unique = true, nullable = false)
   private Role role;

   public UserRole() {

   }

   public UserRole(UserRole.Role role) {

      setRole(role);
   }

   public long getId() {

      return id;
   }

   public void setId(long id) {

      this.id = id;
   }

   public Role getRole() {

      return role;
   }

   public void setRole(Role role) {

      this.role = role;
   }

}
