package org.homebudget.services;

import org.springframework.validation.Errors;

public interface IValidationService<T> {
  public void validate(T target, Errors errors, String parent);
  public void validateEmptyFields(Iterable<String> fields, Errors errors);

}