package org.homebudget.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;

import javax.validation.Valid;

import org.homebudget.dao.UserRepository;
import org.homebudget.model.UserDetails;
import org.homebudget.model.UserRole;
import org.homebudget.services.PasswordService;
import org.homebudget.services.RegistrationValidation;
import org.homebudget.utils.HomeBudgetInitializationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private RegistrationValidation aRegistrationValidation;
    @Resource
    private UserRepository userRepositoryDao;

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
        String dateString = aUserDetails.getDateString();
        Date birthday = getBirthdayFromString(dateString);
        aUserDetails.setUserBirthday(birthday);
        final String userPassword = aUserDetails.getPassword();
        try {
            String passwordHash = PasswordService.getHash(aUserDetails
                    .getPassword());
            aUserDetails.setPassword(passwordHash);
        } catch (Exception e) {// with is a hack. Must be removed. If hashing
            // fails, user must be notified.
            aUserDetails.setPassword(userPassword);
        }
        aUserDetails.addUserRole(UserRole.Role.USER_ROLE);
        //TODO: set to 0, when email confirmation is implemented
        aUserDetails.setEnabled(1);
        userRepositoryDao.save(aUserDetails);
        return "registrationsuccess";

    }

    private Date getBirthdayFromString(String dateString) {
        // password is replaced with hash after validation of the form.
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("DateString read: " + dateString);
        logger.info("DateString read: " + dateString);
        Date birthday = null;
        try {
            birthday = format.parse(dateString);
        } catch (ParseException ex) {
            System.err.println("Datestring could not be parsed " + dateString);
        }
        return birthday;
    }
}
