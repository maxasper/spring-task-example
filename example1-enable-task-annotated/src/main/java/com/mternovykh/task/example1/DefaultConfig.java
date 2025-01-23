package com.mternovykh.task.example1;

import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {
    @Bean
    ApplicationRunner applicationRunner() {
        return new Task1();
    }

    @Bean
    ApplicationRunner applicationRunner1() {
        return new Task2();
    }

    @Bean
    TaskExecutionListener defaultListener1() {
        return new DefaultListener1();
    }

    @Bean
    DefaultListener2 defaultListener2() {
        return new DefaultListener2();
    }
}
