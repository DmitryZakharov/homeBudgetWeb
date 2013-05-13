package org.homebudget.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.homebudget.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Account> getAccounts(Long userId) {

		final Query q = getSessionFactory().openSession().createQuery(
				"from ACCOUNT where USER_ID=?");
		q.setLong(0, userId);

		final List<Account> accounts = (List<Account>) q.list();

		return accounts;
	}

	public void addAccount(Account account) {

		getSessionFactory().openSession().save(account);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
