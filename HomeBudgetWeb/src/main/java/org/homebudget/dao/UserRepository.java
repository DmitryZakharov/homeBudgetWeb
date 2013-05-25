package org.homebudget.dao;

import java.util.List;
import org.homebudget.model.UserDetails;
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
public interface UserRepository extends JpaRepository<UserDetails, Long> {

    // getAllUsers findAll();
    // getUser(String userNickname)
    public List<UserDetails> findByUserUsername(String userName);

    public List<UserDetails> findByEmail(String email);

}
