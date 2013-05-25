package org.homebudget.services;

import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class NewAccountValidation {

    public boolean supports(Class<?> klass) {

        return UserDetails.class.isAssignableFrom(klass);
    }

    public void validate(Object target, Errors errors) {

        Account account = (Account) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountName", "account.name.required",
                "field must not be empty.");
        String accountName = account.getAccountName();
        if ((accountName.length()) > 50) {
            errors.rejectValue("accountName", "account.name.length.toolong",
                    "Account name must not be more than 50 characters.");
        }
    }

}
