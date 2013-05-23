package org.homebudget.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.homebudget.dao.UserRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserManagementController {

	@Resource
	private UserRepository userRepositoryDao;

	@RequestMapping(value = "/addUser")
	public String addNewUser(
			@ModelAttribute("userDetails") UserDetails userDetails) {

		if (userDetails.getUserName() != null) {
                        userDetails.addUserRole(UserRole.Role.USER_ROLE);
			userRepositoryDao.save(userDetails);
		}

		System.out.println("User Name: " + userDetails.getUserName());
		System.out.println("User Surname: " + userDetails.getUserSurname());
		System.out.println("User Date of Birth: "
				+ userDetails.getUserBirthday());
		return "addUser";
	}

	@RequestMapping(value = "/usersList")
	public String showAllUsers(Model model) {

		List<UserDetails> usersList = getHibernateDaoImpl().findAll();
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

	public UserRepository getHibernateDaoImpl() {
		return userRepositoryDao;
	}

	public void setHibernateDaoImpl(UserRepository hibernateDaoImpl) {
		this.userRepositoryDao = hibernateDaoImpl;
	}
}
