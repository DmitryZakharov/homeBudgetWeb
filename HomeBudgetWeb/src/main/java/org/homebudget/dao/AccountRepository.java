package org.homebudget.dao;

import java.util.List;
import org.homebudget.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Accounts
 * @see http://static.springsource.org/spring-data/data-jpa/docs/current/reference/htmlsingle/#jpa.query-methods
 * @see http://static.springsource.org/spring-data/commons/docs/current/reference/html/repositories.html
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    public List<Account> findByUserId(Long userId);

//	@Autowired
//	private SessionFactory sessionFactory;
//
//	public List<Account> getAccounts(Long userId) {
//
//		final Query q = getSessionFactory().openSession().createQuery(
//				"from ACCOUNT where USER_ID=?");
//		q.setLong(0, userId);
//
//		final List<Account> accounts = (List<Account>) q.list();
//
//		return accounts;
//	}
//
//	public void addAccount(Account account) {
//
//		getSessionFactory().openSession().save(account);
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
