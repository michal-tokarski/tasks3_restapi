package com.crud.tasks.domain;

import com.crud.tasks.service.MailTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {

    private String mailTo;
    private String mailCc;
    private String subject;
    private String message;
    private MailTemplate mailTemplate;

    public Mail(String mailTo, String subject, String message) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }

    public Mail(String mailTo, String subject, String message, MailTemplate mailTemplate) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
        this.mailTemplate = mailTemplate;
    }

}
