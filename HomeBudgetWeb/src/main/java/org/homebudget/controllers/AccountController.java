package org.homebudget.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.homebudget.dao.AccountRepository;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
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
   private AccountRepository accountRepositoryDaoImpl;

   @Resource
   private UserRepository userRepositoryDaoImpl;

   @Resource
   private AccountManagementService accountManagementService;

   @RequestMapping(method = RequestMethod.GET)
   public List<Account> getAccounts(Model model) {

      final String username = getSessionUser().getUsername();

      final UserDetails userDetails = userRepositoryDaoImpl.findByUserUsername(username);

      final List<Account> accounts = accountRepositoryDaoImpl.findByOwner(userDetails);

      model.addAttribute(accounts);

      return accounts;
   }

   @RequestMapping(value = "/{name}", method = RequestMethod.GET)
   public String getAccount(@PathVariable("name") String accountName, Model model) {

      final Account account = accountManagementService.getAccount(getSessionUser().getUsername(),
            accountName);

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
         final UserDetails owner = userRepositoryDaoImpl.findByUserUsername(getSessionUser()
               .getUsername());
         account.setOwner(owner);
         accountRepositoryDaoImpl.save(account);
      }
      return "redirect:accounts";
   }

   public AccountManagementService getAccountManagementService() {

      return accountManagementService;
   }

   public void setAccountManagementService(AccountManagementService accountManagementService) {

      this.accountManagementService = accountManagementService;
   }

}
