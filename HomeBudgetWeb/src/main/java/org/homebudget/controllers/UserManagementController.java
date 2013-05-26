package org.homebudget.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.homebudget.dao.UserRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.services.RegistrationValidation;
import org.homebudget.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
public class UserManagementController {

   @Resource
   private UserRepository userRepositoryDao;

   @Resource
   private UserManagementService userManagementService;

   @Autowired
   private RegistrationValidation aRegistrationValidation;

   @RequestMapping(method = RequestMethod.GET)
   public String showUserProfile(Model model) {

      final User user = (User) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
      UserDetails userDetails = userRepositoryDao.findByUserUsername(user.getUsername());
      model.addAttribute(userDetails);

      return "userprofile";
   }

   @RequestMapping(method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void updateUserDetails(@Valid UserDetails newUserDetails, BindingResult result,
         Model model) {

      final User user = (User) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
      UserDetails oldUserDetails = userManagementService.getUserByUsername(user.getUsername());
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

      final UserDetails userDetails = userManagementService.getUserByUsername(username);
      getUserManagementService().deleteUserDetails(userDetails);

      return userDetails;
   }

   @Secured("ADMIN_ROLE")
   @RequestMapping()
   public List<UserDetails> showAllUsers(Model model) {

      final List<UserDetails> users = getHibernateDaoImpl().findAll();
      // model.addAttribute(users);
      return users;
   }

   @InitBinder
   protected void initBinder(WebDataBinder binder) {

      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
   }

   public UserRepository getHibernateDaoImpl() {

      return userRepositoryDao;
   }

   public void setHibernateDaoImpl(UserRepository hibernateDaoImpl) {

      this.userRepositoryDao = hibernateDaoImpl;
   }

   public UserManagementService getUserManagementService() {

      return userManagementService;
   }

   public void setUserManagementService(UserManagementService userManagementService) {

      this.userManagementService = userManagementService;
   }

}
