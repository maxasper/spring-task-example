package com.mternovykh.task.example2;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CustomStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println(String.format("STEP LISTENER 1. Before step %s", stepExecution.getStepName()));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println(String.format("STEP LISTENER 1. After step %s", stepExecution.getStepName()));
        return stepExecution.getExitStatus();
    }
}
