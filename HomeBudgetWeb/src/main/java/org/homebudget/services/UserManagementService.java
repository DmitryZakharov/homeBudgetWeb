package org.homebudget.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
   public void deleteUserDetails(UserDetails userDetails) {

      userRepositoryDao.delete(userDetails);
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
      mailConfirmationService.sendConfirmation(aUserDetails);
      saveUserDetails(aUserDetails);
   }

   private Date getBirthdayFromString(String dateString) {

      // password is replaced with hash after validation of the form.
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      System.out.println("DateString read: " + dateString);
      gLogger.info("DateString read: " + dateString);
      Date birthday = null;
      try {
         birthday = format.parse(dateString);
      }
      catch (ParseException ex) {
         gLogger.error("Datestring could not be parsed " + dateString);
      }
      return birthday;
   }

   public Collection<UserDetails> getUserByUsername(String userName) {

      return userRepositoryDao.findByUserUsername(userName);
   }

   public Collection<UserDetails> getUserByEmail(String email) {

      return userRepositoryDao.findByEmail(email);
   }

   public void updateUserDetails(UserDetails oldUserDetails, UserDetails newUserDetails) {

      oldUserDetails.setUserName(newUserDetails.getUserName());
      oldUserDetails.setUserSurname(newUserDetails.getUserSurname());
      oldUserDetails.setUserUsername(newUserDetails.getUserUsername());
      final String userPassword = newUserDetails.getPassword();
      try {
         final String passwordHash = PasswordService.getHash(newUserDetails.getPassword());
         oldUserDetails.setPassword(passwordHash);
      }
      catch (Exception e) {// with is a hack. Must be removed. If hashing
         // fails, user must be notified.
         oldUserDetails.setPassword(userPassword);
      }
      oldUserDetails.setEmail(newUserDetails.getEmail());
      oldUserDetails.setUserBirthday(newUserDetails.getUserBirthday());

      userRepositoryDao.save(oldUserDetails);

   }
   // public UserRole getRole(Role role){
   // UserRole result = userRoleRepository.findByUserRole(role);
   // return result;
   // }
}