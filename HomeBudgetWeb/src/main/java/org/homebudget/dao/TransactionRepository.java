package org.homebudget.dao;

import java.util.Date;
import java.util.List;
import org.homebudget.model.Account;
import org.homebudget.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for Transactions
 * 
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/htmlsingle/#jpa.query-methods
 * @see http://static.springsource.org/spring-data/commons/docs/current/reference/html/repositories.html
 */
@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

   public List<Transaction> findByAccount(Account account);
   
   public Transaction findByComment(String transactionComment);
   
   public List<Transaction> findByAccountAndExecutionDateBetween (Account account, Date start, Date end);
   
}
