/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.model;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.homebudget.config.TestConfigurator;
import org.homebudget.dao.AccountRepository;
import org.homebudget.dao.CategoryRepository;
import org.homebudget.dao.TransactionRepository;

import org.homebudget.model.Transaction.TransactionType;
import org.homebudget.services.UserManagementService;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionModelTest extends TestConfigurator {

   @Autowired
   private AccountRepository accountRepository;
   
   @Autowired
   private CategoryRepository categoryRepository;
   
   @Autowired
   private TransactionRepository transactionRepository;
   
    
   
   @Autowired
   UserManagementService userManagementService;
   @Autowired
   EntityManagerFactory entityManagerFactory;

   public TransactionModelTest() {

   }
   
   @Before
   public void init() {

	   accountRepository.deleteAll();
      
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


   @Test
   public void saveTransactionWithImage() throws IOException {

      Account account = new Account();
      account.setName("my account");
      account = accountRepository.save(account);
      
      Transaction transaction = new Transaction();
      transaction.setAmount(12);
      Category category = new Category();
      category.setName("dummy");
      category= categoryRepository.save(category);
      
      transaction.setCategory(category);
      transaction.setComment("dummy comment");
      Date date = new Date();
      transaction.setExecutionDate(date);
      transaction.setType(TransactionType.INCOME);

      BinaryResource image = createTestDocument();
      transaction.setAttachment(image);
      account.addTransaction(transaction);

      accountRepository.save(account);

      List<Account> accounts = accountRepository.findAll();
      assertEquals(1, accounts.size());
      List<Transaction> foundTransactions = (List<Transaction>) accounts.get(0).getTransactions();
      assertEquals(1, foundTransactions.size());
      Transaction result = foundTransactions.get(0);
      assertNotNull(result.getAttachment().getContent());

   }

   @Test
   public void saveTransactionWithCategoryHierarchy() {

      Account account = new Account();
      account.setName("my account");
      account = accountRepository.save(account);

      Transaction transaction = new Transaction();
      transaction.setAmount(12);
      Category category1 = new Category();
      String categoryName1 = "category1";
      category1.setName(categoryName1);
      category1= categoryRepository.save(category1);

      Category category2 = new Category();
      String categoryName2 = "category2";
      category2.setName(categoryName2);

      category2.setParent(category1);
      category2 = categoryRepository.save(category2);

      transaction.setCategory(category2);
      transaction.setComment("dummy comment");
      Date date = new Date();
      transaction.setExecutionDate(date);
      transaction.setType(Transaction.TransactionType.INCOME);
      account.addTransaction(transaction);

      accountRepository.save(account);

      List<Account> accounts = accountRepository.findAll();
      assertEquals(1, accounts.size());
      List<Transaction> foundTransactions = (List<Transaction>) accounts.get(0).getTransactions();
      assertEquals(1, foundTransactions.size());
      Transaction resultTransaction = foundTransactions.get(0);
      assertEquals(categoryName2, resultTransaction.getCategory().getName());
      assertEquals(categoryName1, resultTransaction.getCategory().getParent().getName());

   }

}
