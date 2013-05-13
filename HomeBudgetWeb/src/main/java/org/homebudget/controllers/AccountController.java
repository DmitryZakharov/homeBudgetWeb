package org.homebudget.controllers;

import java.util.List;

import org.homebudget.dao.AccountRepositoryDaoImpl;
import org.homebudget.dao.UserRepositoryDaoImpl;
import org.homebudget.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	@Qualifier("accountRepositoryDao")
	private AccountRepositoryDaoImpl accountRepositoryDaoImpl;

	@Autowired
	@Qualifier("userRepositoryDao")
	private UserRepositoryDaoImpl userRepositoryDaoImpl;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		final User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		final String username = user.getUsername();

		Long userId = userRepositoryDaoImpl.getUser(username).getUserId();

		List<Account> accounts = accountRepositoryDaoImpl.getAccounts(userId);

		model.addAttribute("accounts", accounts);

		return "account";

	}

}
