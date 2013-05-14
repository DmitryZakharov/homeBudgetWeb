package org.homebudget.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.homebudget.dao.UserRepositoryDaoImpl;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.services.PasswordService;
import org.homebudget.services.RegistrationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	// private final Logger logger = LoggerFactory
	// .getLogger(RegistrationController.class);

	@Autowired
	private RegistrationValidation aRegistrationValidation;

	@Autowired
	@Qualifier("userRepositoryDao")
	private UserRepositoryDaoImpl userRepositoryDao;

	@RequestMapping(method = RequestMethod.GET)
	protected String showRegistration(Map<String, Object> model)
			throws Exception {
		UserDetails aUserDetails = new UserDetails();
		UserRole aUserRole = new UserRole();
		aUserRole.setUserRole(UserRole.Role.USER_ROLE);
		model.put("userDetails", aUserDetails);
		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@Valid UserDetails aUserDetails,
			BindingResult result) {

		aRegistrationValidation.validate(aUserDetails, result);
		if (result.hasErrors()) {
			return "registration";
		}
		// password is replaced with hash after validation of the form.
		final String userPassword = aUserDetails.getPassword();
		try {
			String passwordHash = PasswordService.getHash(aUserDetails
					.getPassword());
			aUserDetails.setPassword(passwordHash);
		} catch (Exception e) {// with is a hack. Must be removed. If hashing
								// fails, user must be notified.
			aUserDetails.setPassword(userPassword);
		}

		userRepositoryDao.addUser(aUserDetails, UserRole.Role.USER_ROLE);
		return "registrationsuccess";
	}
}
