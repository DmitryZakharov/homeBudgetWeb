package org.homebudget.services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.homebudget.model.Account;
import org.homebudget.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class TransactionValidationService implements IValidationService<Transaction> {

   @Resource
   TransactionManagementService service;

   public boolean supports(Class<?> klass) {

      return Account.class.isAssignableFrom(klass);
   }

   @Override
   public void validate(Transaction target, Errors errors, String accountname) {

      Transaction transaction = (Transaction) target;
      List<String> notEmptyFields = new ArrayList<String>();
      notEmptyFields.add("type");
      notEmptyFields.add("executionDate");
      notEmptyFields.add("amount");
      validateEmptyFields(notEmptyFields, errors);

   }

   @Override
   public void validateEmptyFields(Iterable<String> fields, Errors errors) {

      for (String field : fields) {
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "common.field.required");
      }

   }

}
