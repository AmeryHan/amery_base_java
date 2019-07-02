package batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Job job()  {
        return this.jobs.get("taskletJob").start(step1())
                //.next()
                .build();
    }

    @Bean
    protected Step step1()  {
        return this.steps.get("taskletJob-step1")
                .tasklet(tasklet())
                .build();
    }
}
