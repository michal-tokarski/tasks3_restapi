package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailTemplate;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    // @Scheduled(cron = "0 0 10 * * *") // everyday at 10:00 am
    // @Scheduled(fixedDelay = 10000) // with delay of 10k milisecs = 10 secs
    // @Scheduled(cron = "*/20 * * * * *") // every 20 seconds
    public void sendInformationEmail() {

        long size = taskRepository.count();
        String taskSuffix = size == 1 ? " tasK" : " taskS";
        String message = "Currently in database you got : " + size + taskSuffix;

        simpleEmailService.send(
            new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message,
                MailTemplate.MAIL_TEMPLATE__NUMBER_OF_TASKS_IN_REPOSITORY_MAIL
            )
        );

    }

}



