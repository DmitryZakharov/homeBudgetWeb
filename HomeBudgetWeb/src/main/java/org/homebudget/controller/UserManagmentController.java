package org.homebudget.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.homebudget.dao.UserRepositoryDaoImpl;
import org.homebudget.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserManagmentController {

	@Autowired
	private UserRepositoryDaoImpl hibernateDaoImpl;

	@RequestMapping(value = "/addUser")
	public String addNewUser(
			@ModelAttribute("userDetails") UserDetails userDetails) {

		if (userDetails.getUserName() != null) {
			hibernateDaoImpl.addUser(userDetails);
		}

		System.out.println("User Name: " + userDetails.getUserName());
		System.out.println("User Surname: " + userDetails.getUserSurname());
		System.out.println("User Date of Birth: "
				+ userDetails.getUserBirthday());
		return "addUser";
	}

	@RequestMapping(value = "/usersList")
	public String showAllUsers(Model model) {

		List<UserDetails> usersList = getHibernateDaoImpl().getAllUsers();
		System.out.println("counted users: " + usersList.size());

		model.addAttribute("usersList", usersList);
		return "usersList";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	public UserRepositoryDaoImpl getHibernateDaoImpl() {
		return hibernateDaoImpl;
	}

	public void setHibernateDaoImpl(UserRepositoryDaoImpl hibernateDaoImpl) {
		this.hibernateDaoImpl = hibernateDaoImpl;
	}
}
