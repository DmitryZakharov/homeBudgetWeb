/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.model;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.homebudget.dao.AccountRepository;
import org.homebudget.test.config.TestConfigurator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionModelTest extends TestConfigurator {

   @Autowired
   AccountRepository repository;




   public TransactionModelTest() {

   }

   @Before
   public void setUp() {

      repository.deleteAll();
   }

   @Test
   public void saveTransactionWithImage() throws IOException {

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

      BinaryResource image = createTestDocument();
      transaction.setAttachedImage(image);
      account.addTransaction(transaction);

      repository.save(account);

      List<Account> accounts = repository.findAll();
      assertEquals(1, accounts.size());
      List<Transaction> foundTransactions = (List<Transaction>) accounts.get(0).getTransactions();
      assertEquals(1, foundTransactions.size());
      Transaction result = foundTransactions.get(0);
      assertNotNull(result.getAttachedImage().getContent());

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
