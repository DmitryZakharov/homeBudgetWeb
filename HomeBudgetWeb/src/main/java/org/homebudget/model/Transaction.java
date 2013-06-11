package org.homebudget.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "TRANSACTION")
public class Transaction extends TransactionTemplate implements Serializable {

   private static final long serialVersionUID = -5210927811826634768L;


   @Column(name = "EXECUTION_DATE")
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private Date executionDate;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "ATTACHMENT")
   private BinaryResource attachment;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ACCOUNT_ID")
   private Account parent;

   public Date getExecutionDate() {
      return executionDate;
   }

   public void setExecutionDate(Date executionDate) {

      this.executionDate = executionDate;
   }

   public BinaryResource getAttachment() {

      return attachment;
   }

   public void setAttachment(BinaryResource attachment) {

      this.attachment = attachment;
   }



   public Account getParent() {

      return parent;
   }

   public void setParent(Account parent) {

      this.parent = parent;
   }

  

}
