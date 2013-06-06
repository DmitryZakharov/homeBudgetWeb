package org.homebudget.controllers;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.homebudget.model.UserDetails;
import org.homebudget.services.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/user")
public class UserManagementController extends AbstractController {

   @Resource
   private UserManagementService userManagementService;

   @RequestMapping(method = RequestMethod.GET)
   public String showUserProfile(Model model) {

      UserDetails userDetails = userManagementService.getUserDetailsByUsername(getSessionUser()
            .getUsername());
      String userPicString = userManagementService.getUserPicString(userDetails.getUserPic());
      model.addAttribute(userDetails);
      model.addAttribute("userPic", userPicString);
      return "userprofile";
   }

   @RequestMapping(method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public String updateUserDetails(@Valid UserDetails newUserDetails, BindingResult result,
         Model model, @RequestParam(value = "userPic", required=false) MultipartFile userPic) {

      UserDetails oldUserDetails = userManagementService.getUserDetailsByUsername(getSessionUser()
            .getUsername());
      getUserManagementService().updateUserDetails(oldUserDetails, newUserDetails, userPic);
      return "userprofile";
   }

   @Secured("ADMIN_ROLE")
   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public UserDetails addUserDetails(@Valid UserDetails userDetails, BindingResult result,
         Model model, @RequestParam(value = "userPic", required=false) MultipartFile userPic) {

      getUserManagementService().saveUserDetails(userDetails, userPic);

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

   
   public UserManagementService getUserManagementService() {

      return userManagementService;
   }

   public void setUserManagementService(UserManagementService userManagementService) {

      this.userManagementService = userManagementService;
   }



}
