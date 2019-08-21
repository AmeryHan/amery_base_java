package ahan;

import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static ahan.AhanConstant.PERSON_NAME;

@Component
@Slf4j
public class RedisMessageSubscriber implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LoadingCache<String, Person> caffeineCache;

    @Override
    public void onMessage(final Message message, byte[] pattern) {
        String changeEvent = (String) redisTemplate.getValueSerializer().deserialize(message.toString().getBytes());
        log.debug(changeEvent);
        Object object = null;
        try {
            Person p = Person.builder().age(10).name(PERSON_NAME).build();
            object = redisTemplate.opsForHash().get(AhanConstant.KEY, p.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Person person = (Person) object;
        log.debug(person.toString());

        caffeineCache.put(person.getName(), person);
        log.debug("save");
        Person caffeine = caffeineCache.get(person.getName());
        log.debug(caffeine.toString());
    }
}
