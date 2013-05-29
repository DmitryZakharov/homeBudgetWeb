package org.homebudget.dao;

import java.util.List;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Accounts
 * 
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/htmlsingle/#jpa.query-methods
 * @see http://static.springsource.org/spring-data/commons/docs/current/reference/html/repositories.html
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

   public List<Account> findByOwner(UserDetails user);
   
   public Account findByAccountName(String accountName);
   
}
