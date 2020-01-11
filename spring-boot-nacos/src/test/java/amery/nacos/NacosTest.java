package amery.nacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NacosConfiguration.class})
public class NacosTest {

    @NacosInjected
    private NamingService namingService;

    @Autowired
    private Foo foo;

    //    @NacosValue(value = "${spring.redis-four.database}", autoRefreshed = true)
    private int useLocalCache;

    @Test
    public void get() {
        try {
            int getValue = useLocalCache;
            List<Instance> list = namingService.getAllInstances("front-api");
            Assert.assertNotNull(1 == 1);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPlusCount() {
//        log.info("TestConfiguration1");
        Assert.assertEquals(foo.getName(), "from config");
    }
}
