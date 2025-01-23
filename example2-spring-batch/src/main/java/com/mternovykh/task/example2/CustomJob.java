package com.mternovykh.task.example2;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CustomJob {
    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;
    private final JobLauncher jobLauncher;

    private static final Map<String, Function<CustomTaskletParameters, Tasklet>> simpleTaskletBuilder = Map.of(
            "t1", CustomTasklet1::new,
            "t2", CustomTasklet2::new,
            "t3", CustomTasklet3::new,
            "t-ex", CustomTaskletWithException::new
    );


    public ExitStatus runJob1() {
        return runJob(buildJob1Simple());
    }

    public ExitStatus runJob2() {
        return runJob(buildJob2Conditional());
    }

    public ExitStatus runJob3() {
        return runJob(buildJob3Exception());
    }

    public ExitStatus runJob4() {
        return runJob(buildJob4Async());
    }


    private ExitStatus runJob(Job job) {
        try {
            return jobLauncher.run(job, new JobParameters()).getExitStatus();
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

    public Job buildJob1Simple() {
        return new JobBuilder("job-" + UUID.randomUUID(), jobRepository)
                .start(tasklet1Step1())
                .next(tasklet1Step2())
                .next(tasklet2Step3())
                .next(tasklet3Step4())
                .build();
    }

    public Job buildJob2Conditional() {
        Step step1 = tasklet1Step1();
        return new JobBuilder("job-" + UUID.randomUUID(), jobRepository)
                .start(step1)
                .on("*").to(tasklet1Step2()).next(tasklet2Step3())
                .from(step1).on("FAILED").to(tasklet3Step4())
                .end()
                .build();
    }

    public Job buildJob3Exception() {
        Step step2 = taskletWithExceptionStep5();
        return new JobBuilder("job-" + UUID.randomUUID(), jobRepository)
                .start(tasklet1Step1())
                .on("*").to(step2).next(tasklet2Step3())
                .from(step2).on(ExitStatus.FAILED.getExitCode()).to(tasklet3Step4())
                .end()
                .build();
    }

    public Job buildJob4Async() {
        Flow f1 = new FlowBuilder<SimpleFlow>("flow1")
                .start(tasklet1Step1())
                .next(tasklet2Step3())
                .build();

        Flow f2 = new FlowBuilder<SimpleFlow>("flow2")
                .start(tasklet3Step4())
                .next(tasklet1Step2())
                .build();

        return new JobBuilder("job-" + UUID.randomUUID(), jobRepository)
                .start(f1)
                .split(new SimpleAsyncTaskExecutor())
                .add(f2)
                .next(tasklet2Step5())
                .end()
                .build();
    }

    public Step buildStep(CustomStepParameters config) {
        return new StepBuilder(config.name(), jobRepository)
                .tasklet(buildTasklet(config), transactionManager)
                .build();
    }

    private Tasklet buildTasklet(CustomStepParameters config) {
        return simpleTaskletBuilder.get(config.taskletType()).apply(config.customTaskletParameters());
    }

    private Step tasklet1Step1() {
        return buildStep(CustomStepParameters.builder()
                .name("tasklet1Step1")
                .taskletType("t1")
                .customTaskletParameters(CustomTaskletParameters.builder().params(Map.of("p1", "v1")).build())
                .build());
    }

    private Step tasklet1Step2() {
        return buildStep(CustomStepParameters.builder()
                .name("tasklet1Step2")
                .taskletType("t1")
                .customTaskletParameters(CustomTaskletParameters.builder().params(Map.of("p2", "v2")).build())
                .build());
    }

    private Step tasklet2Step3() {
        return buildStep(CustomStepParameters.builder()
                .name("tasklet2Step3")
                .taskletType("t2")
                .customTaskletParameters(CustomTaskletParameters.builder().params(Map.of("p3", "v3")).build())
                .build());
    }

    private Step tasklet3Step4() {
        return buildStep(CustomStepParameters.builder()
                .name("tasklet3Step4")
                .taskletType("t3")
                .customTaskletParameters(CustomTaskletParameters.builder().params(Map.of("p4", "v4")).build())
                .build());
    }

    private Step tasklet2Step5() {
        return buildStep(CustomStepParameters.builder()
                .name("tasklet2Step5")
                .taskletType("t2")
                .customTaskletParameters(CustomTaskletParameters.builder().params(Map.of("p5", "v5")).build())
                .build());
    }

    private Step taskletWithExceptionStep5() {
        return buildStep(CustomStepParameters.builder()
                .name("taskletWithExceptionStep5")
                .taskletType("t-ex")
                .customTaskletParameters(CustomTaskletParameters.builder().params(Map.of("p5", "v5")).build())
                .build());
    }

}
