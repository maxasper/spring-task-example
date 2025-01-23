package com.mternovykh.task.example2;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@RequiredArgsConstructor
public class CustomTasklet2 implements Tasklet {
    private final CustomTaskletParameters customTaskletParameters;

    @Override
    @SneakyThrows
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        System.out.println(String.format(
                "%s started with params %s",
                this.getClass().getSimpleName(), customTaskletParameters.params())
        );

        Thread.sleep(500);

        System.out.println(String.format(
                "%s finished with params %s",
                this.getClass().getSimpleName(), customTaskletParameters.params())
        );

        return RepeatStatus.FINISHED;
    }
}
