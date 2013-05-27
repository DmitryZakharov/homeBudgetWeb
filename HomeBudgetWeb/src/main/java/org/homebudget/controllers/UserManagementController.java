package org.homebudget.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.homebudget.model.UserDetails;
import org.homebudget.services.UserValidationService;
import org.homebudget.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/user")
public class UserManagementController extends AbstractController {

   @Resource
   private UserManagementService userManagementService;

   @Autowired
   private UserValidationService aRegistrationValidation;

   @RequestMapping(method = RequestMethod.GET)
   public String showUserProfile(Model model) {

      UserDetails userDetails = userManagementService.getUserDetailsByUsername(getSessionUser()
            .getUsername());
      model.addAttribute(userDetails);

      return "userprofile";
   }

   @RequestMapping(method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void updateUserDetails(@Valid UserDetails newUserDetails, BindingResult result,
         Model model) {

      UserDetails oldUserDetails = userManagementService.getUserDetailsByUsername(getSessionUser()
            .getUsername());
      getUserManagementService().updateUserDetails(oldUserDetails, newUserDetails);
   }

   @Secured("ADMIN_ROLE")
   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public UserDetails addUserDetails(@Valid UserDetails userDetails, BindingResult result,
         Model model) {

      getUserManagementService().saveUserDetails(userDetails);

      return userDetails;
   }

   @Secured("ADMIN_ROLE")
   @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public UserDetails deleteUserDetails(@PathVariable("username") String username) {

      final UserDetails userDetails = userManagementService.getUserDetailsByUsername(username);
      getUserManagementService().deleteUserDetails(userDetails);

      return userDetails;
   }

   @Secured("ADMIN_ROLE")
   @RequestMapping()
   public List<UserDetails> showAllUsers(Model model) {

      final List<UserDetails> users = userManagementService.getAllUsers();
      model.addAttribute(users);
      return users;
   }

   @InitBinder
   protected void initBinder(WebDataBinder binder) {

      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
   }

   public UserManagementService getUserManagementService() {

      return userManagementService;
   }

   public void setUserManagementService(UserManagementService userManagementService) {

      this.userManagementService = userManagementService;
   }

}
