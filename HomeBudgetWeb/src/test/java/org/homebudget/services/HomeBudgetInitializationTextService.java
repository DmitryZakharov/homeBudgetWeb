/**
 * 
 */
package org.homebudget.services;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.homebudget.dao.UserRoleRepository;
import org.homebudget.services.AccountManagementService;
import org.homebudget.services.CategoryManagementService;
import org.homebudget.services.TransactionManagementService;
import org.homebudget.services.UserManagementService;
import org.homebudget.services.utils.DatabasePopulator;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;

/**
 * @author dza
 * 
 */
public class HomeBudgetInitializationTextService {

   private static final Logger logger = Logger.getLogger(HomeBudgetInitializationTextService.class);

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

   private DatabasePopulator databasePopulator;

   @PostConstruct
   public void init() {

      databasePopulator = new DatabasePopulator(userManagementService, accountManagementService,
            userRoleRepository, transactionManagementService, categoryManagementService);
      
      initUserRoles();
   }

   /**
    * 
    */
   public HomeBudgetInitializationTextService() {

      // TODO Auto-generated constructor stub
   }

   public void executePopulation() {

      System.out.println("POST CONSTRUCT!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      logger.info("Create initial Population !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      int userCount = 0;
      if (userRoleRepository != null) {
         userCount = userRoleRepository.findAll().size();
         logger.info("User Count: " + userCount);
      }
      if (!executed) {
         databasePopulator.populateUsers(userNumber);
         executed = true;
      }
      else {
         logger.info("ALREADY CREATED MANY USERS!!!!!!!!!!!");
      }
   }

   public void initUserRoles(){
      databasePopulator.initUserRoles();
   }

   public void setUserNumber(int userNumber) {

      this.executed = false;
      this.userNumber = userNumber;
   }

   
   public DatabasePopulator getDatabasePopulator() {
   
      return databasePopulator;
   }

   
   public void setDatabasePopulator(DatabasePopulator databasePopulator) {
   
      this.databasePopulator = databasePopulator;
   }

}
