/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.homebudget.test.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/config/homebudget-servlet.xml",
        "classpath:/config/datasource-config.xml", "classpath:/config/persistence-config.xml",
        "classpath:/config/homebudget-mail.xml" })
public class TestConfigurator {

    public TestConfigurator() {

    }

    /**
     * Stub test to avoid no runnable methods
     */
    @Test
    public void test() {

    }

}