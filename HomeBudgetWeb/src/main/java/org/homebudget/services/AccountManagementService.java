package org.homebudget.services;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.homebudget.dao.AccountRepository;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountManagementService {
   
   private static final Logger logger = Logger.getLogger(AccountManagementService.class);

   @Resource
   private AccountRepository accountRepository;

   @Resource
   private UserRepository userRepository;

   public void saveAccount(Account account, String username) {

      final UserDetails owner = userRepository.findByUsername(username);
      account.setOwner(owner);

      accountRepository.save(account);
   }

   public void updateAccountDetails(Account oldAccount, Account newAccount) {

      BeanUtils.copyProperties(newAccount, oldAccount, new String[]{"id", "owner"});

      accountRepository.save(oldAccount);

   }

   public List<Account> getAllUserAccounts(String username) {

      final UserDetails userDetails = userRepository.findByUsername(username);

      return accountRepository.findByOwner(userDetails);
   }

   public Account getAccount(String accountName, String username) {

      Account account = accountRepository.findByName(accountName);

      if (account != null && account.getOwner().getUsername().equals(username)) {
         return account;
      }
      return null;
   }

   public Account getAccount(long id, String username) {

      Account account = accountRepository.findOne(id);

      if (account.getOwner().getUsername().equals(username)) {
         return account;
      }
      return null;
   }

   public void deleteAccount(long id) {
      accountRepository.delete(id);
   }

   public void deleteAccount(Account account) {
      accountRepository.delete(account);
   }

   public void deleteAll() {

      accountRepository.deleteAll();
   }

   public boolean isAuthorized(String accountName, String sessionUserName) {
      Account account = getAccount(accountName, sessionUserName);
      if (account == null) {
         logger.warn("The user " + sessionUserName + " is not authorized for " + accountName);
         return false;
      }
      else {
         return true;
      }
   }

   public boolean isAuthorized(String accountName, String sessionUserName, Long transactionId) {
      Account account = getAccount(accountName, sessionUserName);
      if (account == null) {
          logger.warn("The user " + sessionUserName + " is not authorized for " + accountName);
         return false;
      }
      else {
         
         return account.hasTransaction(transactionId);
      }
   }

}