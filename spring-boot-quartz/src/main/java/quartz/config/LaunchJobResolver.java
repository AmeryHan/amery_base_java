package quartz.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ameryhan
 * @date 2019/7/24 18:21
 */
@Component
public class LaunchJobResolver {
    @Autowired
    private Scheduler scheduler;

    public void launchWithCron() throws Exception{
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("xxx", "xxx");

        JobDetail jobDetail = JobBuilder.newJob(FkbQuartzJob.class)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();

        //cron will replaced with param */5 * * * * ?
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");

        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withSchedule(scheduleBuilder)
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
