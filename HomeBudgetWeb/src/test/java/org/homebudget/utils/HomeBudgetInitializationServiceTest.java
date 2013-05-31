/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.utils;

import java.util.List;
import javax.annotation.Resource;
import org.homebudget.model.Account;
import org.homebudget.model.Category;
import org.homebudget.model.Transaction;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.services.AccountManagementService;
import org.homebudget.services.HomeBudgetInitializationService;
import org.homebudget.services.UserManagementService;
import org.homebudget.test.config.TestConfigurator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
   HomeBudgetInitializationService aHomeBudgetInitializationService;

   @Before
   public void setUp(){
      aAccountManagementService.deleteAll();
      aUserManagementService.deleteAllUserDetails();
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
      System.out.println("testCreateTestAccount");
      int accountNr = 10;
      UserDetails aUserDetails = createTestUser(1);
      String username = aUserDetails.getUsername();
      createTestAccounts(accountNr, username);

      assertEquals(accountNr, aAccountManagementService.getAllUserAccounts(username).size());
      assertEquals(1, aAccountManagementService.getAllUserAccounts(username).get(0).getTransactions().size());
   }

   @Test
   public void testInitialization(){
      int numberOfUsers = 10;
      aHomeBudgetInitializationService.setUserNumber(numberOfUsers);
      aHomeBudgetInitializationService.executePopulation();
      List<UserDetails> users = aUserManagementService.getAllUsers();
      assertEquals(numberOfUsers, users.size());
      UserDetails user = users.get(0);
      List<Account> accounts = aAccountManagementService.getAllUserAccounts(user.getUsername());
      assertEquals(1, accounts.size());
      List<Transaction> transactions = (List<Transaction>) accounts.get(0).getTransactions();
      assertEquals(1, transactions.size());
   }
   
   private void createTestUsers(int usersNr) {
      for (int j = 0; j < usersNr; j++) {
         createTestUser(j);

      }
   }

   private UserDetails createTestUser(int j) {
      UserDetails aUserDetails = new UserDetails();
      aUserDetails.setEmail("test" + j + "@example.com");
      aUserDetails.setEnabled(1);
      aUserDetails.setUsername("user" + j);
      aUserDetails.setPassword("password");
      UserRole role = new UserRole(UserRole.Role.USER_ROLE);
      aUserDetails.addUserRole(role);
      aUserManagementService.saveUserDetails(aUserDetails);
      return aUserDetails;
   }

   private void createTestAccounts(int accountNr, String userName) {
      for (int i = 0; i < accountNr; i++) {
         Account aAccount = new Account();
         aAccount.setName("account" + i);
         aAccount.setStartingBalance(i);
         Transaction transaction = new Transaction();
         transaction.setAmount(i);
         transaction.setComment("transaction" + i);
         Category category = new Category();
         category.setName("category" + 1);
         transaction.setCategory(category);
         aAccount.addTransaction(transaction);
         aAccountManagementService.saveAccount(aAccount, userName);
      }

   }

}