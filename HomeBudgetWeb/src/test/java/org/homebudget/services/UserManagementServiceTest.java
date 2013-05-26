/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.homebudget.dao.UserRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.test.config.TestConfigurator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserManagementServiceTest extends TestConfigurator {

   @Autowired
   UserManagementService service;

   @Autowired
   UserRepository repository;

   /**
    * Test of saveUserDetails method, of class UserManagementService.
    */
   @Test
   public void testSaveUserDetails() {

      System.out.println("saveUserDetails");
      UserDetails userDetails = new UserDetails();
      userDetails.setEmail("test@test.com");
      userDetails.setUserName("testUser");
      userDetails.setUserUsername("testUser");

      service.saveUserDetails(userDetails);
      UserDetails result = repository.findByUserUsername(userDetails.getUserUsername());
      assertNotNull(result);
      assertEquals(userDetails.getEmail(), result.getEmail());
      assertEquals(userDetails.getUserName(), result.getUserName());

   }

}