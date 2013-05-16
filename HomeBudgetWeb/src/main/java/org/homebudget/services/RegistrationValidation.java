package org.homebudget.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userUsername",
                "registration.userUsername.empty");
        
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
                "registration.email.empty");
         
          ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "registration.password.empty");
        
        String userName = aUserDetails.getUserName();
        if ((userName.length()) > 50) {
            errors.rejectValue("userName",
                    "registration.user_name.size");
        }
        String password = aUserDetails.getPassword();
        String confPassword = aUserDetails.getConfpassword();
        if (password.compareTo(confPassword) != 0) {
            errors.rejectValue("password", "registration.password.dont_match");
        }

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

        Matcher m = p.matcher(aUserDetails.getEmail());

        //check  whether any match is found 
        boolean matchFound = m.matches();

        if (!matchFound) {
            errors.rejectValue("email", "registration.email.invalid");
        }
    }
}
