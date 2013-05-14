
package org.homebudget.services;

import org.homebudget.model.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class RegistrationValidation {
     public boolean supports(Class<?> klass) {
    return UserDetails.class.isAssignableFrom(klass);
  }

  public void validate(Object target, Errors errors) {
    UserDetails aUserDetails = (UserDetails) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
        "NotEmpty.registration.userName",
        "User Name must not be Empty.");
    String userName = aUserDetails.getUserName();
    if ((userName.length()) > 50) {
      errors.rejectValue("userName",
          "lengthOfUser.registration.userName",
          "User Name must not more than 50 characters.");
    }
  }
}
