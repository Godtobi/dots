package com.ideamasn.transita;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideamasn.transita.dao.BaseRepositoryImpl;
import com.ideamasn.transita.service.UserAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class,basePackages = "com.ideamasn.transita.dao")
@EnableScheduling
public class Application {


    private static UserAccessLogService userAccessLogService;

    @Autowired
    public void setDemo(UserAccessLogService d) {
        userAccessLogService = d;
    }

    public static void main(String[] args)  {

        SpringApplication.run(Application.class, args);
        userAccessLogService.run();
    }

}
