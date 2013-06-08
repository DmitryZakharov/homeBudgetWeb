/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.homebudget.config.TestConfigurator;
import org.homebudget.dao.AccountRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.services.UserManagementService;
import org.homebudget.services.UserValidationService;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
public class RegistrationValidationTest extends TestConfigurator {

   public static final String INVALID_EMAIL = "blub.blub";

   public static final String VALID_EMAIL = "blub@blub.com";

   public static final String VALID_USERNAME = "JohnDoe";

   @Resource
   UserValidationService instance;

   @Autowired
   UserManagementService userManagementService;
   @Autowired
   EntityManagerFactory entityManagerFactory;
   
   @Autowired
   AccountRepository repository;
   
   @Before
   public void init() {
      
      repository.deleteAll();

      EntityManager  entryManager = entityManagerFactory.createEntityManager();
      
      TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(entryManager));

   }

   @After
   public void tearDown() {

      userManagementService.deleteAllUserDetails();
      EntityManagerHolder emHolder = (EntityManagerHolder)
            TransactionSynchronizationManager.unbindResource(entityManagerFactory);
      EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
      
   }

   /**
    * Test of validate method, of class RegistrationValidation.
    */
   @Test
   public void testValidateEmailFormat() {

      System.out.println("testValidateEmailFormat");
      UserDetails target = new UserDetails();
      target.setEmail(INVALID_EMAIL);
      Errors errors = new BeanPropertyBindingResult(target, "userDetails");

      instance.validate(target, errors, null);
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("email"));
   }

   @Test
   public void testValidateEmailUniqueness() {

      System.out.println("testValidateEmailUniqueness");
      UserDetails target = new UserDetails();
      target.setEmail(VALID_EMAIL);
      userManagementService.saveUserDetails(target);
      Errors errors = new BeanPropertyBindingResult(target, "userDetails");

      instance.validate(target, errors, null);
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("email"));
      userManagementService.deleteUserDetails(target);
   }

   @Test
   public void testValidateUserNameUniqueness() {

      System.out.println("testValidateUserNameUniqueness");
      UserDetails target = new UserDetails();
      target.setUsername(VALID_USERNAME);
      target.setEmail(VALID_EMAIL);
      target = userManagementService.saveUserDetails(target);
      Errors errors = new BeanPropertyBindingResult(target, "userDetails");

      instance.validate(target, errors,target.getUsername());
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("username"));
      userManagementService.deleteUserDetails(target);
   }

   @Test
   public void testValidatePassword() {

      System.out.println("testValidatePassword");
      UserDetails target = new UserDetails();
      target.setEmail(VALID_EMAIL);
      target.setPassword("passwordA");
      target.setConfpassword("passwordB");

      Errors errors = new BeanPropertyBindingResult(target, "userDetails");

      instance.validate(target, errors, target.getUsername());
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("password"));
   }

   @Test
   public void testValidUser() {

      System.out.println("testValidUser");
      UserDetails target = new UserDetails();
      target.setEmail(VALID_EMAIL);
      target.setUsername(VALID_USERNAME);
      target.setPassword("password");
      target.setConfpassword("password");

      Errors errors = new BeanPropertyBindingResult(target, "userDetails");

      instance.validate(target, errors,target.getUsername());
      assertFalse(errors.hasErrors());

   }

}