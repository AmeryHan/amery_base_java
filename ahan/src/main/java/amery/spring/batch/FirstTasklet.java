package amery.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FirstTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(FirstTasklet.class);

    public RepeatStatus execute(StepContribution contribution,
	    ChunkContext chunkContext) throws Exception {
	
	logger.info("-----------FirstTasklet");
	return null;
    }

}
