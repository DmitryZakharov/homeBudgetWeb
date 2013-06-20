package org.homebudget.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.persistence.EntityManagerFactory;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.homebudget.model.BinaryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


@Service
public class ResourceManagementService implements ResourceLoaderAware {

   private static final Logger logger = Logger.getLogger(ResourceManagementService.class);

   private ResourceLoader resourceLoader;

   @Autowired
   private EntityManagerFactory entityFactory;

   public BinaryResource getResource(MultipartFile file) {
      BinaryResource resource = new BinaryResource();
      Session session = entityFactory.createEntityManager().unwrap(Session.class);
      Blob blob = null;
      try {
         blob = Hibernate.getLobCreator(session).createBlob(file.getBytes());
      }
      catch (IOException ex) {
         logger.error("Document could not be created. " + ex.getLocalizedMessage());
      }
      resource.setFilename(file.getOriginalFilename());
      resource.setName(file.getName());
      resource.setContent(blob);
      resource.setContentType(file.getContentType());
      return resource;
   }

   public byte[] getBytesFromResource(Resource resource) {

      byte[] result = {};
      InputStream inputStream = null;
      try {
         inputStream = resource.getInputStream();
         result = IOUtils.toByteArray(inputStream);
      }
      catch (Exception ex) {
         logger.error("Failed to get byte array from resource", ex);
      }
      finally {
         try {
            if (inputStream != null) {
               inputStream.close();
            }
         }
         catch (IOException ex) {
            logger.warn("Failed to close input stream", ex);
         }
      }
      return result;
   }

   /**
    * Decode string to image
    *
    * @param imageString The string to decode
    * @return decoded image
    */
   private byte[] decodeToImage(InputStream inputStream) {

      byte[] imageByte = null;
      try {
         BASE64Decoder decoder = new BASE64Decoder();
         imageByte = decoder.decodeBuffer(inputStream);
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      return imageByte;
   }

   /**
    * Encode image to string
    *
    * @param image The image to encode
    * @param type jpeg, bmp, ...
    * @return encoded string
    */
   private String encodeToString(byte[] imageBytes, String type) {
      String imageString = null;
      BASE64Encoder encoder = new BASE64Encoder();
      imageString = encoder.encode(imageBytes);

      return imageString;
   }

   public String getBase64ImageString(BinaryResource resource){
      String base64String = null;
      try {
         
         Blob content = resource.getContent();
         int contentLength = (int) content.length();
         String contentType = resource.getContentType();
         base64String = "data:" + contentType + ";base64," + encodeToString(content.getBytes(1, contentLength), resource.getContentType());
         
      }
      catch (SQLException ex) {
        logger.error("Exception by downloading of file: " + ex);
      }
      return base64String;

   }

   @Override
   public void setResourceLoader(ResourceLoader resourceLoader) {

      this.resourceLoader = resourceLoader;
   }

   public Resource getResource(String location) {

      return resourceLoader.getResource(location);
   }

}
