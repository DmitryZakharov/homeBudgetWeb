/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;

import java.util.Date;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.homebudget.dao.AccountRepository;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.homebudget.test.config.TestConfigurator;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;
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
   AccountRepository repository;

   @Autowired
   UserManagementService userManagementService;

   private EntityManager entryManager;
   
   @Before
   public void init() {

      entryManager = getEntityManagerFactory().createEntityManager();
      
      TransactionSynchronizationManager.bindResource(getEntityManagerFactory(), new EntityManagerHolder(entryManager));

   }

   @After
   public void tearDown() {

      userManagementService.deleteAllUserDetails();
      EntityManagerHolder emHolder = (EntityManagerHolder)
            TransactionSynchronizationManager.unbindResource(getEntityManagerFactory());
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
      userManagementService.saveUserDetails(user);
      account.setName("AccountName1");
      account.setOwnerMetadata(user.getMetadata());
      accountManagementService.saveAccount(account, userName);

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