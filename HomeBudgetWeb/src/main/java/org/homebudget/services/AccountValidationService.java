package org.homebudget.services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class AccountValidationService implements IValidationService<Account> {

   @Resource
   AccountManagementService service;

   public boolean supports(Class<?> klass) {

      return UserDetails.class.isAssignableFrom(klass);
   }

   @Override
   public void validate(Object target, Errors errors) {

      Account account = (Account) target;
      List<String> notEmptyFields = new ArrayList<String>();
      notEmptyFields.add("accountName");
      notEmptyFields.add("dateOfCreation");
      notEmptyFields.add("startingBalance");
      validateEmptyFields(notEmptyFields, errors);

      String accountName = account.getAccountName();
      String ownerUsername = account.getOwner().getUserUsername();
      validateAccountName(accountName, ownerUsername, errors);
   }

   private void validateAccountName(String accountName, String ownerUsername, Errors errors) {

      if ((accountName.length()) > 50) {
         errors.rejectValue("accountName", "account.name.length.toolong");
      }

      Account result = service.getAccount(accountName, ownerUsername);
      if (result != null) {
         errors.rejectValue("accountName", "registration.email.notunique");
      }
   }

   @Override
   public void validateEmptyFields(Iterable<String> fields, Errors errors) {
      for (String field : fields) {
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "common.field.required");
      }

   }

}
