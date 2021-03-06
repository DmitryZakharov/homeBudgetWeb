package org.homebudget.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.homebudget.model.Category;
import org.homebudget.model.UserDetails;
import org.homebudget.services.CategoryEditor;
import org.homebudget.services.CategoryManagementService;
import org.homebudget.services.UserManagementService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/categories")
public class CategoryController extends AbstractController {

   @Resource
   private CategoryManagementService categoryManagementService;

   @Resource
   private UserManagementService userManagementService;

   @RequestMapping(method = RequestMethod.GET)
   public String getAllUserCategories(Model model) {

      final List<Category> accounts = categoryManagementService.getAllCategories(getSessionUser()
            .getUsername());

      model.addAttribute(accounts);

      return "category/listCategories";
   }

   @RequestMapping(value = "/{name}", method = RequestMethod.GET)
   public String getTransaction(@PathVariable("name") String categoryname, Model model) {

      final Category category = categoryManagementService.getCategoryByName(categoryname,
            getSessionUser().getUsername());

      model.addAttribute(category);
      return "category/editCategory";
   }

   @RequestMapping(value = "/new", method = RequestMethod.GET)
   public String createTransaction(Model model) {

      final List<Category> categoryList = categoryManagementService
            .getAllCategories(getSessionUser().getUsername());

      model.addAttribute(categoryList);
      model.addAttribute(new Category());
      return "category/newCategory";
   }

   @RequestMapping(value = "/new", method = RequestMethod.POST)
   public String postCategory(@ModelAttribute("category") @Valid Category category,
         BindingResult result, Model model) {

      final UserDetails userDetails = userManagementService
            .getUserDetailsByUsername(getSessionUser().getUsername());
      userDetails.getMetadata().addCategory(category);
      userManagementService.saveUserDetails(userDetails);
      return "redirect:";
   }

   @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
   public String deleteCategory(@PathVariable("name") String name) {

      categoryManagementService.delete(name, getSessionUser().getUsername());

      return "redirect:";
   }
   
   @InitBinder
   public void initBinder(WebDataBinder binder) {
      
      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

      binder.registerCustomEditor(Category.class, new CategoryEditor(categoryManagementService,
            getSessionUser().getUsername()));
   }

}
