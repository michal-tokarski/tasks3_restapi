package com.crud.tasks.service;

public enum MailTemplate {

    MAIL_TEMPLATE__CREATED_TRELLO_CARD_MAIL (
        "mail/created-trello-card-mail",
        "created-trello-card-mail.html"),

    MAIL_TEMPLATE__NUMBER_OF_TASKS_IN_REPOSITORY_MAIL(
        "mail/number-of-tasks-in-repository-mail",
        "number-of-tasks-in-repository-mail.html");

    private final String templateName;
    private final String fileName;

    MailTemplate(String templateName, String fileName) {
        this.templateName = templateName;
        this.fileName = fileName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getFileName() {
        return fileName;
    }
}
