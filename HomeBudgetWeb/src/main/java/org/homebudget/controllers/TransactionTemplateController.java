package org.homebudget.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.homebudget.model.Category;
import org.homebudget.model.Transaction;
import org.homebudget.model.TransactionTemplate;
import org.homebudget.services.CategoryEditor;
import org.homebudget.services.CategoryManagementService;
import org.homebudget.services.TransactionTemplateManagementService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class TransactionTemplateController extends AbstractController {

   private static final Logger logger = Logger.getLogger(TransactionTemplateController.class);

   @Resource
   private TransactionTemplateManagementService transactionTemplateManagementService;

   @Resource
   private CategoryManagementService categoryManagementService;


   @RequestMapping(value = "transactionTemplates", method = RequestMethod.GET)
   public String getAllTransactionTemplates(Model model) {
      List<TransactionTemplate> transactionTemplates = transactionTemplateManagementService.
          getAllTransactionTemplates(getSessionUser().getUsername());
      model.addAttribute(transactionTemplates);
      return "transactionTemplate/listTransactionTemplates";
   }

   @RequestMapping(value = "transactionTemplates/{id}", method = RequestMethod.GET)
   public String getTransactionTemplate(@PathVariable("id") Long transactionTemplateId, Model model) {

      TransactionTemplate transactionTemplate = transactionTemplateManagementService.
          getTransactionTemplate(getSessionUser().getUsername(), transactionTemplateId);
      if (transactionTemplate == null) {
         return "redirect:";
      }
      model.addAttribute(transactionTemplate);
      return "transactionTemplate/editTransactionTemplate";
   }

   @RequestMapping(value = "transactionTemplates/new", method = RequestMethod.GET)
   public String createTransactionTemplate(Model model) {
      final List<Category> categories = categoryManagementService.getAllCategories(getSessionUser()
          .getUsername());
      final List<Transaction.TransactionType> transactionTypeList = new ArrayList<Transaction.TransactionType>(
            Arrays.asList(Transaction.TransactionType.values()));
      model.addAttribute(categories);
      model.addAttribute(transactionTypeList);
      model.addAttribute(new TransactionTemplate());
      return "transactionTemplate/newTransactionTemplate";
   }

   @RequestMapping(value = "transactionTemplates/{id}", method = RequestMethod.DELETE)
   public String deleteTransactionTemplate(
       @PathVariable("id") Long transactionTemplateId) {

      transactionTemplateManagementService.
          deleteTransactionTemplate(getSessionUser().getUsername(), transactionTemplateId);

      return "redirect:";
   }

   @RequestMapping(value = "transactionTemplates/new", method = RequestMethod.POST)
   public String postTransactionTemplate(
       @ModelAttribute("transactionTemplate") @Valid TransactionTemplate transactionTemplate,
       BindingResult result) {

      logger.info("Processing save transacton template: " + transactionTemplate);
     
      transactionTemplateManagementService.saveTransactionTemplate(getSessionUser().getUsername(), transactionTemplate);

      return "redirect:";
   }

   @RequestMapping(value = "transactionTemplates", method = RequestMethod.PUT)
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public String updateTransactionTemplateDetails(TransactionTemplate transactionTemplate,
       BindingResult result,
       Model model) {

      TransactionTemplate oldTransactionTemplate = transactionTemplateManagementService.
          getTransactionTemplate(getSessionUser().getUsername(), transactionTemplate.getId());

      if (oldTransactionTemplate == null) {
         return "redirect:transactionTemplate/listTransactionTemplates";
      }
      transactionTemplateManagementService
          .updateTransactionTemplateDetails(oldTransactionTemplate, transactionTemplate);

      return "redirect:transactionTemplate/listTransactionTemplates";

   }

   public TransactionTemplateManagementService getTransactionTemplateManagementService() {

      return transactionTemplateManagementService;
   }

   public void setTransactionTemplateManagementService(
       TransactionTemplateManagementService transactionTemplateManagementService) {

      this.transactionTemplateManagementService = transactionTemplateManagementService;
   }

  

   @InitBinder
   public void initBinder(WebDataBinder binder) {

      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

      binder.registerCustomEditor(Category.class, new CategoryEditor(categoryManagementService,
          getSessionUser().getUsername()));
   }

}
