package ahan;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = {"ahan"})
@EnableApolloConfig({"application", "platform-configs"})
public class RedisMqConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(this.redisHost);
        jedisConnectionFactory.setPort(this.redisPort);
        String redisPasswordDecrypted = new String(Base64.getMimeDecoder().decode(this.redisPasswordEncrypted));
        jedisConnectionFactory.setPassword(redisPasswordDecrypted);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.setConnectionFactory(jedisConnectionFactory);

        return template;
    }

    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("change-topic");
    }

    /**
     * start one async thread redisContainer to monitor it
     *
     * @param redisMessageSubscriber
     * @param channelTopic
     * @param jedisConnectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisContainer(RedisMessageSubscriber redisMessageSubscriber, ChannelTopic channelTopic, JedisConnectionFactory jedisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);
        container.addMessageListener(new MessageListenerAdapter(redisMessageSubscriber), channelTopic);
        return container;
    }

    @Bean
    public LoadingCache<String, Person> caffeineCache(RedisTemplate redisTemplate) {
        return buildLoadingCache(redisTemplate, AhanConstant.KEY);
    }

    private <CachedObjectType> LoadingCache<String, CachedObjectType> buildLoadingCache(RedisTemplate redisTemplate, String redisHashName) {
        LoadingCache<String, CachedObjectType> cache = Caffeine.newBuilder()
                .expireAfterWrite(Integer.MAX_VALUE, TimeUnit.DAYS)
                .maximumSize(10000)
                //build is fallback
                .build(name -> (CachedObjectType) redisTemplate
                        .opsForHash()
                        .get(redisHashName, name));
        return cache;
    }

    @Value("${redis_port}")
    private Integer redisPort;
    @Value("${redis_host}")
    private String redisHost;
    @Value("${redis_password}")
    private String redisPasswordEncrypted;

}
