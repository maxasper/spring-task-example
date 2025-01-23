package com.mternovykh.task.example1;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class Task2 implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            System.out.println("Task 2 is started");
            Thread.sleep(1000);
            System.out.println("Task 2 is finished");
        }
    }
