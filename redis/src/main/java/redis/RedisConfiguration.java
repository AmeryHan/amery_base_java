package redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by ahan on 17/05/2017.
 */
@Configuration
public class RedisConfiguration {

	public static final String DEFAULT_REDIS_PREFIX = "redis";
	@Autowired
	private Environment environment;

	@Bean
	public StringRedisTemplate redisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
		return stringRedisTemplate;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new SentinelRedisConnectionFactoryBuilder(environment, DEFAULT_REDIS_PREFIX).build();
	}
}
