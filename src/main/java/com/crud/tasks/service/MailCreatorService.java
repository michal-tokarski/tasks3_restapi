package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private TrelloClient trelloClient;


    MailTemplate templateFlag;

    public String buildTrelloCardEmail(String message) {

        // New object :
        Context context = new Context();

        // Steering variable :
        templateFlag = MailTemplate.MAIL_TEMPLATE__CREATED_TRELLO_CARD_MAIL;

        // Basic fields :
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Thank you for using our services.");

        // Company details :
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());

        // Functionalities list :
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        // Extra fields :
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

        // Process :
        // return templateEngine.process("mail/created-trello-card-mail", context);
        return templateEngine.process(templateFlag.getTemplateName(), context);

    }

    public String buildNumberOfTasksInRepositoryEmail(String message) {

        // New object :
        Context context = new Context();

        // Steering variable :
        templateFlag = MailTemplate.MAIL_TEMPLATE__NUMBER_OF_TASKS_IN_REPOSITORY_MAIL;

        // Basic fields :
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost/tasks_frontend");
        context.setVariable("button", "See the tasks");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Thank you for using our services.");

        // Company details :
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("company_email", adminConfig.getCompanyEmail());
        context.setVariable("company_phone", adminConfig.getCompanyPhone());

        // Functionalities list :
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        // Extra fields :
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

        // Process :
        // return templateEngine.process("mail/number-of-tasks-in-repository-mail", context);
        return templateEngine.process(templateFlag.getTemplateName(), context);

    }


}
