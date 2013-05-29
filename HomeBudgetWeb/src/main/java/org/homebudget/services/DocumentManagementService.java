package org.homebudget.services;

import java.io.IOException;
import java.sql.Blob;
import javax.persistence.EntityManagerFactory;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.homebudget.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentManagementService {

   private static final Logger logger = Logger.getLogger(DocumentManagementService.class);

   @Autowired
   private EntityManagerFactory entityFactory;

   public Document getDocument(MultipartFile file) {
      Document document = new Document();
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

   
}
