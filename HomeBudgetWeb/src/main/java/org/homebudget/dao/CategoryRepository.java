package org.homebudget.dao;

import java.util.List;
import org.homebudget.model.Category;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for Users
 * 
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/htmlsingle/#jpa.query-methods
 * @see http://static.springsource.org/spring-data/commons/docs/current/reference/html/repositories.html
 */
@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

   public Category findByNameAndOwnerMetadata(String name, UserMetadata owner);
   
   public List<Category> findByOwnerMetadata(UserMetadata owner);

   public UserDetails findById(Long id);

}
