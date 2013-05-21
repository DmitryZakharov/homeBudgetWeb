/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;


import java.util.Date;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.UserDetails;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:/config/homebudget-servlet.xml",
    "classpath:/config/datasource-config.xml",
    "classpath:/config/persistence-config.xml",
    "classpath:/config/homebudget-mail.xml"
    })
public class UserManagementServiceTest {
    
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
        assertEquals(userDetails.getEmail(), result.getEmail());
        assertEquals(userDetails.getUserName(), result.getUserName());
        
    }

   
}