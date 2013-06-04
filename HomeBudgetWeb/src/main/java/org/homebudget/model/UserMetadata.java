/**
 * 
 */
package org.homebudget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author dza
 * 
 */
@Entity(name = "METADATA")
public class UserMetadata {

   @Id
   @Column(name = "METADATA_ID")
   @GeneratedValue
   private long id;

   @Column(name = "ENABLED")
   private int enabled;

   @Enumerated(EnumType.STRING)
   @Column(name = "MAIN_CURRENCY")
   private Currency currency;

   public long getId() {

      return id;
   }

   public void setId(long id) {

      this.id = id;
   }

   public int getEnabled() {

      return enabled;
   }

   public void setEnabled(int enabled) {

      this.enabled = enabled;
   }

   public Currency getCurrency() {

      return currency;
   }

   public void setCurrency(Currency currency) {

      this.currency = currency;
   }

}
