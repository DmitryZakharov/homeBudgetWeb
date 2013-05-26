package org.homebudget.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends AbstractController {

   @RequestMapping(value = "/main", method = RequestMethod.GET)
   public String printWelcome(ModelMap model) {

      return "main";

   }

   @RequestMapping(value = "/welcome", method = RequestMethod.GET)
   public String login(ModelMap model) {

      return "welcome";

   }

   @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
   public String loginerror(ModelMap model) {

      model.addAttribute("error", "true");
      return "welcome";

   }

   @RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(ModelMap model) {

      return "forward:/welcome.html";
   }

}