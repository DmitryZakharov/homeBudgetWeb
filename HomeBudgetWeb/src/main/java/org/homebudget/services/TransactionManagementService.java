package org.homebudget.services;

import java.util.List;
import javax.annotation.Resource;
import org.homebudget.dao.AccountRepository;
import org.homebudget.dao.TransactionRepository;
import org.homebudget.model.Account;
import org.homebudget.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionManagementService {

   @Resource
   private TransactionRepository transactionRepository;

   @Resource
   private AccountRepository accountRepository;

   public void saveTransaction(Transaction transaction, String accountname) {
      final Account parent = accountRepository.findByName(accountname);
      transaction.setParent(parent);
      transactionRepository.save(transaction);
   }

   public List<Transaction> getAllAccountTransactions(String accountname) {

      final Account accountDetails = accountRepository.findByName(accountname);

      return transactionRepository.findByParent(accountDetails);
   }
   
    public List<Transaction> getAllTransactions() {

      return transactionRepository.findAll();
   }

   public Transaction getTransaction(String transactionComment, String accountname) {

      Transaction transaction = transactionRepository.findByComment(transactionComment);

      if (transaction != null && transaction.getParent().getName().equals(accountname)) {
         return transaction;
      }
      return null;
   }
   
      
   public void deleteAll(){
      transactionRepository.deleteAll();
   }

}