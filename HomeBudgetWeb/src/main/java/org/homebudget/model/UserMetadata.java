/**
 *
 */
package org.homebudget.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy =
       "ownerMetadata")
   private List<Category> categories = new ArrayList<Category>();

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy =
       "ownerMetadata")
   private List<Account> accounts = new ArrayList<Account>();

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy =
       "ownerMetadata")
   private List<TransactionTemplate> transactionTemplates = new ArrayList<TransactionTemplate>();

   @OneToOne
   @JoinColumn(name = "USER_ID", nullable = false)
   private UserDetails userDetails;

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

   public List<Category> getCategories() {

      return Collections.unmodifiableList(categories);
   }

   public void addCategory(Category category) {

      if (category == null) {
         throw new IllegalArgumentException("Null category!");
      }
      if (categories.contains(category)) {
         categories.remove(category);
      }
      category.setOwnerMetadata(this);
      categories.add(category);
   }

   public void removeCategory(Category category) {

      if (category == null) {
         throw new IllegalArgumentException("Null category!");
      }
      if (categories.contains(category)) {
         categories.remove(category);
      }
   }

   public List<Account> getAccounts() {

      return Collections.unmodifiableList(accounts);
   }

   public void addAccount(Account account) {

      if (account == null) {
         throw new IllegalArgumentException("Null account!");
      }
      if (accounts.contains(account)) {
         accounts.remove(account);
      }
      account.setOwnerMetadata(this);
      accounts.add(account);
   }

   public void removeAccount(Account account) {

      if (account == null) {
         throw new IllegalArgumentException("Null account!");
      }
      if (accounts.contains(account)) {
         accounts.remove(account);
      }
   }

   public UserDetails getUserDetails() {

      return userDetails;
   }

   public void setUserDetails(UserDetails userDetails) {

      this.userDetails = userDetails;
   }

   public List<TransactionTemplate> getTransactionTemplates() {
      return transactionTemplates;
   }

   public void setTransactionTemplates(
       List<TransactionTemplate> transactionTemplates) {
      this.transactionTemplates = transactionTemplates;
   }
   
   

}
