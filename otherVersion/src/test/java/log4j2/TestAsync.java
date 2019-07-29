package log4j2;

import org.apache.logging.log4j.core.LoggerContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAsync {

    private static final Logger logger = LoggerFactory.getLogger(TestAsync.class);

    @Test
    public void test_one_thread() {
        URI uri = null;
        try {
            uri = new URI("classpath:log4j2.xml");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.setProperty("idc", "idc");
        System.setProperty("spring.application.name", "applicationName");
        System.setProperty("instanceId", "uuid");
        LoggerContext.getContext().setConfigLocation(uri);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = 100;
        for (int i = 0; i <= count; i ++) {
            logger.debug("check is order by count {} ", i);
        }
        logger.debug("end");
    }

}
