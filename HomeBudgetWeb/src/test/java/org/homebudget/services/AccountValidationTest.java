/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;

import java.util.Date;
import javax.annotation.Resource;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.homebudget.test.config.TestConfigurator;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 *
 * @author Michael Wolowyk
 */
public class AccountValidationTest extends TestConfigurator {

   @Resource
   AccountValidationService instance;

   @Resource
   AccountManagementService service;

   @Autowired
   UserRepository userRepository;

   @After
   public void tearDown() {
      service.deleteAll();
      userRepository.deleteAll();
   }

   /**
    * Test of validate method, of class RegistrationValidation.
    */
   @Test
   public void testValidateAccountWithDuplicateName() {

      System.out.println("testValidateAccountWithDuplicateName");

      Account account = new Account();
      String userName = "User";
      UserDetails user = createTestUser(userName);
      userRepository.save(user);
      account.setName("AccountName1");
      service.saveAccount(account, userName);

      Errors errors = new BeanPropertyBindingResult(account, "Account");
      instance.validate(account, errors);
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("name"));
   }

   @Test
   public void testValidateAccountCreationDate() {

      System.out.println("testValidateAccountCreationDate");

      Account account = new Account();
      String userName = "User";
      UserDetails user = createTestUser(userName);
      userRepository.save(user);
      account.setName("AccountName1");
      account.setOwner(user);
      Errors errors = new BeanPropertyBindingResult(account, "Account");
      instance.validate(account, errors);
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("dateOfCreation"));
   }

   @Test
   public void testValidateCorrectAccount() {

      System.out.println("testValidateCorrectAccount");

      Account account = new Account();
      String userName = "User";
      UserDetails user = createTestUser(userName);
      userRepository.save(user);
      account.setName("AccountName1");
      account.setDateOfCreation(new Date());
      account.setOwner(user);
      Errors errors = new BeanPropertyBindingResult(account, "Account");
      instance.validate(account, errors);
      assertFalse(errors.hasErrors());
   }

   private UserDetails createTestUser(String userName) {
      UserDetails user = new UserDetails();
      user.setUsername(userName);
      user.setEmail("user@email.com");
      return user;
   }

}