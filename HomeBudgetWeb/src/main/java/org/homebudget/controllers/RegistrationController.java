package org.homebudget.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.homebudget.model.Currency;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole.Role;
import org.homebudget.services.UserManagementService;
import org.homebudget.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userDetails")
@RequestMapping("/registration")
public class RegistrationController extends AbstractController {

   private static final Logger gLogger = Logger.getLogger(RegistrationController.class);

   @Autowired
   private UserValidationService userValidationService;

   @Resource
   private UserManagementService userManagementService;

   @RequestMapping(method = RequestMethod.GET)
   public String showRegistration(Model model) {

      final List<Currency> currencyList = new ArrayList<Currency>(Arrays.asList(Currency.values()));

      model.addAttribute(userManagementService.getNewUser(Role.USER_ROLE));
      model.addAttribute(currencyList);
      return "registration";
   }

   @RequestMapping(value = "/success", method = RequestMethod.GET)
   public String successfulRegistration(Model model) {

      return "registrationsuccess";
   }

   @RequestMapping(method = RequestMethod.POST)
   // @ResponseStatus(HttpStatus.CREATED)
   public String registerUser(@ModelAttribute("userDetails") @Valid UserDetails userDetails,
         BindingResult result, HttpServletResponse response, Model model) {

      userValidationService.validate(userDetails, result, "");
      if (result.hasErrors()) {
         return "registration";
      }

      userManagementService.registerUser(userDetails);
      return "redirect:registration/success";

   }
}
