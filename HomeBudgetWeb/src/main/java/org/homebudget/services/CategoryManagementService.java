package org.homebudget.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.homebudget.dao.CategoryRepository;
import org.homebudget.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryManagementService {

   private static final Logger gLogger = Logger.getLogger(CategoryManagementService.class);

   @Resource
   private CategoryRepository categoryRepository;

   public Category createCategory(String name, Category parent) {

      Category category = categoryRepository.findByName(name);

      if (category == null) {

         category = new Category();

         category.setParent(parent);
         
         categoryRepository.save(category);
         
         return category;

      }else 
         return null;
   }
   
   public List<Category> getAllCategories(){
      
    return  categoryRepository.findAll();
      
   }

}