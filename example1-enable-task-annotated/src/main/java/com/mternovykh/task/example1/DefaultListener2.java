package com.mternovykh.task.example1;

import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;

public class DefaultListener2 {
    @BeforeTask
    public void onTaskStartup(TaskExecution taskExecution) {
        System.out.printf("DefaultListener2. Task started: %s\n", taskExecution.getTaskName());
    }

    @AfterTask
    public void onTaskEnd(TaskExecution taskExecution) {
        taskExecution.setErrorMessage("some error message");
        System.out.printf("DefaultListener2. Task ended: %s\n", taskExecution.getTaskName());
    }

    @FailedTask
    public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
        System.out.printf("DefaultListener2. Task failed: %s\n", taskExecution.getTaskName());
    }
}
