/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.homebudget.dao.AccountRepository;
import org.homebudget.services.ResourceLoaderService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:/config/homebudget-servlet.xml",
    "classpath:/config/datasource-config.xml",
    "classpath:/config/persistence-config.xml",
    "classpath:/config/homebudget-mail.xml"})
public class TransactionModelTest {

    @Before
    public void setUp() {
    }

    @Autowired
    AccountRepository repository;

    @Autowired
    ApplicationContext applicationContext;

    public TransactionModelTest() {
    }

    @Test
    public void saveTransactionWithImage() {
        Account account = new Account();
        account.setAccountName("my account");

        Transaction transaction = new Transaction();
        transaction.setAmount(12);
        Category category = new Category();
        category.setCategory("dummy");

        transaction.setCategory(category);
        transaction.setComment("dummy comment");
        Date date = new Date();
        transaction.setDateOFTransaction(date);
        transaction.setTransactionType(Transaction.TransactionType.INCOME);

        ResourceLoaderService resourceLoader = (ResourceLoaderService) applicationContext
            .getBean("resourceLoaderService");

        Resource resource = resourceLoader
            .getResource("classpath:docs/foto2.jpg");

        BinaryResource image = new BinaryResource(resource);
        transaction.setTransactionImage(image);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);
        account.setTransactions(transactions);
        try {
            repository.save(account);

            List<Account> accounts = repository.findAll();
            assertEquals(1, accounts.size());
            List<Transaction> foundTransactions = (List<Transaction>) accounts
                .get(0).getTransactions();
            assertEquals(1, foundTransactions.size());
            Transaction result = foundTransactions.get(0);
            assertEquals(
                transaction.getTransactionImage().getResource().length,
                result.getTransactionImage().getResource().length);
        } finally {
            repository.delete(account);
        }

    }

}