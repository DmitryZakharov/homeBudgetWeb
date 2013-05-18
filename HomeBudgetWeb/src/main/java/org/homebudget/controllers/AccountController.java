package org.homebudget.controllers;

import info.joseluismartin.dao.Page;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.homebudget.dao.AccountRepository;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.Account;
import org.homebudget.model.UserDetails;
import org.homebudget.services.NewAccountValidation;
import org.homebudget.springmvc.PaginatedListAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private NewAccountValidation aNewAccountValidation;
    @Resource
    private AccountRepository accountRepositoryDaoImpl;
    @Resource
    private UserRepository userRepositoryDaoImpl;

    @RequestMapping(method = RequestMethod.GET)
    public String showAccounts(@ModelAttribute("paginatedList") PaginatedListAdapter paginatedList, ModelMap model) {

        final User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        
       final Page<Account> page = (Page<Account>) paginatedList.getModel();

        final String username = user.getUsername();
        UserDetails userDetails = userRepositoryDaoImpl.findByUserUsername(username);

        Long userId = userDetails
                .getUserId();

        List<Account> accounts = accountRepositoryDaoImpl.findByOwner(userDetails);

        model.addAttribute("accounts", accounts);

        return "account";

    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    public String showContacts(Map<String, Object> model) {
        Account account = new Account();
        model.put("account", account);
        return "createAccount";
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public ModelAndView addAccount(
            @ModelAttribute("account") @Valid Account account,
            BindingResult result) {

        final User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        aNewAccountValidation.validate(account, result);
        if (result.hasErrors()) {
            return new ModelAndView("createAccount");
        }

        if (account != null) {
            final String username = user.getUsername();
            final UserDetails owner = userRepositoryDaoImpl
                    .findByUserUsername(username);
            account.setOwner(owner);
            accountRepositoryDaoImpl.save(account);
        }
        return new ModelAndView("redirect:../account.html");
    }
}
