package org.homebudget.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("TEMPL")
public class TransactionTemplate extends TransactionAbstract {

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "OWNER_METADATA")
   private UserMetadata ownerMetadata;

   public UserMetadata getOwnerMetadata() {
      return ownerMetadata;
   }

   public void setOwnerMetadata(UserMetadata ownerMetadata) {
      this.ownerMetadata = ownerMetadata;
   }


}