package org.homebudget.services;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.homebudget.dao.UserRepository;
import org.homebudget.dao.UserRoleRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagementService {

	private static final Logger gLogger = Logger.getLogger(UserManagementService.class);
	
	@Resource
	private UserRepository userRepositoryDao;

	@Resource
	private UserRoleRepository userRoleRepository;

	@Resource
	private MailConfirmationService mailConfirmationService;

	@Transactional
	public void saveUserDetails(UserDetails userDetails) {
		userRepositoryDao.save(userDetails);
	}

	@Transactional
	public void saveUserRole(UserRole userRole) {
		userRoleRepository.save(userRole);
	}

	public void registerUser(UserDetails aUserDetails) {

		final String userPassword = aUserDetails.getPassword();
		try {
			String passwordHash = PasswordService.getHash(aUserDetails
					.getPassword());
			aUserDetails.setPassword(passwordHash);
		} catch (Exception e) {// with is a hack. Must be removed. If hashing
			// fails, user must be notified.
			aUserDetails.setPassword(userPassword);
		}
		aUserDetails.addUserRole(UserRole.Role.USER_ROLE);
		// TODO: set to 0, when email confirmation is implemented
		aUserDetails.setEnabled(1);
		mailConfirmationService.sendConfirmation(aUserDetails);
		saveUserDetails(aUserDetails);
	}

	
}