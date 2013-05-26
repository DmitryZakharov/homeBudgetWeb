/**
 * 
 */
package org.homebudget.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


/**
 * @author dza
 *
 */
public abstract class AbstractController {

   
   public User getSessionUser(){
      return  (User) SecurityContextHolder.getContext().getAuthentication()
            .getPrincipal();
   }

}
