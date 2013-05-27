package org.homebudget.services;

import org.springframework.validation.Errors;

public interface IValidationService<T extends Object> {
  public void validate(Object target, Errors errors);

}