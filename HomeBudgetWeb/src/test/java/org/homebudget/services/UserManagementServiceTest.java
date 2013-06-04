/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;

import java.io.IOException;
import java.sql.SQLException;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.BinaryResource;
import org.homebudget.model.UserDetails;
import org.homebudget.test.config.TestConfigurator;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserManagementServiceTest extends TestConfigurator {

   public static final String EMAIL = "test@test.com";

   public static final String FNAME = "testUser";

   public static final String USER_NAME = "testUser";

   @Autowired
   UserManagementService service;

   @Autowired
   UserRepository repository;
   


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
      
      BinaryResource document = createTestDocument();
      userDetails.setUserPic(document);
      service.saveUserDetails(userDetails);
      UserDetails result = repository.findByUsername(userDetails.getUsername());
      assertNotNull(result);
      assertNotNull(result.getUserPic());

   }

   private UserDetails createTestUser() {
      UserDetails userDetails = new UserDetails();
      userDetails.setEmail(EMAIL);
      userDetails.setFname(FNAME);
      userDetails.setUsername(USER_NAME);
      return userDetails;
   }



}