package org.homebudget.services;

import java.util.List;
import javax.annotation.Resource;
import org.homebudget.dao.TransactionTemplateRepository;
import org.homebudget.dao.UserRepository;
import org.homebudget.model.TransactionTemplate;
import org.homebudget.model.UserMetadata;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TransactionTemplateManagementService {

   @Resource
   private TransactionTemplateRepository transactionTemplateRepository;

   @Resource
   private UserRepository userRepository;

   private void saveTransactionTemplate(
       UserMetadata userMetadata, TransactionTemplate transactionTemplate) {
      transactionTemplate.setOwnerMetadata(userMetadata);
      transactionTemplateRepository.save(transactionTemplate);
   }

   public void saveTransactionTemplate(String username, TransactionTemplate transactionTemplate) {
      UserMetadata userMetadata = getParentMetadata(username);
      saveTransactionTemplate(userMetadata, transactionTemplate);
   }

   private List<TransactionTemplate> getAllTransactionTemplates(UserMetadata userMetadata) {
      return transactionTemplateRepository.findByOwnerMetadata(userMetadata);
   }

   public List<TransactionTemplate> getAllTransactionTemplates(String username) {
      final UserMetadata userMetadata = getParentMetadata(username);
      return getAllTransactionTemplates(userMetadata);
   }

   public void deleteAll() {
      transactionTemplateRepository.deleteAll();
   }

   private TransactionTemplate getTransactionTemplate(Long transactionTemplateId) {
      return transactionTemplateRepository.findOne(transactionTemplateId);
   }

   public TransactionTemplate getTransactionTemplate(String username, Long transactionTemplateId) {
      UserMetadata userMetadata = getParentMetadata(username);
      TransactionTemplate result = getTransactionTemplate(transactionTemplateId);
      if (result.getOwnerMetadata().equals(userMetadata)) {
         return result;
      }
      return null;
   }

   private void deleteTransactionTemplate(Long transactionTemplateId) {
      transactionTemplateRepository.delete(transactionTemplateId);
   }

   public void deleteTransactionTemplate(String username, Long transactionTemplateId) {
      TransactionTemplate transactionTemplate = getTransactionTemplate(username,
          transactionTemplateId);
      if (transactionTemplate != null) {
         transactionTemplateRepository.delete(transactionTemplate);
      }
   }

   private void deleteTransactionTemplate(TransactionTemplate transactionTemplate) {
      transactionTemplateRepository.delete(transactionTemplate);
   }

   public void updateTransactionTemplateDetails(TransactionTemplate oldTransactionTemplate,
       TransactionTemplate newTransactionTemplate) {
      BeanUtils.copyProperties(newTransactionTemplate, oldTransactionTemplate, new String[]{"id",
         "ownerMetadata"});
      transactionTemplateRepository.save(oldTransactionTemplate);
   }

   private UserMetadata getParentMetadata(String username) {
      UserMetadata userMetadata = userRepository.findByUsername(username).getMetadata();
      return userMetadata;
   }

}