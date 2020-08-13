package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// for Tomcat
/*
@SpringBootApplication
public class TasksApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TasksApplication.class);
	}

}
*/

// for Heroku
@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

}