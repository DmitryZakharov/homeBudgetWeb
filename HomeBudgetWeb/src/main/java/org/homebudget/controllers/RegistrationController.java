package org.homebudget.controllers;

import java.util.Map;

import javax.validation.Valid;
import javax.inject.Inject;
import org.homebudget.dao.UserRepositoryDaoImpl;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.services.ReistrationValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private final Logger logger = LoggerFactory
			.getLogger(RegistrationController.class);


	private ReistrationValidation aReistrationValidation;

	@Autowired
	@Qualifier("userRepositoryDao")
	private UserRepositoryDaoImpl userRepositoryDao;

	@RequestMapping(method = RequestMethod.GET)
	protected String showRegistration(Map model) throws Exception {
		UserDetails aUserDetails = new UserDetails();
		UserRole aUserRole = new UserRole();
		aUserRole.setAuthority("ROLE_USER");
		model.put("userDetails", aUserDetails);
		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@Valid UserDetails aUserDetails,
			BindingResult result) {

		aReistrationValidation.validate(aUserDetails, result);
		if (result.hasErrors()) {
			return "registration";
		}
		userRepositoryDao.addUser(aUserDetails, "ROLE_USER");
		return "registrationsuccess";
	}
	
	@Inject
	public void setaReistrationValidation(
			ReistrationValidation aReistrationValidation) {
		this.aReistrationValidation = aReistrationValidation;
	}

}
