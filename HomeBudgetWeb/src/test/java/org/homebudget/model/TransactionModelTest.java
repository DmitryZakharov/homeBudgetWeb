/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.homebudget.dao.AccountRepository;
import org.homebudget.services.ResourceLoaderService;
import org.homebudget.test.config.TestConfigurator;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

public class TransactionModelTest extends TestConfigurator {

   @Autowired
   AccountRepository repository;

   @Autowired
   ApplicationContext applicationContext;

   public TransactionModelTest() {

   }

   @After
   public void tearDown() {

      repository.deleteAll();
   }

   @Test
   public void saveTransactionWithImage() {

      Account account = new Account();
      account.setName("my account");

      Transaction transaction = new Transaction();
      transaction.setAmount(12);
      Category category = new Category();
      category.setName("dummy");

      transaction.setCategory(category);
      transaction.setComment("dummy comment");
      Date date = new Date();
      transaction.setExecutionDate(date);
      transaction.setType(Transaction.TransactionType.INCOME);

      ResourceLoaderService resourceLoader = (ResourceLoaderService) applicationContext
            .getBean("resourceLoaderService");

      Resource resource = resourceLoader.getResource("classpath:docs/foto2.jpg");

      BinaryResource image = new BinaryResource(resource);
      transaction.setAttachedImage(image);
      account.addTransaction(transaction);

      repository.save(account);

      List<Account> accounts = repository.findAll();
      assertEquals(1, accounts.size());
      List<Transaction> foundTransactions = (List<Transaction>) accounts.get(0).getTransactions();
      assertEquals(1, foundTransactions.size());
      Transaction result = foundTransactions.get(0);
      assertEquals(transaction.getAttachedImage().getResource().length, result.getAttachedImage()
            .getResource().length);

   }

   @Test
   public void saveTransactionWithCategoryHierarchy() {

      Account account = new Account();
      account.setName("my account");

      Transaction transaction = new Transaction();
      transaction.setAmount(12);
      Category category1 = new Category();
      String categoryName1 = "category1";
      category1.setName(categoryName1);

      Category category2 = new Category();
      String categoryName2 = "category2";
      category2.setName(categoryName2);

      category2.setParent(category1);

      transaction.setCategory(category2);
      transaction.setComment("dummy comment");
      Date date = new Date();
      transaction.setExecutionDate(date);
      transaction.setType(Transaction.TransactionType.INCOME);
      account.addTransaction(transaction);

      repository.save(account);

      List<Account> accounts = repository.findAll();
      assertEquals(1, accounts.size());
      List<Transaction> foundTransactions = (List<Transaction>) accounts.get(0).getTransactions();
      assertEquals(1, foundTransactions.size());
      Transaction resultTransaction = foundTransactions.get(0);
      assertEquals(categoryName2, resultTransaction.getCategory().getName());
      assertEquals(categoryName1, resultTransaction.getCategory().getParent().getName());

   }

}
