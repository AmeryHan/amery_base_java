package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ahan on 17/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = redis.RedisConfiguration.class, initializers = ConfigurationPropertiesApplicationContextInitializer.class)
@Configuration
public class RedisTest {

	@Autowired
	private StringRedisTemplate redisTemplate;

	private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

	@Test
	public void testQuartz() {
		logger.info("start");
		redisTemplate.opsForValue().set("key", "value");
		logger.info("end");
	}

}
