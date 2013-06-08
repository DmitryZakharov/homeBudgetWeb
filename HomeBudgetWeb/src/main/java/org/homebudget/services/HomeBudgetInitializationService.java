package org.homebudget.services;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.homebudget.dao.UserRoleRepository;
import org.homebudget.services.utils.DatabasePopulator;
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

      DatabasePopulator databasePopulator = new DatabasePopulator(userManagementService,
            accountManagementService, userRoleRepository, transactionManagementService,
            categoryManagementService);
      
      databasePopulator.initUserRoles();
      
      

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
   
   public void setUserNumber(int userNumber) {

      this.executed = false;
      this.userNumber = userNumber;
   }

}
