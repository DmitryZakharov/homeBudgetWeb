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

      Category category = categoryRepository.findByNameAndOwnerMetadata(name, owner.getMetadata());

      if (category == null) {

         category = new Category();

         category.setName(name);

         category.setParent(parent);

         category.setOwnerMetadata(owner.getMetadata());

         return categoryRepository.save(category);

      }
      else
         return null;
   }

   public List<Category> getAllCategories(String username) {

      UserDetails owner = userManagementService.getUserDetailsByUsername(username);

      return categoryRepository.findByOwnerMetadata(owner.getMetadata());

   }

   public Category getCategoryByName(String name, String username) {

      final UserDetails owner = userManagementService.getUserDetailsByUsername(username);

      return categoryRepository.findByNameAndOwnerMetadata(name, owner.getMetadata());
   }

   public void saveCategory(Category category, String username) {

      UserDetails owner = userManagementService.getUserDetailsByUsername(username);
      category.setOwnerMetadata(owner.getMetadata());
      owner.getMetadata().getCategories().add(category);
      userManagementService.saveUserDetails(owner);
   }

   public void delete(String categoryName, String username) {

      final UserDetails owner = userManagementService.getUserDetailsByUsername(username);

      Category category = categoryRepository.findByNameAndOwnerMetadata(categoryName, owner.getMetadata());

      owner.getMetadata().getCategories().remove(category);

      userManagementService.saveUserDetails(owner);

   }

}