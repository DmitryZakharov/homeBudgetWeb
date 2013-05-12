package org.homebudget.controller;

import org.homebudget.dao.HibernateDaoImpl;
import org.homebudget.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@Autowired
	private HibernateDaoImpl hibernateDaoImpl;

	@RequestMapping(value = "/greeting")
	public String sayHello(Model model) {

		Account account = getHibernateDaoImpl().getAccount(new Long(1));
		model.addAttribute("greeting", account.getOwner().getUserName() + " changed");
		return "hello";
	}
	
	// @RequestMapping(value = "/addUser")
	// public String addUser(Model model){
	// UserDetails user = new UserDetails();
	// user.setUserBirthday(new Date());
	// user.setUserName("someUserName");
	// user.setUserSurname("Parcenson");
	//
	// getHibernateDaoImpl().addUser(user);
	// model.addAttribute("greeting", user.getUserName());
	// return "hello";
	//
	// }

	public HibernateDaoImpl getHibernateDaoImpl() {
		return hibernateDaoImpl;
	}

	public void setHibernateDaoImpl(HibernateDaoImpl hibernateDaoImpl) {
		this.hibernateDaoImpl = hibernateDaoImpl;
	}

}
