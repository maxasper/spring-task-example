package com.mternovykh.task.example2;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CustomJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(String.format("JOB LISTENER 1. before job %s", jobExecution.getJobId()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(String.format("JOB LISTENER 1. after job %s", jobExecution.getJobId()));
    }
}
