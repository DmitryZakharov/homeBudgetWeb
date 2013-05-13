package org.homebudget.controllers;

import java.util.List;

import org.homebudget.dao.AccountRepositoryDaoImpl;
import org.homebudget.dao.UserRepositoryDaoImpl;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public String showAccounts(ModelMap model) {

		final User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		final String username = user.getUsername();

		Long userId = userRepositoryDaoImpl.getUser(username).getUserId();

		List<Account> accounts = accountRepositoryDaoImpl.getAccounts(userId);

		model.addAttribute("accounts", accounts);

		return "account";

	}
	
	@RequestMapping("/createAccount")
	public ModelAndView showContacts() {
		
		return new ModelAndView("createAccount", "command", new Account());
	}
	
	
	@RequestMapping(value= "/create",  method = RequestMethod.POST)
	public String addAccount(@ModelAttribute("account") Account account, BindingResult result) {

		final User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (account != null) {
			final String username = user.getUsername();
			final UserDetails owner = userRepositoryDaoImpl.getUser(username);
			account.setOwner(owner);
			accountRepositoryDaoImpl.addAccount(account);
		}
		return "redirect:";
	}

}
