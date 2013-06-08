/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.homebudget.config.TestConfigurator;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.homebudget.services.AccountManagementService;
import org.homebudget.services.AccountValidationService;
import org.homebudget.services.UserManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * 
 * @author Michael Wolowyk
 */
public class AccountValidationTest extends TestConfigurator {

   @Resource
   AccountValidationService accountValidationService;

   @Resource
   AccountManagementService accountManagementService;

   @Autowired
   UserManagementService userManagementService;

   @Autowired
   EntityManagerFactory entityManagerFactory;

   @Before
   public void init() {

      accountManagementService.deleteAll();

      EntityManager entryManager = entityManagerFactory.createEntityManager();

      TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(
            entryManager));

   }

   @After
   public void tearDown() {

      userManagementService.deleteAllUserDetails();
      EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager
            .unbindResource(entityManagerFactory);
      EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());

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
      account.setOwnerMetadata(user.getMetadata());
      account.setName("AccountName1");
      user.getMetadata().addAccount(account);
      userManagementService.saveUserDetails(user);

      // account = accountManagementService.saveAccount(account, userName);

      Errors errors = new BeanPropertyBindingResult(account, "Account");
      accountValidationService.validate(account, errors, userName);
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("name"));
   }

   @Test
   public void testValidateAccountCreationDate() {

      System.out.println("testValidateAccountCreationDate");

      Account account = new Account();
      String userName = "User";
      UserDetails user = createTestUser(userName);
      userManagementService.saveUserDetails(user);
      account.setName("AccountName1");
      account.setOwnerMetadata(user.getMetadata());
      Errors errors = new BeanPropertyBindingResult(account, "Account");
      accountValidationService.validate(account, errors, user.getUsername());
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("dateOfCreation"));
   }

   @Test
   public void testValidateCorrectAccount() {

      System.out.println("testValidateCorrectAccount");

      Account account = new Account();
      String userName = "User";
      UserDetails user = createTestUser(userName);
      userManagementService.saveUserDetails(user);
      account.setName("AccountName1");
      account.setDateOfCreation(new Date());
      account.setOwnerMetadata(user.getMetadata());
      Errors errors = new BeanPropertyBindingResult(account, "Account");
      accountValidationService.validate(account, errors, userName);
      assertFalse(errors.hasErrors());
   }

   private UserDetails createTestUser(String userName) {

      UserDetails user = new UserDetails();
      user.setUsername(userName);
      user.setEmail("user@email.com");
      return user;
   }

}