package org.homebudget.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Entity(name = "ACCOUNT")
public class Account {

   private static final Logger logger = Logger.getLogger(Account.class);

   @Id
   @Column(name = "ACCOUNT_ID")
   @GeneratedValue
   private long id;

   // @NotBlank (message = "{account.name.required}")
   @Column(name = "ACCOUNT_NAME")
   private String name;

   @Column(name = "CREATION_DATE")
   @Temporal(javax.persistence.TemporalType.DATE)
   private Date dateOfCreation;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "OWNER_METADATA")
   private UserMetadata ownerMetadata;

   // @NotBlank (message = "{account.startingbalance.required}")
   @Column(name = "STARTING_BALANCE")
   private float startingBalance;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account")
   private Collection<Transaction> transactions = new ArrayList<Transaction>();

   @Enumerated(EnumType.STRING)
   @Column(name = "CURRENCY")
   private Currency currency;

   public long getId() {

      return id;
   }

   public String getName() {

      return name;
   }

   public void setId(long id) {

      this.id = id;
   }

   public void setName(String name) {

      this.name = name;
   }

   public UserMetadata getOwnerMetadata() {

      return ownerMetadata;
   }

   public void setOwnerMetadata(UserMetadata ownerMetadata) {

      this.ownerMetadata = ownerMetadata;
   }

   public float getStartingBalance() {

      return startingBalance;
   }

   public void setStartingBalance(float startingBalance) {

      this.startingBalance = startingBalance;
   }

   public Date getDateOfCreation() {

      return dateOfCreation;
   }

   public void setDateOfCreation(Date dateOfCreation) {

      this.dateOfCreation = dateOfCreation;
   }

   @Transactional
   public Collection<Transaction> getTransactions() {

      return transactions;
   }

   public void setTransactions(Collection<Transaction> transactions) {

      this.transactions = transactions;
   }

   public void addTransaction(Transaction transaction) {

      transaction.setAccount(this);
      this.transactions.add(transaction);
   }

   public Currency getCurrency() {

      return currency;
   }

   public void setCurrency(Currency currency) {

      this.currency = currency;
   }

   public boolean hasTransaction(Long transactionId) {

      for (Transaction transaction : transactions) {
         if (transaction.getId() == transactionId) {
            return true;
         }
      }
      logger.warn("Account " + name + " does not contain transaction with id " + transactionId);
      return false;
   }

}
