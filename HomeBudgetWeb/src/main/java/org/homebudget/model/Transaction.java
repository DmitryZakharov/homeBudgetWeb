package org.homebudget.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "TRANSACTION")
public class Transaction {

   @Id
   @GeneratedValue
   @Column(name = "TRANSACTION_ID")
   private Long id;

   public static enum TransactionType {

      INCOME, OUTCOME

   };

   @Column(name = "EXECUTION_DATE")
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "MM/dd/yyyy")
   private Date executionDate;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "CATEGORY")
   private Category category;

   @Column(name = "AMOUNT")
   private float amount;

   @Enumerated(EnumType.STRING)
   @Column(name = "TRANSACTION_TYPE")
   private TransactionType type;

   @Column(name = "COMMENT")
   private String comment;

   @JoinColumn(name = "ATTACHMENT")
   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private BinaryResource attachment;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ACCOUNT_ID", nullable = false)
   private Account parent;

   public Date getExecutionDate() {

      return executionDate;
   }

   public void setExecutionDate(Date executionDate) {

      this.executionDate = executionDate;
   }

   public TransactionType getType() {

      return type;
   }

   public void setType(TransactionType type) {

      this.type = type;
   }

   public BinaryResource getAttachment() {

      return attachment;
   }

   public void setAttachment(BinaryResource attachment) {

      this.attachment = attachment;
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

   public Account getParent() {

      return parent;
   }

   public void setParent(Account parent) {

      this.parent = parent;
   }

}
