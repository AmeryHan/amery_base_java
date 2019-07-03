package batch.config;

import batch.PartitionerStep;
import batch.TestPartitioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.PartitionStep;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableBatchProcessing
@Configuration
public class SampleBatchApplication {
    private static final Logger log = LoggerFactory.getLogger(SampleBatchApplication.class);
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;

    @Bean
    protected Tasklet tasklet() {
        return (contribution, context) -> {
            log.debug("step1 tasklet");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    protected Tasklet tasklet2() {
        return (contribution, context) -> {
            log.debug("step2 tasklet");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    protected TestPartitioner testPartitioner() {
        TestPartitioner testPartitioner = new TestPartitioner();
        testPartitioner.setItemCount(100);

        //testPartitioner.set

        return testPartitioner;
    }

    @Bean
    public Job job()  {
        return this.jobs.get("taskletJob")
                .start(step1())
                .next(step2())
                .next(step3(threadPoolTaskExecutor()))
                .build();
    }

    @Bean
    protected Step step1()  {
        return this.steps.get("taskletJob-step1")
                .tasklet(tasklet())
                .build();
    }

    @Bean
    protected Step step2()  {
        return this.steps.get("taskletJob-step2")
                .tasklet(tasklet2())
                .build();
    }

    @Bean
    protected Step step3(ThreadPoolTaskExecutor threadPoolTaskExecutor)  {
        Step step3 = this.steps.get("taskletJob-step3")
                .partitioner("taskletJob-step3", testPartitioner())
                .build();

        if (step3 instanceof  PartitionStep) {
            PartitionStep partitionStep = (PartitionStep)step3;

            TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
            partitionHandler.setGridSize(3);
            partitionHandler.setTaskExecutor(threadPoolTaskExecutor);
            partitionHandler.setStep(step3);
            partitionStep.setPartitionHandler(partitionHandler);
        }
        return step3;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(50);
        threadPoolTaskExecutor.setMaxPoolSize(200);
        threadPoolTaskExecutor.setQueueCapacity(1000);
        threadPoolTaskExecutor.setThreadNamePrefix("Data-Job");
        return threadPoolTaskExecutor;
    }

    @Bean
    protected PartitionerStep partitionerStep() {
        PartitionerStep partitionerStep = new PartitionerStep();
        //partitionerStep.setNumber("#{stepExecutionContext['number']}");
        partitionerStep.setNumber(10);
        return partitionerStep;
    }
}
