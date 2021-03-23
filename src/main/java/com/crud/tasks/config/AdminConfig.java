package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {

    // @Value("mtokarski154@gmail.com")
    @Value("${admin.mail}")
    private String adminMail;

    // @Value("Michal_admin")
    @Value("${admin.name}")
    private String adminName;

    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.goal}")
    private String companyGoal;

    @Value("${info.company.email}")
    private String companyEmail;

    @Value("${info.company.phone}")
    private String companyPhone;

}
