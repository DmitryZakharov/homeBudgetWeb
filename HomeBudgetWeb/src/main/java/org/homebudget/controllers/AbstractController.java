/**
 *
 */
package org.homebudget.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author dza
 *
 */
public abstract class AbstractController {

   public User getSessionUser() {
      return (User) SecurityContextHolder.getContext().getAuthentication()
          .getPrincipal();
   }

   @InitBinder
   protected void initBinder(WebDataBinder binder) {

      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
   }

}
