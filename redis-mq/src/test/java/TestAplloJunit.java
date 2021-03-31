import ahan.AhanConstant;
import ahan.Person;
import ahan.RedisMqConfiguration;
import ahan.TestApollo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static ahan.AhanConstant.PERSON_NAME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApollo.class})
@Slf4j
public class TestAplloJunit {

    @Autowired
    private String jedisConnectionFactory;

    @Test
    public void test() {
        System.out.println("qq" + jedisConnectionFactory);
    }

}
