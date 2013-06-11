/**
 * 
 */
package org.homebudget.services.utils;

import java.util.Date;
import org.apache.log4j.Logger;
import org.homebudget.dao.UserRoleRepository;
import org.homebudget.model.Account;
import org.homebudget.model.Category;
import org.homebudget.model.Currency;
import org.homebudget.model.Transaction;
import org.homebudget.model.TransactionTemplate.TransactionType;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.model.UserRole.Role;
import org.homebudget.services.AccountManagementService;
import org.homebudget.services.CategoryManagementService;
import org.homebudget.services.PasswordService;
import org.homebudget.services.TransactionManagementService;
import org.homebudget.services.UserManagementService;

/**
 * @author dza
 * 
 */
public class DatabasePopulator {

   private static final Logger logger = Logger.getLogger(DatabasePopulator.class);

   private final UserManagementService userManagementService;

   private final AccountManagementService accountManagementService;

   private final UserRoleRepository userRoleRepository;

   private final TransactionManagementService transactionManagementService;

   private final CategoryManagementService categoryManagementService;

   /**
    * 
    */
   public DatabasePopulator(UserManagementService userManagementService,
         AccountManagementService accountManagementService, UserRoleRepository userRoleRepository,
         TransactionManagementService transactionManagementService,
         CategoryManagementService categoryManagementService) {

      this.accountManagementService = accountManagementService;
      this.userManagementService = userManagementService;
      this.userRoleRepository = userRoleRepository;
      this.transactionManagementService = transactionManagementService;
      this.categoryManagementService = categoryManagementService;
   }

   public void populateUsers(int number) {

      logger.info("initialize database with " + number + " users");

      UserRole uRole = userRoleRepository.findByRole(UserRole.Role.USER_ROLE);
      for (int i = 0; i < number; i++) {
         String name = "";
         if (i == 0) {
            name = "Michael";
         }
         else {
            name = "Dmitry";
         }
         UserDetails user = createTestUser(i, name);
         logger.info("Creating user: " + user.getFname());
         user.addUserRole(uRole);
         user = userManagementService.saveUserDetails(user);

         Account account = createTestAccount(user);
         logger.info("Creating account: " + account.getName());

         Category category = createTestCategory(user, i);
         logger.info("Creating category: " + category.getName());

         Transaction transaction = createTestTransaction(category);
         logger.info("Creating transaction: " + transaction.getAmount());

         account.addTransaction(transaction);

         user.getMetadata().addAccount(account);
         userManagementService.saveUserDetails(user);
         // transactionManagementService.saveTransaction(transaction, account.getName());
      }
   }

   public void initUserRoles() {

      for (Role role : Role.values()) {
         UserRole uRole = new UserRole();
         uRole.setRole(role);
         logger.info("Creating role: " + uRole.getRole());
         userManagementService.saveUserRole(uRole);
      }
   }

   public UserDetails createTestUser(int i, String fName) {

      final UserDetails user = new UserDetails();
      user.setFname(fName + i);

      user.setUsername(i + "_nick");
      user.setFname("Doe");
      user.setEmail("some" + i + "@email.com");
      Date birthday = new Date();
      user.setBirthday(birthday);
      user.getMetadata().setEnabled(1);
      try {
         String password = "000" + i;
         if (i == 0) {
            user.setFname(fName);
            user.setUsername("admin");
            password = "admin";
         }
         user.setPassword(PasswordService.getHash(password));
      }
      catch (Exception e) {
         e.printStackTrace();
      }

      return user;
   }

   public Transaction createTestTransaction(final Category category) {

      final Transaction transaction = new Transaction();
      transaction.setAmount(10);
      transaction.setComment("comment");
      transaction.setCategory(category);
      transaction.setExecutionDate(new Date());
      transaction.setType(TransactionType.INCOME);

      return transaction;
   }

   public Account createTestAccount(UserDetails user) {

      final Account account = new Account();
      account.setName("account for user " + user.getUsername());
      account.setDateOfCreation(new Date());
      account.setOwnerMetadata(user.getMetadata());
      account.setStartingBalance(0);
      account.setCurrency(Currency.RUB);
      return account;
   }

   public Category createTestCategory(UserDetails user, long index) {

      final Category category = categoryManagementService.createCategory(
            "work".concat(Long.toString(index)), null, user.getUsername());
      return category;
   }

}
