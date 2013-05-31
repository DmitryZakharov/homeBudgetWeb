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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity(name = "ACCOUNT")
public class Account {

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

   @OneToOne
   @JoinColumn(name = "USER_ID")
   private UserDetails owner;

   // @NotBlank (message = "{account.startingbalance.required}")
   @Column(name = "STARTING_BALANCE")
   private float startingBalance;

   @OneToMany(
       mappedBy = "parent",
       fetch = FetchType.EAGER,
       cascade = CascadeType.ALL)
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

   public UserDetails getOwner() {

      return owner;
   }

   public void setOwner(UserDetails owner) {

      this.owner = owner;
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

   public Collection<Transaction> getTransactions() {

      return transactions;
   }

   public void setTransactions(Collection<Transaction> transactions) {

      this.transactions = transactions;
   }

   public void addTransaction(Transaction transaction) {
      transaction.setParent(this);
      this.transactions.add(transaction);
   }

   public Currency getCurrency() {

      return currency;
   }

   public void setCurrency(Currency currency) {

      this.currency = currency;
   }

}
