/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.homebudget.model.BinaryResource;
import org.homebudget.services.ResourceManagementService;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/config/homebudget-servlet.xml",
      "classpath:/config/datasource-config.xml", "classpath:/config/persistence-config.xml",
      "classpath:/config/homebudget-mail.xml" })
public abstract class TestConfigurator {

   @Autowired
   ApplicationContext applicationContext;

   @Autowired
   ResourceManagementService aResourceManagementService;

   public TestConfigurator() {

   }

   protected BinaryResource createTestDocument() throws BeansException, IOException {

      MultipartFile multipartFile = mock(MultipartFile.class);

      Resource resource = aResourceManagementService.getResource("classpath:docs/foto2.jpg");
      byte[] bytes = aResourceManagementService.getBytesFromResource(resource);

      when(multipartFile.getName()).thenReturn(resource.getFilename());
      when(multipartFile.getOriginalFilename()).thenReturn(resource.getFile().getPath());
      when(multipartFile.getInputStream()).thenReturn(resource.getInputStream());
      when(multipartFile.getBytes()).thenReturn(bytes);
      BinaryResource document = aResourceManagementService.getResource(multipartFile);
      return document;
   }
}