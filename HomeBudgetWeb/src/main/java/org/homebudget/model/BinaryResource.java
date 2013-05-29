package org.homebudget.model;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

@Entity(name = "BINARY_RESOURCE")
public class BinaryResource implements java.io.Serializable {
   
   private static final Logger gLogger = Logger.getLogger(BinaryResource.class);

   private static final long serialVersionUID = 1172145991794242627L;

   @Id
   @Column(name = "RESOURCE_ID")
   @GeneratedValue
   private Long id;

   @Lob
   @Column(name = "RESOURCE")
   private byte[] resource;

   public BinaryResource() {

   }

   public BinaryResource(Resource resource) {

      this.resource = getBytesFromResource(resource);
   }

   
   public Long getId() {
   
      return id;
   }

   
   public void setId(Long id) {
   
      this.id = id;
   }

   public byte[] getResource() {

      return resource;
   }

   public void setResource(byte[] resource) {

      this.resource = resource;
   }

   private byte[] getBytesFromResource(Resource resource) {

      byte[] result = {};
      InputStream inputStream = null;
      try {
         inputStream = resource.getInputStream();
         result = IOUtils.toByteArray(inputStream);
      }
      catch (Exception ex) {
         gLogger.error("Failed to get byte array from resource", ex);
      }
      finally {
         try {
            if (inputStream != null) {
               inputStream.close();
            }
         }
         catch (IOException ex) {
            gLogger.warn("Failed to close input stream", ex);
         }
      }
      return result;
   }

}