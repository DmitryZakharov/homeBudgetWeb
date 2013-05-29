/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;

import java.io.IOException;
import java.sql.SQLException;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.BinaryResource;
import org.homebudget.model.Document;
import org.homebudget.model.UserDetails;
import org.homebudget.test.config.TestConfigurator;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class UserManagementServiceTest extends TestConfigurator {

   public static final String EMAIL = "test@test.com";

   public static final String FNAME = "testUser";

   public static final String USER_NAME = "testUser";

   @Autowired
   UserManagementService service;

   @Autowired
   UserRepository repository;
   
   @Autowired
   ApplicationContext applicationContext;
   
   @Autowired
   DocumentManagementService documentManagementService;

   @After
   public void tearDown(){
      service.deleteAllUserDetails();
   }
   
   /**
    * Test of saveUserDetails method, of class UserManagementService.
    */
   @Test
   public void testSaveUserDetails() {

      System.out.println("saveUserDetails");
      UserDetails userDetails = createTestUser();

      service.saveUserDetails(userDetails);
      UserDetails result = repository.findByUsername(userDetails.getUsername());
      assertNotNull(result);
      assertEquals(userDetails.getEmail(), result.getEmail());
      assertEquals(userDetails.getFname(), result.getFname());

   }
   
   @Test
   public void testSaveUserDetailsWithImage() throws IOException, SQLException {

      System.out.println("testSaveUserDetailsWithImage");
      UserDetails userDetails = createTestUser();
      
      Document document = createTestDocument();
      userDetails.setImage(document);
      service.saveUserDetails(userDetails);
      UserDetails result = repository.findByUsername(userDetails.getUsername());
      assertNotNull(result);
      assertNotNull(result.getImage());

   }

   private UserDetails createTestUser() {
      UserDetails userDetails = new UserDetails();
      userDetails.setEmail(EMAIL);
      userDetails.setFname(FNAME);
      userDetails.setUsername(USER_NAME);
      return userDetails;
   }

   private Document createTestDocument() throws BeansException, IOException {
      MultipartFile multipartFile = mock(MultipartFile.class);
      ResourceLoaderService resourceLoader = (ResourceLoaderService) applicationContext
           .getBean("resourceLoaderService");
      Resource resource = resourceLoader.getResource("classpath:docs/foto2.jpg");
      BinaryResource binaryResource = new BinaryResource(resource);
      when(multipartFile.getName()).thenReturn(resource.getFilename());
      when(multipartFile.getOriginalFilename()).thenReturn(resource.getFile().getPath());
      when(multipartFile.getInputStream()).thenReturn(resource.getInputStream());
      when(multipartFile.getBytes()).thenReturn(binaryResource.getResource());
      Document document = documentManagementService.getDocument(multipartFile);
      return document;
   }

}