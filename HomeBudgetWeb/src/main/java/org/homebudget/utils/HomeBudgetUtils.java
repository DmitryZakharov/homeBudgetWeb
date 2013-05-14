package org.homebudget.utils;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.homebudget.model.Account;
import org.homebudget.model.Category;
import org.homebudget.model.Transaction;
import org.homebudget.model.Transaction.TransactionType;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.model.UserRole.Role;
import org.homebudget.services.PasswordService;
import org.homebudget.services.UserManagementService;

public class HomeBudgetUtils {

	public final String HIBERNATE_CONFIG_FILE = "org/homebudget/utils/hibernate.cfg.xml";
	private final org.hibernate.classic.Session session;

	@Resource
	UserManagementService userManagementService;

	// TODO: replace with userManagementService.getRole();
	UserRole userRole;

	public HomeBudgetUtils() {
		SessionFactory sessionFactory = new Configuration().configure(
				HIBERNATE_CONFIG_FILE).buildSessionFactory();
		session = sessionFactory.openSession();
	}

	public void populateUsers(int number) {

		initUserRoles();

		for (int i = 0; i < number; i++) {

			final UserDetails user = new UserDetails();
			user.setUserName("Dmitry" + i);
			user.setUserUsername(user.getUserName() + "_nick");
			user.setUserSurname("Zakharov");
			user.setEmail("some" + i + "@email.com");
			Date birthday = new Date();
			user.setUserBirthday(birthday);
			user.setEnabled(1);

			try {
				user.setPassword(PasswordService.getHash("123" + i));
			} catch (Exception e) {
				e.printStackTrace();
			}

			final Account account = new Account();
			account.setDateOfCreation(new Date());
			account.setOwner(user);
			account.setStartingBalance(0);

			final Transaction transaction = new Transaction();
			transaction.setAmount(10);

			final Category category = new Category();
			category.setCategory("work");

			transaction.setCategory(category);
			transaction.setDateOFTransaction(new Date());
			transaction.setTransactionType(TransactionType.INCOME);

			account.getTransactions().add(transaction);

			// UserRole role =
			// userManagementService.getRole(UserRole.Authority.USER_ROLE);
			user.getUserRoles().add(userRole);

			session.beginTransaction();
			session.save(user);
			session.save(category);
			session.save(account);

			session.getTransaction().commit();
		}

		session.close();

	}

	private void initUserRoles() {

		for (Role role : Role.values()) {
			session.beginTransaction();
			UserRole uRole = new UserRole();
			uRole.setUserRole(role);
			if (role == Role.USER_ROLE) {
				userRole = uRole;
			}
			session.save(uRole);
			session.getTransaction().commit();
		}
	}
}
