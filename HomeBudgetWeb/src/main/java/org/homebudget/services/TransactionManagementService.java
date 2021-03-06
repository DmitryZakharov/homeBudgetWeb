package org.homebudget.services;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.homebudget.dao.AccountRepository;
import org.homebudget.dao.TransactionRepository;
import org.homebudget.model.Account;
import org.homebudget.model.BinaryResource;
import org.homebudget.model.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TransactionManagementService {

   @Resource
   private TransactionRepository transactionRepository;

   @Resource
   private AccountRepository accountRepository;

   @Resource
   private ResourceManagementService resourceManagementService;

   public void saveTransaction(Transaction transaction, String accountname) {
      final Account parent = accountRepository.findByName(accountname);
      transaction.setAccount(parent);
      transactionRepository.save(transaction);
   }

   public List<Transaction> getAllAccountTransactions(String accountname) {

      final Account accountDetails = accountRepository.findByName(accountname);

      return transactionRepository.findByAccount(accountDetails);
   }
   
   public List<Transaction> getAllAccountTransactionsBetween(String accountname, Date start, Date end) {

      final Account accountDetails = accountRepository.findByName(accountname);

      return transactionRepository.findByAccountAndExecutionDateBetween(accountDetails, start, end);
   }


   public Transaction getTransaction(Long transactionId, String accountname) {

      Transaction transaction = transactionRepository.findOne(transactionId);

      if (transaction != null && transaction.getAccount().getName().equals(accountname)) {
         return transaction;
      }
      return null;
   }

   public void deleteAll() {
      transactionRepository.deleteAll();
   }

   public Transaction getTransaction(Long transactionId) {
      return transactionRepository.findOne(transactionId);
   }

   public void deleteTransaction(Long transactionId) {
      transactionRepository.delete(transactionId);
   }

   public void deleteTransaction(Transaction transaction) {
      transactionRepository.delete(transaction);
   }

   public void updateTransactionDetails(Transaction oldTransaction, Transaction newTransaction) {
	  if(isAttachmentEmpty(newTransaction.getAttachment())){
	      BeanUtils.copyProperties(newTransaction, oldTransaction, new String[]{"id", "account", "attachment"});  
	  }
	  else{
	      BeanUtils.copyProperties(newTransaction, oldTransaction, new String[]{"id", "account"});  

	  }
      transactionRepository.save(oldTransaction);
   }

private boolean isAttachmentEmpty(BinaryResource attachment) {
	if(attachment == null || attachment.getFilename() == null || attachment.getFilename().isEmpty()){
		return true;
	}
	
	return false;
}

   public void updateTransactionDetails(Transaction oldTransaction, Transaction newTransaction,
       MultipartFile attachment) {
      if (attachment != null) {
         BinaryResource resource = resourceManagementService.getResource(attachment);
         newTransaction.setAttachment(resource);
      }
      updateTransactionDetails(oldTransaction, newTransaction);
   }

}