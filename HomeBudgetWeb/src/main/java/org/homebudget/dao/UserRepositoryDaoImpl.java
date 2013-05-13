package org.homebudget.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public List<UserDetails> getAllUsers() {

		Query q = getSessionFactory().openSession().createQuery(
				"from USER_DETAILS");

		return (List<UserDetails>) q.list();

	}

	public UserDetails getUser(String userNickname) {

		Query q = getSessionFactory().openSession().createQuery(
				"from USER_DETAILS where USER_USERNAME=?");
		q.setString(0, userNickname);
		List<UserDetails> users = (List<UserDetails>) q.list();

		if (users.size() == 1)
			return users.get(0);
		return null;
	}
	
	public Long getUserId(String username){
		
		Query q = getSessionFactory().openSession().createQuery(
				"from USER_DETAILS where USER_USEERNAME=?");
		q.setString(0, username);

		List<UserDetails> users = (List<UserDetails>) q.list();
		UserDetails user = null;

		if (users.size() == 1) {
			user = users.get(0);
		} else {
			return null;
		}
		
		return user.getUserId();
	}

	public void addUser(UserDetails user, String role) {
		UserRole uRole = new UserRole();
		uRole.setAuthority(role);
		user.setUserRole(uRole);
		getSessionFactory().openSession().save(uRole);
		getSessionFactory().openSession().save(user);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
