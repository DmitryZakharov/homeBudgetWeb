package org.homebudget.controller;

import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.homebudget.dao.UserRepositoryDaoImpl;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private ReistrationValidation aReistrationValidation;
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserRepositoryDaoImpl service;

    @RequestMapping(method = RequestMethod.GET)
    protected String showRegistration(Map model) throws Exception {
        UserDetails aUserDetails = new UserDetails();
        UserRole aUserRole  = new UserRole();
        aUserRole.setAuthority("ROLE_USER");
        model.put("userDetails", aUserDetails);
        return "registration";
    }
    
    @RequestMapping(method = RequestMethod.POST)
        public String processRegistration(@Valid UserDetails aUserDetails,
                        BindingResult result) {

                aReistrationValidation.validate(aUserDetails, result);
                if (result.hasErrors()) {
                        return "registration";
                }
                service.addUser(aUserDetails);
                return "registrationsuccess";
        }

  
    
    public void setReistrationValidation(ReistrationValidation aReistrationValidation){
        this.aReistrationValidation = aReistrationValidation;
    }
}
