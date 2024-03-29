package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;


    public void send(final Mail mail) {

        LOGGER.info("Starting email preparation ...");

        try {
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed  to process email sending: [{}] ", e.getMessage(), e);
        }

    }


    private MimeMessagePreparator createMimeMessage(final Mail mail) {

        return mimeMessage -> {

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());

            switch (mail.getMailTemplate()) {
                case MAIL_TEMPLATE__CREATED_TRELLO_CARD_MAIL :
                    messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
                    break;
                case MAIL_TEMPLATE__NUMBER_OF_TASKS_IN_REPOSITORY_MAIL :
                    messageHelper.setText(mailCreatorService.buildNumberOfTasksInRepositoryEmail(mail.getMessage()), true);
                    break;
            }

        };

    }

    // method replaced by createMimeMessage(final Mail mail)
    private SimpleMailMessage createMailMessage (final Mail mail){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        // mailMessage.setText(mail.getMessage()); // old line
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage())); // new line
        if (mail.getMailCc()!=null && !mail.getMailCc().equals("")) {
            mailMessage.setCc(mail.getMailCc());
        }
        return mailMessage;

    }

}

