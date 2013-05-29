package org.homebudget.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.homebudget.model.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class UserValidationService implements IValidationService<UserDetails> {

   @Resource
   UserManagementService service;

   public boolean supports(Class<?> klass) {

      return UserDetails.class.isAssignableFrom(klass);
   }

   @Override
   public void validate(UserDetails target, Errors errors, String username) {

      UserDetails aUserDetails = (UserDetails) target;

      validateEmptyFields(errors);

      String userUsername = aUserDetails.getUsername();
      validateUserUsername(userUsername, errors);

      String password = aUserDetails.getPassword();
      String confPassword = aUserDetails.getConfpassword();
      if (password == null || confPassword == null || password.compareTo(confPassword) != 0) {
         errors.rejectValue("password", "registration.password.dont_match");
      }

      String email = aUserDetails.getEmail();
      validateEmail(email, errors);

   }

   private void validateEmail(String email, Errors errors) {

      Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
      Matcher m = p.matcher(email);

      // check whether any match is found
      boolean matchFound = m.matches();

      if (!matchFound) {
         errors.rejectValue("email", "registration.email.invalid");
      }

      final UserDetails result = service.getUserByEmail(email);
      if (result != null) {
         errors.rejectValue("email", "registration.email.notunique");
      }
   }

   private void validateEmptyFields(Errors errors) {
      List<String> notEmptyFields = new ArrayList<String>();
      notEmptyFields.add("username");
      notEmptyFields.add("email");
      notEmptyFields.add("password");
      notEmptyFields.add("confpassword");
      validateEmptyFields(notEmptyFields, errors);
   }

   private void validateUserUsername(String userUsername, Errors errors) {

      if (userUsername == null || userUsername.isEmpty() || (userUsername.length()) > 50) {
         errors.rejectValue("username", "registration.user_username.size");
      }
      else {
         UserDetails result = service.getUserDetailsByUsername(userUsername);
         if (result != null && result.getUsername().equals(userUsername)) {
            errors.rejectValue("username", "registration.user_username.notunique");
         }
      }

   }

   @Override
   public void validateEmptyFields(
       Iterable<String> fields, Errors errors) {
      for (String field : fields) {
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "common.field.required");
      }
   }

}
