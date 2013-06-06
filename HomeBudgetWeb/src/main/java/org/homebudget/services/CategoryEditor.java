/**
 * 
 */
package org.homebudget.services;

import java.beans.PropertyEditorSupport;

import org.homebudget.model.Category;

/**
 * @author dza
 * 
 */
public class CategoryEditor extends PropertyEditorSupport {

   private final CategoryManagementService categoryManagementService;
   
   private final String username;

   /**
    * 
    */
   public CategoryEditor(CategoryManagementService categoryManagementService, String username) {

      this.categoryManagementService = categoryManagementService;
      
      this.username = username;
   }
   
   /* (non-Javadoc)
    * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
    */
   @Override
   public void setAsText(String text) throws IllegalArgumentException {
   
      Category category = categoryManagementService.getCategoryByName(text, username);
      setValue(category);
   }
}
