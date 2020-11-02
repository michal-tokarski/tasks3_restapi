package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
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

    @Scheduled(cron = "0 0 10 * * *") // action done everyday at 10:00 am
    // @Scheduled(fixedDelay = 10000) // action done with delay of 10k milisecs = 10 secs
    public void sendInformationEmail() {

        long size = taskRepository.count();
        String taskSuffix = size == 1 ? " tasK" : " taskS";
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently in database you got : " + size + taskSuffix
                )
        );

    }

}

