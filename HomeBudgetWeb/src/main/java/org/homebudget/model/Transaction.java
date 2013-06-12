package org.homebudget.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DiscriminatorValue("TRANS")
public class Transaction extends TransactionAbstract {

   @Column(name = "EXECUTION_DATE")
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "dd/MM/yyyy")
   private Date executionDate;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "ATTACHMENT")
   private BinaryResource attachment;

 @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ACCOUNT_ID")
   private Account account;

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

  public Account getAccount() {
      return account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }




}
