package org.homebudget.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.homebudget.model.UserDetails;
import org.homebudget.services.UserManagementService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/user")
public class UserManagementController extends AbstractController {

   private static final Logger gLogger = Logger.getLogger(UserManagementController.class);

   @Resource
   private UserManagementService userManagementService;

   @RequestMapping(method = RequestMethod.GET)
   public String showUserProfile(Model model) {

      UserDetails userDetails = userManagementService.getUserDetailsByUsername(getSessionUser()
          .getUsername());
      String userPicString = userManagementService.getUserPicString(userDetails.getUserpic());
      model.addAttribute(userDetails);
      model.addAttribute("userpic", userPicString);
      return "user/editUser";
   }

   @RequestMapping(method = RequestMethod.PUT)
//   @ResponseStatus(HttpStatus.NO_CONTENT)
   public String updateUserDetails(@Valid UserDetails newUserDetails, BindingResult result,
       Model model, @RequestParam(value = "file", required = false) MultipartFile userpic) {

      UserDetails oldUserDetails = userManagementService.getUserDetailsByUsername(getSessionUser()
          .getUsername());

      oldUserDetails = getUserManagementService().updateUserDetails(oldUserDetails, newUserDetails,
          userpic);
      String userPicString = userManagementService.getUserPicString(oldUserDetails.getUserpic());
      model.addAttribute(oldUserDetails);
      model.addAttribute("userpic", userPicString);
      return "redirect:user";
   }

   @Secured("ADMIN_ROLE")
   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public String addUserDetails(
       @RequestParam(value = "userpic", required = false) MultipartFile userPic,
       @Valid UserDetails userDetails, BindingResult result, Model model) {

      userDetails = getUserManagementService().saveUserDetails(userDetails, userPic);

      model.addAttribute(userDetails);
      return "user/editUser";
   }

   @Secured("ADMIN_ROLE")
   @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public String deleteUserDetails(@PathVariable("username") String username) {

      final UserDetails userDetails = userManagementService.getUserDetailsByUsername(username);
      getUserManagementService().deleteUserDetails(userDetails);

      return "";
   }

   @Secured("ADMIN_ROLE")
   @RequestMapping()
   public String showAllUsers(Model model) {

      final List<UserDetails> users = userManagementService.getAllUsers();
      model.addAttribute(users);
      return "user/listUsers";
   }

   public UserManagementService getUserManagementService() {

      return userManagementService;
   }

   public void setUserManagementService(UserManagementService userManagementService) {

      this.userManagementService = userManagementService;
   }

   @InitBinder
   public void initBinder(WebDataBinder binder) {

      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
   }

}
