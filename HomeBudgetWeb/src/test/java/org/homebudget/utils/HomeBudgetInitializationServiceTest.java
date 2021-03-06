/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.utils;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.BasicConfigurator;
import org.homebudget.config.TestConfigurator;
import org.homebudget.dao.UserRepository;
import org.homebudget.dao.UserRoleRepository;
import org.homebudget.model.Account;
import org.homebudget.model.Category;
import org.homebudget.model.Transaction;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.services.AccountManagementService;
import org.homebudget.services.CategoryManagementService;
import org.homebudget.services.HomeBudgetInitializationTextService;
import org.homebudget.services.UserManagementService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 
 * @author Michael Wolowyk
 */
public class HomeBudgetInitializationServiceTest extends TestConfigurator {

   @Resource
   UserManagementService aUserManagementService;

   @Resource
   AccountManagementService aAccountManagementService;

   @Resource
   HomeBudgetInitializationTextService aHomeBudgetInitializationService;

   @Resource
   CategoryManagementService categoryManagementService;

   @Resource
   UserRoleRepository userRoleRepository;

   @Autowired
   UserManagementService userManagementService;

   @Autowired
   EntityManagerFactory entityManagerFactory;

   @Autowired
   UserRepository repository;
   
   private  EntityManager entryManager ;

   @Before
   public void init() {
      
      BasicConfigurator.configure();

      aUserManagementService.getAllUsers();

      // aHomeBudgetInitializationService.initUserRoles();

      entryManager = entityManagerFactory.createEntityManager();

      TransactionSynchronizationManager.bindResource(entityManagerFactory, new EntityManagerHolder(
            entryManager));
      
   }

   @After
   public void tearDown() {
	   
     List<UserDetails> users = userManagementService.getAllUsers();
     
//     for (UserDetails user : users) {
//       System.out.println("accounts: " + user.getMetadata().getAccounts().size());
//        user.getMetadata().deleteAllAccounts();
//        userManagementService.saveUserDetails(user);
//        System.out.println("accounts: " + user.getMetadata().getAccounts().size());
//       Collection<Category> category =  user.getMetadata().getCategories();
//       user.getMetadata().removeCategory(category.iterator().next());
//        userManagementService.saveUserDetails(user);
//        
//   }
	  userManagementService.deleteAllUserDetails();
      EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager
            .unbindResource(entityManagerFactory);
      EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
      


   }

   /**
    * Test of executePopulation method, of class HomeBudgetInitializationService.
    */
   @Test
   public void testCreateTestUsers() {

      System.out.println("testCreateTestUsers");
      int usersNr = 10;
      createTestUsers(usersNr);
      assertEquals(usersNr, aUserManagementService.getAllUsers().size());
   }

   @Test
   public void testCreateTestAccounts() {


	 System.out.println("Size: " + userManagementService.getAllUsers().size());
      System.out.println("testCreateTestAccount");
      int accountNr = 10;
      UserDetails aUserDetails = createTestUser(1);
      String username = aUserDetails.getUsername();
      createTestAccounts(accountNr, username);

      assertEquals(accountNr, aAccountManagementService.getAllUserAccounts(username).size());
//      assertEquals(1, aAccountManagementService.getAllUserAccounts(username).get(0)
//            .getTransactions().size());
   }

   @Test
   public void testInitialization() {

      int numberOfUsers = 10;
      aHomeBudgetInitializationService.setUserNumber(numberOfUsers);
      aHomeBudgetInitializationService.executePopulation();
      List<UserDetails> users = aUserManagementService.getAllUsers();
      assertEquals(numberOfUsers, users.size());
      UserDetails user = users.get(0);
      Collection<Account> accounts = aAccountManagementService.getAllUserAccounts(user.getUsername());
      assertEquals(1, accounts.size());
//      Collection<Transaction> transactions = (List<Transaction>) accounts.get(0).getTransactions();
//      assertEquals(1, transactions.size());
   }

   private void createTestUsers(int usersNr) {

      for (int j = 0; j < usersNr; j++) {
         createTestUser(j);

      }
   }

   private UserDetails createTestUser(int j) {

      UserDetails aUserDetails = new UserDetails();
      aUserDetails.setEmail("test" + j + "@example.com");
      aUserDetails.getMetadata().setEnabled(1);
      aUserDetails.setUsername("user" + j);
      aUserDetails.setPassword("password");
      UserRole role = userRoleRepository.findByRole(UserRole.Role.USER_ROLE);
      aUserDetails.addUserRole(role);

      return aUserManagementService.saveUserDetails(aUserDetails);
   }

   private void createTestAccounts(int accountNr, String userName) {

       final UserDetails userDetails = aUserManagementService.getUserDetailsByUsername(userName);

      for (int i = 0; i < accountNr; i++) {
         Account aAccount = new Account();
         aAccount.setName("account" + i);
         aAccount.setStartingBalance(i);
         aAccount = aAccountManagementService.saveAccount(aAccount, userName);
         
         Transaction transaction = new Transaction();
         transaction.setAmount(i);
         transaction.setComment("transaction" + i);
         final  Category category = categoryManagementService.createCategory("category" + i, null,
                 userName);
         
         transaction.setCategory(category);
         aAccount.addTransaction(transaction);

         userDetails.getMetadata().addAccount(aAccount);
         userDetails.getMetadata().addCategory(category);
         userManagementService.saveUserDetails(userDetails);
      }

   }

}