package com.mternovykh.task.example1;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;


@EnableTask
@SpringBootApplication
public class TaskExampleApplication {

    public static void main(String[] args) {
        System.out.println("main started!");
        SpringApplication.run(TaskExampleApplication.class, args);
        System.out.println("main finished!");
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

}
