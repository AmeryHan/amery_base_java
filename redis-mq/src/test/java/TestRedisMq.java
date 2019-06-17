import ahan.AhanConstant;
import ahan.Person;
import ahan.RedisMqConfiguration;
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
@ContextConfiguration(classes = {RedisMqConfiguration.class})
@Slf4j
public class TestRedisMq {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ChannelTopic channelTopic;

    @Test
    public void test() {
        Person p = Person.builder().age(10).name(PERSON_NAME).build();
        redisTemplate.opsForHash().put(AhanConstant.KEY, p.getName(), p);
        redisTemplate.convertAndSend(channelTopic.getTopic(), "change-name");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("test");
    }

}
