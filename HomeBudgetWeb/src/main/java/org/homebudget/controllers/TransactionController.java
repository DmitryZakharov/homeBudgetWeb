package org.homebudget.controllers;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.homebudget.model.Transaction;
import org.homebudget.services.TransactionManagementService;
import org.homebudget.services.TransactionValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/transactions")
public class TransactionController extends AbstractController {

   @Autowired
   private TransactionValidationService transactionValidation;

   @Resource
   private TransactionManagementService transactionManagementService;

   @RequestMapping(method = RequestMethod.GET)
   public List<Transaction> getAllTransactions(Model model) {

      final List<Transaction> transactions = transactionManagementService.getAllTransactions();

      model.addAttribute(transactions);

      return transactions;
   }
   
   
   @RequestMapping(value = "/{account.name}", method = RequestMethod.GET)
   public List<Transaction> getTransactions(@PathVariable("account.name") String accountName, Model model) {

      final List<Transaction> transactions = transactionManagementService.getAllAccountTransactions(
          accountName);

      model.addAttribute(transactions);

      return transactions;
   }

//   @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//   public String getTransaction(@PathVariable("name") String transactionName, Model model) {
//
//      final Transaction transaction = transactionManagementService.getTransaction(transactionName,
//          getSessionUser()
//          .getUsername());
//
//      model.addAttribute(transaction);
//      return "transaction";
//   }

   @RequestMapping(value = "/new", method = RequestMethod.GET)
   public String getTransaction(Model model) {

      model.addAttribute(new Transaction());
      return "transaction";
   }

   @RequestMapping(method = RequestMethod.POST)
   public String postTransaction(@ModelAttribute("transaction") @Valid Transaction transaction,
       BindingResult result) {

      transactionValidation.validate(transaction, result, getSessionUser().getUsername());
      if (result.hasErrors()) {
         return "forward:new";
      }
      if (transaction != null) {

         transactionManagementService.saveTransaction(transaction, getSessionUser().getUsername());
      }
      return "redirect:transactions";
   }

   public TransactionManagementService getTransactionManagementService() {

      return transactionManagementService;
   }

   public void setTransactionManagementService(
       TransactionManagementService transactionManagementService) {

      this.transactionManagementService = transactionManagementService;
   }

   public TransactionValidationService getTransactionValidation() {
      return transactionValidation;
   }

   public void setTransactionValidation(TransactionValidationService transactionValidation) {
      this.transactionValidation = transactionValidation;
   }

}
