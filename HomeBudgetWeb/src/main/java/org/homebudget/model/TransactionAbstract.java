package org.homebudget.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name = "TRANSACTION_CLASS")
@Table(name = "TRANSACTIONS")
public class TransactionAbstract {

   @Id
   @GeneratedValue
   @Column(name = "TRANSACTION_TEMPLATE_ID")
   private Long id;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "CATEGORY")
   protected Category category;

   @Column(name = "AMOUNT")
   protected float amount;

   @Enumerated(EnumType.STRING)
   @Column(name = "TRANSACTION_TYPE")
   protected Transaction.TransactionType type;

   @Column(name = "COMMENT")
   protected String comment;

  

   public Transaction.TransactionType getType() {
      return type;
   }

   public void setType(Transaction.TransactionType type) {

      this.type = type;
   }

   public float getAmount() {

      return amount;
   }

   public void setAmount(float amount) {

      this.amount = amount;
   }

   public Category getCategory() {

      return category;
   }

   public void setCategory(Category category) {

      this.category = category;
   }

   public String getComment() {

      return comment;
   }

   public void setComment(String comment) {

      this.comment = comment;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   
   public static enum TransactionType {

      INCOME, OUTCOME

   };

}
