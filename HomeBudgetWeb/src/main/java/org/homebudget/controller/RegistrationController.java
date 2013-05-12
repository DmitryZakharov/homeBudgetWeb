package org.homebudget.controller;

import java.util.Date;
import javax.annotation.Resource;
import org.homebudget.dao.HibernateDaoImpl;
import org.homebudget.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private HibernateDaoImpl service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected ModelAndView register(Object command) throws Exception {

        UserDetails aUserDetails = (UserDetails) command;
        logger.info("Register user with id: " + aUserDetails.getUserId());
        service.addUser(aUserDetails);
        ModelAndView model = new ModelAndView("redirect:/greeting");

        return model;
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
}
