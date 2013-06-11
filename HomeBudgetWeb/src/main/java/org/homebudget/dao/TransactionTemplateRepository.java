package org.homebudget.dao;

import java.util.List;
import org.homebudget.model.TransactionTemplate;
import org.homebudget.model.UserMetadata;
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
public interface TransactionTemplateRepository extends JpaRepository<TransactionTemplate, Long>{
   public List<TransactionTemplate> findByOwnerMetadata(UserMetadata userMetadata);
}
