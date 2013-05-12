package org.homebudget.controller;

import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.homebudget.dao.HibernateDaoImpl;
import org.homebudget.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private ReistrationValidation aReistrationValidation;
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private HibernateDaoImpl service;

    @RequestMapping(method = RequestMethod.GET)
    protected String showRegistration(Map model) throws Exception {
        UserDetails aUserDetails = new UserDetails();
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

    @RequestMapping(value = "/user/{userId}")
    public ModelAndView userDetails(@PathVariable Long userId) {
        UserDetails aUserDetails = service.getUser(userId);
        ModelAndView model = new ModelAndView();
        model.addObject("uname", aUserDetails.getUserName());
        model.addObject("fname", aUserDetails.getUserSurname());
        model.addObject("bthday", aUserDetails.getUserBirthday());
        return model;
    }
    
    public void setReistrationValidation(ReistrationValidation aReistrationValidation){
        this.aReistrationValidation = aReistrationValidation;
    }
}
