package com.mternovykh.task.example1;

import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;

public class DefaultListener1 implements TaskExecutionListener {
    @Override
    public void onTaskStartup(TaskExecution taskExecution) {
        taskExecution.setExitMessage("some exit message");
        System.out.printf("DefaultListener1. Task started: %s\n", taskExecution.getTaskName());
    }

    @Override
    public void onTaskEnd(TaskExecution taskExecution) {
        System.out.printf("DefaultListener1. Task ended: %s\n", taskExecution.getTaskName());
    }

    @Override
    public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
        System.out.printf("DefaultListener1. Task failed: %s\n", taskExecution.getTaskName());
    }
}
