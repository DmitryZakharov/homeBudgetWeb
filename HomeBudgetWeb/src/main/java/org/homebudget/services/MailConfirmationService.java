package org.homebudget.services;

import org.homebudget.model.UserDetails;
import org.slf4j.LoggerFactory;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailConfirmationService {

    private MailSender mailSender;
    private SimpleMailMessage message;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(MailConfirmationService.class);

    public MailConfirmationService(){
        this.mailSender = new JavaMailSenderImpl();
        this.message = new SimpleMailMessage();
    }
    
   
    public void sendConfirmation(UserDetails userDetails) {

        SimpleMailMessage msg = new SimpleMailMessage(this.message);

        msg.setTo(userDetails.getEmail());
        msg.setSubject("Homebudget registration. The last step");
        msg.setText(
                "Dear "
                + userDetails.getUserName() + " "
                + userDetails.getUserSurname()
                + ",\n Thank you for your registration at homebudget service.\n"
                + "In order to use all our features we need to confirm your mail.\n"
                + "Please click the link below to complete your registration.\n"
                + "<<Very pretty link>>");
        try {
            mailSender.send(msg);
            logger.info("Confirmation E-Mail was successfully sent to :" + userDetails.getEmail());

        } catch (MailException ex) {
            logger.error("Message sending to email:" + userDetails.getEmail() + " is failed!\n" + ex.getMessage());
        }
    }

    public MailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public SimpleMailMessage getMessage() {
        return message;
    }

    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }
    
    
}