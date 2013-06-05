package org.homebudget.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.homebudget.model.Account;
import org.homebudget.model.Category;
import org.homebudget.model.Transaction;
import org.homebudget.model.Transaction.TransactionType;
import org.homebudget.services.AccountManagementService;
import org.homebudget.services.CategoryManagementService;
import org.homebudget.services.TransactionManagementService;
import org.homebudget.services.TransactionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/categories")
public class CategoryController extends AbstractController {
   
   @Resource
   private CategoryManagementService categoryManagementService;

   @RequestMapping(method = RequestMethod.GET)
   public List<Category> getCategories(Model model) {

      final List<Category> accounts = categoryManagementService.getAllCategories();

      model.addAttribute(accounts);

      return accounts;
   }

}
