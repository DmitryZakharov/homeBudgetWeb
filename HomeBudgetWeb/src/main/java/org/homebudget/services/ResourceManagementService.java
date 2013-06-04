package org.homebudget.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import javax.persistence.EntityManagerFactory;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.homebudget.model.BinaryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResourceManagementService {

   private static final Logger logger = Logger.getLogger(ResourceManagementService.class);

   @Autowired
   private EntityManagerFactory entityFactory;

   public BinaryResource getResource(MultipartFile file) {
      BinaryResource document = new BinaryResource();
      Session session = entityFactory.createEntityManager().unwrap(Session.class);
      Blob blob = null;
      try {
         blob = Hibernate.getLobCreator(session).createBlob(file.getBytes());
      }
      catch (IOException ex) {
         logger.error("Document could not be created. " + ex.getLocalizedMessage());
      }
      document.setFilename(file.getOriginalFilename());
      document.setName(file.getName());
      document.setContent(blob);
      document.setContentType(file.getContentType());
      return document;
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

}
