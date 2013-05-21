/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;


import java.util.List;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.test.config.TestConfigurator;
import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

public class UserManagementServiceTest extends TestConfigurator{
    
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
        List<UserDetails> result = repository.findByUserUsername(userDetails.getUserUsername());
        assertEquals(1, result.size());
        assertEquals(userDetails.getEmail(), result.get(0).getEmail());
        assertEquals(userDetails.getUserName(), result.get(0).getUserName());
        
    }
    
    

   
}