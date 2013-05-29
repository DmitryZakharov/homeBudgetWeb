package org.homebudget.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Transaction {

   public static enum TransactionType {

      INCOME, OUTCOME

   };

   @Column(name = "EXECUTION_DATE")
   @Temporal(TemporalType.DATE)
   private Date executionDate;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "CATEGORY_ID")
   private Category category;

   @Column(name = "AMOUNT")
   private float amount;

   @Enumerated(EnumType.STRING)
   @Column(name = "TRANSACTION_TYPE")
   private TransactionType type;

   @Column(name = "COMMENT")
   private String comment;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "RESOURCE_ID")
   private BinaryResource attachedImage;

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

   public BinaryResource getAttachedImage() {

      return attachedImage;
   }

   public void setAttachedImage(BinaryResource attachedImage) {

      this.attachedImage = attachedImage;
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

}
