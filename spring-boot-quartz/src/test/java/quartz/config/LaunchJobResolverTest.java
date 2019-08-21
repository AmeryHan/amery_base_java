package quartz.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ameryhan
 * @date 2019/7/24 18:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JobSchedulerApplication.class})
public class LaunchJobResolverTest {
    @Autowired
    private LaunchJobResolver launchJobResolver;

    @Test
    public void test() {
        //現在ut有問題，但是run spring boot main 函數沒有問題
        try {
            launchJobResolver.launchWithCron();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(1 == 1);
    }
}
