package com.mternovykh.task.example1;

import org.springframework.boot.CommandLineRunner;

public class Task2 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Task 2 is started");
        Thread.sleep(1000);
        System.out.println("Task 2 is finished");
    }
}
