package amery.spring.quartz;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class BusinessReport extends QuartzJobBean {

    public void perform() { //执行报表统计入口函数
        //业务逻辑  
        System.out.println("开始执行报表的业务逻辑了----现在的时间是--" + new Date());

    }

    @Override
    protected void executeInternal(JobExecutionContext arg0) {
        perform();

    }

}
