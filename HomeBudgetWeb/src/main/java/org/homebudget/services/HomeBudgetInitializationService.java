package org.homebudget.services;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.homebudget.dao.UserRoleRepository;
import org.homebudget.model.Account;
import org.homebudget.model.Category;
import org.homebudget.model.Currency;
import org.homebudget.model.Transaction;
import org.homebudget.model.Transaction.TransactionType;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.model.UserRole.Role;
import org.springframework.stereotype.Service;

@Service
public class HomeBudgetInitializationService {

   private static final Logger logger = Logger.getLogger(HomeBudgetInitializationService.class);

   @Resource
   UserManagementService userManagementService;

   @Resource
   AccountManagementService accountManagementService;

   @Resource
   UserRoleRepository userRoleRepository;

   @Resource
   private TransactionManagementService transactionManagementService;

   @Resource
   private CategoryManagementService categoryManagementService;

   private boolean executed = false;

   private int userNumber = 10;

   @PostConstruct
   public void executePopulation() {

      logger.info("Create initial Population !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      int userCount = 0;
      if (userRoleRepository != null) {
         userCount = userRoleRepository.findAll().size();
         logger.info("User Count: " + userCount);
      }
      if (!executed) {
         populateUsers(userNumber);
         executed = true;
      }
      else {
         logger.info("ALREADY CREATED MANY USERS!!!!!!!!!!!");
      }
   }

   private void populateUsers(int number) {

      logger.info("initialize database with " + number + " users");
      initUserRoles();

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

         // save user so it can queried by other services
         user.getRoles().add(uRole);
         userManagementService.saveUserDetails(user);

         Account account = createTestAccount(user);
         logger.info("Creating account: " + account.getName());

         Category category = createTestCategory(user, i);
         logger.info("Creating category: " + category.getName());

         Transaction transaction = createTestTransaction(category);
         logger.info("Creating transaction: " + transaction.getAmount());

         account.addTransaction(transaction);

         accountManagementService.saveAccount(account, user.getUsername());
         // transactionManagementService.saveTransaction(transaction, account.getName());
      }
   }

   private void initUserRoles() {

      for (Role role : Role.values()) {
         UserRole uRole = new UserRole();
         uRole.setRole(role);
         logger.info("Creating role: " + uRole.getRole());
         userManagementService.saveUserRole(uRole);
      }
   }

   private UserDetails createTestUser(int i, String fName) {

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

   private Transaction createTestTransaction(final Category category) {

      final Transaction transaction = new Transaction();
      transaction.setAmount(10);
      transaction.setComment("comment");
      transaction.setCategory(category);
      transaction.setExecutionDate(new Date());
      transaction.setType(TransactionType.INCOME);

      return transaction;
   }

   private Account createTestAccount(UserDetails user) {

      final Account account = new Account();
      account.setName("account for user " + user.getUsername());
      account.setDateOfCreation(new Date());
      account.setOwner(user);
      account.setStartingBalance(0);
      account.setCurrency(Currency.RUB);
      return account;
   }

   private Category createTestCategory(UserDetails user, long index) {

      final Category category = categoryManagementService.createCategory("work".concat(Long.toString(index)), null,
            user.getUsername());
      return category;
   }

   public void setUserNumber(int userNumber) {

      this.executed = false;
      this.userNumber = userNumber;
   }

}
