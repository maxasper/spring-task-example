package com.mternovykh.task.example2;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;

public class CustomStepListenerAnnotated {
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        System.out.println(String.format("STEP LISTENER 2. Before step %s", stepExecution.getStepName()));
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println(String.format("STEP LISTENER 2. After step %s", stepExecution.getStepName()));
        return stepExecution.getExitStatus();
    }
}
