package org.homebudget.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "CATEGORY")
public class Category {

   @Id
   @Column(name = "CATEGORY_ID")
   @GeneratedValue
   private int id;

   @Column(name = "CATEGORY_NAME")
   private String name;

   @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinColumn(name = "CATEGORY_PARENT")
   private Category parent;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "OWNER_METADATA")
   private UserMetadata ownerMetadata;

   public int getId() {

      return id;
   }

   public void setId(int id) {

      this.id = id;
   }

   public UserMetadata getOwnerMetadata() {

      return ownerMetadata;
   }

   public void setOwnerMetadata(UserMetadata ownerMetadata) {

      this.ownerMetadata = ownerMetadata;
   }

   public String getName() {

      return name;
   }

   public void setName(String name) {

      this.name = name;
   }

   public Category getParent() {

      return parent;
   }

   public void setParent(Category parent) {

      this.parent = parent;
   }

}
