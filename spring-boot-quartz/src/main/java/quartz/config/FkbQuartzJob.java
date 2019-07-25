package quartz.config;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author ameryhan
 * @date 2019/7/24 15:51
 */
public class FkbQuartzJob extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(FkbQuartzJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        logger.error("执行到这儿了");
    }
}
