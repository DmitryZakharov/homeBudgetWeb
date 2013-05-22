package org.homebudget.dao;

import org.homebudget.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Transactions
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/htmlsingle/#jpa.query-methods
 * @see http://static.springsource.org/spring-data/commons/docs/current/reference/html/repositories.html
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{


}
