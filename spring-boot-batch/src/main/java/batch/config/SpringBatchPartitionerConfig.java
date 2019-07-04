package batch.config;

import batch.TestPartitionerStep;
import batch.TestPartitioner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.PartitionStep;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableBatchProcessing
//@Configuration
public class SpringBatchPartitionerConfig {
    private static final Logger log = LoggerFactory.getLogger(SpringBatchPartitionerConfig.class);
    @Autowired
    private JobBuilderFactory jobs;
    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job job(ThreadPoolTaskExecutor threadPoolTaskExecutor, TestPartitionerStep testPartitionerStep)  {
        return this.jobs.get("taskletJob444")
                .start(step1())
                .next(step2())
                .next(step3(threadPoolTaskExecutor, testPartitionerStep))
                //.incrementer(new BatchIncrementer())
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
    protected Step step3(ThreadPoolTaskExecutor threadPoolTaskExecutor, TestPartitionerStep testPartitionerStep)  {
        Step step3 = this.steps.get("taskletJob-step3")
                .partitioner("taskletJob-step3", testPartitioner())
                .build();

        if (step3 instanceof  PartitionStep) {
            PartitionStep partitionStep = (PartitionStep)step3;

            TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
            partitionHandler.setGridSize(3);
            partitionHandler.setTaskExecutor(threadPoolTaskExecutor);

            Step partitionerStep = this.steps.get("taskletJob-step3-partitionerStep")
                    .tasklet(testPartitionerStep)
                    .build();

            partitionHandler.setStep(partitionerStep);
            partitionStep.setPartitionHandler(partitionHandler);
        }
        return step3;
    }

    @Bean
    protected Tasklet tasklet() {
        return (contribution, context) -> {
            log.info("step1 tasklet");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    protected Tasklet tasklet2() {
        return (contribution, context) -> {
            log.info("step2 tasklet");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    protected TestPartitioner testPartitioner() {
        TestPartitioner testPartitioner = new TestPartitioner();
        testPartitioner.setItemCount(100);
        return testPartitioner;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(50);
        threadPoolTaskExecutor.setMaxPoolSize(200);
        threadPoolTaskExecutor.setQueueCapacity(1000);
        threadPoolTaskExecutor.setThreadNamePrefix("fkb-spring-batch-thread");
        return threadPoolTaskExecutor;
    }

    @Bean
    @StepScope
    protected TestPartitionerStep testPartitionerStep(@Value("#{stepExecutionContext['number']}") int number) {
        TestPartitionerStep testPartitionerStep = new TestPartitionerStep();
        testPartitionerStep.setNumber(number);
        return testPartitionerStep;
    }
}
