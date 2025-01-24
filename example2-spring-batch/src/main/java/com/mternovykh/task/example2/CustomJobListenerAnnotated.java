package com.mternovykh.task.example2;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

public class CustomJobListenerAnnotated {
    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(String.format("JOB LISTENER 2. before job %s", jobExecution.getJobId()));
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        System.out.println(String.format("JOB LISTENER 2. after job %s", jobExecution.getJobId()));
    }
}
