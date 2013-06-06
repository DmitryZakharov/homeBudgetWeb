package org.homebudget.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.homebudget.dao.CategoryRepository;
import org.homebudget.model.Category;
import org.homebudget.model.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CategoryManagementService {

   private static final Logger gLogger = Logger.getLogger(CategoryManagementService.class);

   @Resource
   private CategoryRepository categoryRepository;

   @Resource
   private UserManagementService userManagementService;

   public Category createCategory(String name, Category parent, String username) {

      final UserDetails owner = userManagementService.getUserDetailsByUsername(username);

      Category category = categoryRepository.findByNameAndOwner(name, owner);

      if (category == null) {

         category = new Category();

         category.setName(name);

         category.setParent(parent);

         category.setOwner(owner);

         categoryRepository.save(category);

         return category;

      }
      else
         return null;
   }

   public List<Category> getAllCategories(String username) {

      UserDetails owner = userManagementService.getUserDetailsByUsername(username);

      return categoryRepository.findByOwner(owner);

   }

   public Category getCategoryByName(String name, String username) {

      final UserDetails owner = userManagementService.getUserDetailsByUsername(username);

      return categoryRepository.findByNameAndOwner(name, owner);
   }

   public void saveCategory(Category category, String username) {

      UserDetails owner = userManagementService.getUserDetailsByUsername(username);
      category.setOwner(owner);
      owner.getMetadata().getCategories().add(category);
      userManagementService.saveUserDetails(owner);
   }

   public void delete(String categoryName, String username) {

      final UserDetails owner = userManagementService.getUserDetailsByUsername(username);

      Category category = categoryRepository.findByNameAndOwner(categoryName, owner);

      owner.getMetadata().getCategories().remove(category);

      userManagementService.saveUserDetails(owner);

   }

}