package org.homebudget.utils;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.homebudget.dao.UserRoleRepository;
import org.homebudget.model.Account;
import org.homebudget.model.Category;
import org.homebudget.model.Transaction;
import org.homebudget.model.Transaction.TransactionType;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.model.UserRole.Role;
import org.homebudget.services.AccountManagementService;
import org.homebudget.services.PasswordService;
import org.homebudget.services.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HomeBudgetInitializationService {

	private final Logger logger = LoggerFactory
			.getLogger(HomeBudgetInitializationService.class);

	@Resource
	UserManagementService userManagementService;

	@Resource
	AccountManagementService accountManagementService;

	@Resource
	UserRoleRepository userRoleRepository;

	private boolean executed = false;

	@PostConstruct
	private void executePopulation() {
		System.out
				.println("Create initial Population !!!!!!!!!!!!!!!!!!!!!!!!!!!! " + this);
		logger.info("Create initial Population !!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		if (!executed) {
			populateUsers(10);
			executed = true;

		}else {
			System.out.println("ALREADY CREATED MANY USERS!!!!!!!!!!!");
		}

	}

	private void populateUsers(int number) {
		logger.info("initialize database with " + number + " users");
		initUserRoles();

		UserRole uRole = userRoleRepository
				.findByUserRole(UserRole.Role.USER_ROLE);
		for (int i = 0; i < number; i++) {
			UserDetails user = createTestUser(i);
			logger.info("Creating user: " + user.getUserName());

			Account account = createTestAccount(user);
			logger.info("Creating account: " + account.getAccountName());

			Category category = createTestCategory();
			logger.info("Creating category: " + category.getCategory());

			Transaction transaction = createTestTransaction(category);
			logger.info("Creating transaction: " + transaction.getAmount());

			// account.getTransactions().add(transaction);

			user.getUserRoles().add(uRole);

			userManagementService.saveUserDetails(user);
		}
	}

	private void initUserRoles() {

		for (Role role : Role.values()) {
			UserRole uRole = new UserRole();
			uRole.setUserRole(role);
			logger.info("Creating role: " + uRole.getUserRole());
			userManagementService.saveUserRole(uRole);
		}
	}

	private UserDetails createTestUser(int i) {
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

		return user;
	}

	private Transaction createTestTransaction(final Category category) {
		final Transaction transaction = new Transaction();
		transaction.setAmount(10);
		transaction.setCategory(category);
		transaction.setDateOFTransaction(new Date());
		transaction.setTransactionType(TransactionType.INCOME);

		return transaction;
	}

	private Account createTestAccount(UserDetails user) {
		final Account account = new Account();
		account.setDateOfCreation(new Date());
		account.setOwner(user);
		account.setStartingBalance(0);
		return account;
	}

	private Category createTestCategory() {
		Category category = new Category();
		category.setCategory("work");
		return category;
	}
}