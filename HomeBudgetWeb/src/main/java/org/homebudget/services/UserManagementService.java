package org.homebudget.services;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.homebudget.dao.UserRepository;
import org.homebudget.dao.UserRoleRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.springframework.beans.BeanUtils;
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
   private MailService mailService;

   public UserDetails getNewUser(UserRole.Role role) {

      return new UserDetails(role);
   }

   @Transactional
   public void saveUserDetails(UserDetails userDetails) {

      userRepositoryDao.save(userDetails);
   }

   @Transactional
   public void deleteUserDetails(UserDetails userDetails) {

      userRepositoryDao.delete(userDetails);
   }

   @Transactional
   public void deleteAllUserDetails() {
      
      userRepositoryDao.deleteAll();
   }

   @Transactional
   public void saveUserRole(UserRole userRole) {

      userRoleRepository.save(userRole);
   }

   public void registerUser(UserDetails aUserDetails) {

      final String userPassword = aUserDetails.getPassword();
      try {
         String passwordHash = PasswordService.getHash(aUserDetails.getPassword());
         aUserDetails.setPassword(passwordHash);
      }
      catch (Exception e) {// with is a hack. Must be removed. If hashing
         // fails, user must be notified.
         aUserDetails.setPassword(userPassword);
      }
      aUserDetails.addUserRole(UserRole.Role.USER_ROLE);
      // TODO: set to 0, when email confirmation is implemented
      aUserDetails.setEnabled(1);
      mailService.sendConfirmation(aUserDetails);
      saveUserDetails(aUserDetails);
   }

   public List<UserDetails> getAllUsers() {

      return userRepositoryDao.findAll();
   }

   public UserDetails getUserDetailsByUsername(String userName) {

      return userRepositoryDao.findByUsername(userName);
   }

   public UserDetails getUserByEmail(String email) {

      return userRepositoryDao.findByEmail(email);
   }

   public void updateUserDetails(UserDetails oldUserDetails, UserDetails newUserDetails) {

      BeanUtils.copyProperties(newUserDetails, oldUserDetails, new String[]{"password", "userId",
         "userRoles", "enabled"});
      final String userPassword = newUserDetails.getPassword();
      try {
         final String passwordHash = PasswordService.getHash(newUserDetails.getPassword());
         oldUserDetails.setPassword(passwordHash);
      }
      catch (Exception e) {// with is a hack. Must be removed. If hashing
         // fails, user must be notified.
         oldUserDetails.setPassword(userPassword);
      }
      userRepositoryDao.save(oldUserDetails);

   }

}