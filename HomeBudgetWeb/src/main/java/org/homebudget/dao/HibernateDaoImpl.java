package org.homebudget.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public Account getAccount(long id) {

		Account account = (Account) getSessionFactory().openSession().get(
				Account.class, id);
		return account;
	}

	public List<UserDetails> getAllUsers() {

		Query q = getSessionFactory().openSession().createQuery(
				"from USER_DETAILS");

		return (List<UserDetails>) q.list();

	}

	public UserDetails getUser(String userNickname) {

		Query q = getSessionFactory().openSession().createQuery(
				"from USER_DETAILS where USER_NICKNAME=?");
		q.setString(0, userNickname);
		List<UserDetails> users = (List<UserDetails>) q.list();

		if (users.size() == 1)
			return users.get(0);
		return null;
	}

	public void addUser(UserDetails user) {
		getSessionFactory().openSession().save(user);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
