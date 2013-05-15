package org.homebudget.dao;

import org.homebudget.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for UserRoles
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/htmlsingle/#jpa.query-methods
 * @see http://static.springsource.org/spring-data/commons/docs/current/reference/html/repositories.html
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
//    public UserRole findByUserRole(UserRole.Role role);

}
