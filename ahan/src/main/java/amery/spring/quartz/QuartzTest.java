package amery.spring.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class QuartzTest {
    
    private static final Logger logger = LoggerFactory.getLogger(QuartzTest.class);
    
    @Test
    public void testQuartz(){
	logger.info("testQuartz");
    }
    

}
