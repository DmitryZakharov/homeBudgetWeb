package org.homebudget.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.homebudget.model.Account;
import org.homebudget.services.AccountManagementService;
import org.homebudget.services.NewAccountValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/accounts")
public class AccountController extends AbstractController {

   @Autowired
   private NewAccountValidation aNewAccountValidation;

   @Resource
   private AccountManagementService accountManagementService;

   @RequestMapping(method = RequestMethod.GET)
   public List<Account> getAccounts(Model model) {

      final List<Account> accounts = accountManagementService.getAllUserAccounts(getSessionUser()
            .getUsername());

      model.addAttribute(accounts);

      return accounts;
   }

   @RequestMapping(value = "/{name}", method = RequestMethod.GET)
   public String getAccount(@PathVariable("name") String accountName, Model model) {

      final Account account = accountManagementService.getAccount(accountName, getSessionUser()
            .getUsername());

      model.addAttribute(account);
      return "account";
   }

   @RequestMapping(value = "/new", method = RequestMethod.GET)
   public String getAccount(Model model) {

      model.addAttribute(new Account());
      return "account";
   }

   @RequestMapping(method = RequestMethod.POST)
   public String postAccount(@ModelAttribute("account") @Valid Account account, BindingResult result) {

      aNewAccountValidation.validate(account, result);
      if (result.hasErrors()) {
         return "forward:new";
      }
      if (account != null) {

         accountManagementService.saveAccount(account, getSessionUser().getUsername());
      }
      return "redirect:accounts";
   }

   public AccountManagementService getAccountManagementService() {

      return accountManagementService;
   }

   public void setAccountManagementService(AccountManagementService accountManagementService) {

      this.accountManagementService = accountManagementService;
   }

   public NewAccountValidation getaNewAccountValidation() {

      return aNewAccountValidation;
   }

   public void setaNewAccountValidation(NewAccountValidation aNewAccountValidation) {

      this.aNewAccountValidation = aNewAccountValidation;
   }

}
