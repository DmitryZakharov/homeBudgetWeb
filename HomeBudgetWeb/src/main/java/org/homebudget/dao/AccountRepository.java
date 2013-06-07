package org.homebudget.dao;

import java.util.List;
import org.homebudget.model.Account;
import org.homebudget.model.UserMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for Accounts
 * 
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/htmlsingle/#jpa.query-methods
 * @see http://static.springsource.org/spring-data/commons/docs/current/reference/html/repositories.html
 */
@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

   public List<Account> findByOwnerMetadata(UserMetadata userMetadata);
   
   public Account findByName(String accountName);
   
}
