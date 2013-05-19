package org.homebudget.controllers;

import java.util.Map;
import javax.annotation.Resource;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.services.RegistrationValidation;
import org.homebudget.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger gLogger = Logger.getLogger(RegistrationController.class);
    @Autowired
    private RegistrationValidation aRegistrationValidation;

    @Resource
    private UserManagementService userManagementService;

    @RequestMapping(method = RequestMethod.GET)
    protected String showRegistration(Map<String, Object> model)
            throws Exception {
        UserDetails aUserDetails = new UserDetails();
        UserRole aUserRole = new UserRole();
        aUserRole.setUserRole(UserRole.Role.USER_ROLE);
        model.put("userDetails", aUserDetails);
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@Valid UserDetails aUserDetails,
            BindingResult result) {
        aRegistrationValidation.validate(aUserDetails, result);
        if (result.hasErrors()) {
            return "registration";
        }
        userManagementService.registerUser(aUserDetails);
        return "registrationsuccess";

    }

  

   
}
