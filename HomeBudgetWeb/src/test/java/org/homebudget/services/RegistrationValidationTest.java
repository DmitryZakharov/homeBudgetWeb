/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.homebudget.model.UserDetails;
import org.homebudget.test.config.TestConfigurator;
import org.junit.After;
import org.junit.Test;
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

   @Resource
   UserManagementService service;

   @After
   public void tearDown() {

      service.deleteAllUserDetails();
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

      instance.validate(target, errors);
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("email"));
   }

   @Test
   public void testValidateEmailUniqueness() {

      System.out.println("testValidateEmailUniqueness");
      UserDetails target = new UserDetails();
      target.setEmail(VALID_EMAIL);
      service.saveUserDetails(target);
      Errors errors = new BeanPropertyBindingResult(target, "userDetails");

      instance.validate(target, errors);
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("email"));
      service.deleteUserDetails(target);
   }

   @Test
   public void testValidateUserNameUniqueness() {

      System.out.println("testValidateUserNameUniqueness");
      UserDetails target = new UserDetails();
      target.setUsername(VALID_USERNAME);
      target.setEmail(VALID_EMAIL);
      service.saveUserDetails(target);
      Errors errors = new BeanPropertyBindingResult(target, "userDetails");

      instance.validate(target, errors);
      assertTrue(errors.hasErrors());
      assertNotNull(errors.getFieldError("username"));
      service.deleteUserDetails(target);
   }

   @Test
   public void testValidatePassword() {

      System.out.println("testValidatePassword");
      UserDetails target = new UserDetails();
      target.setEmail(VALID_EMAIL);
      target.setPassword("passwordA");
      target.setConfpassword("passwordB");

      Errors errors = new BeanPropertyBindingResult(target, "userDetails");

      instance.validate(target, errors);
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

      instance.validate(target, errors);
      assertFalse(errors.hasErrors());

   }

}