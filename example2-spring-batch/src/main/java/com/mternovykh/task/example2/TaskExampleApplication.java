package com.mternovykh.task.example2;

import org.h2.tools.Server;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.SimpleTaskAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@EnableBatchProcessing
@SpringBootApplication(exclude = {SimpleTaskAutoConfiguration.class})
public class TaskExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskExampleApplication.class, args);
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

}
