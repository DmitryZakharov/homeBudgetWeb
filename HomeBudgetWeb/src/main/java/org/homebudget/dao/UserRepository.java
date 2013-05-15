package org.homebudget.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.model.UserRole.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 * Repository for Users
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/htmlsingle/#jpa.query-methods
 * @see http://static.springsource.org/spring-data/commons/docs/current/reference/html/repositories.html
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserDetails, Long>{

        // getAllUsers findAll();
        
        
        //getUser(String userNickname)
        public UserDetails findByUserUsername(String userName);
        
        
//        
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	public List<UserDetails> getAllUsers() {
//
//		Query q = getSessionFactory().openSession().createQuery(
//				"from USER_DETAILS");
//
//		return (List<UserDetails>) q.list();
//	}
//
//	public UserDetails getUser(String userNickname) {
//
//		Query q = getSessionFactory().openSession().createQuery(
//				"from USER_DETAILS where USER_USERNAME=?");
//		q.setString(0, userNickname);
//		List<UserDetails> users = (List<UserDetails>) q.list();
//
//		if (users.size() == 1) {
//			return users.get(0);
//		}
//		return null;
//	}

	// public UserRole getRole(Role role) {
	// Query q = getSessionFactory().openSession().createQuery(
	// "from USER_ROLES where ROLE=?");
	// q.setString(0, role.roleName());
	// List<UserRole> userRoles = (List<UserRole>) q.list();
	//
	// if (userRoles.size() == 1) {
	// return userRoles.get(0);
	// }
	// return null;
	// }

//	public Long getUserId(String username) {
//
//		Query q = getSessionFactory().openSession().createQuery(
//				"from USER_DETAILS where USER_USEERNAME=?");
//		q.setString(0, username);
//
//		List<UserDetails> users = (List<UserDetails>) q.list();
//		UserDetails user = null;
//
//		if (users.size() == 1) {
//			user = users.get(0);
//		} else {
//			return null;
//		}
//
//		return user.getUserId();
//	}

//	public void addUser(UserDetails user, Role role) {
//		
//		Query q = getSessionFactory().openSession().createQuery(
//				"from USER_ROLE where USER_ROLE_TYPE=?");
//		q.setString(0, role.name());
//
//		List<UserRole> roles = (List<UserRole>) q.list();
//		UserRole uRole;
//		if (roles.size() == 1) {
//			uRole = roles.get(0);
//		} else {
//			return;
//		}
//		Session session = getSessionFactory().openSession();
//		Transaction tr = session.beginTransaction();
//		user.getUserRoles().add(uRole);
//		session.save(user);
//		tr.commit();
//
//	}
//
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
}
