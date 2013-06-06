package org.homebudget.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.homebudget.model.Category;
import org.homebudget.services.CategoryEditor;
import org.homebudget.services.CategoryManagementService;
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

   @RequestMapping(method = RequestMethod.GET)
   public String getAllUserCategories(Model model) {

      final List<Category> accounts = categoryManagementService.getAllCategories(getSessionUser()
            .getUsername());

      model.addAttribute(accounts);

      return "categories";
   }

   @RequestMapping(value = "/{name}", method = RequestMethod.GET)
   public String getTransaction(@PathVariable("name") String categoryname, Model model) {

      final Category category = categoryManagementService.getCategoryByName(categoryname,
            getSessionUser().getUsername());

      model.addAttribute(category);
      return "categoryDetails";
   }

   @RequestMapping(value = "/new", method = RequestMethod.GET)
   public String createTransaction(Model model) {

      final List<Category> categoryList = categoryManagementService
            .getAllCategories(getSessionUser().getUsername());

      model.addAttribute(categoryList);
      model.addAttribute(new Category());
      return "category";
   }

   @RequestMapping(value = "/new", method = RequestMethod.POST)
   public String postCategory(@ModelAttribute("category") @Valid Category category,
         BindingResult result, Model model) {

      categoryManagementService.saveCategory(category, getSessionUser().getUsername());

      return "redirect:";
   }

   @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
   public String deleteCategory(@PathVariable("name") String name) {

      categoryManagementService.delete(name, getSessionUser().getUsername());

      return "redirect:";
   }

   @InitBinder
   protected void initBinder(WebDataBinder binder) {

      binder.registerCustomEditor(Category.class, new CategoryEditor(categoryManagementService,
            getSessionUser().getUsername()));
   }

}
