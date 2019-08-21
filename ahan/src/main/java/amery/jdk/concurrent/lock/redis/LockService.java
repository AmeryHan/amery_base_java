package amery.jdk.concurrent.lock.redis;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ameryhan
 * @date 2019/8/21 20:30
 */
public class LockService {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "39.107.69.43", 6379, 3000);
    }

    LockRedis lockRedis = new LockRedis(pool);

    public void seckill() {
        String identifier = lockRedis.lockWithTimeout("itmayiedu", 5000l, 5000l);
        if (StringUtils.isEmpty(identifier)) {
            // 获取锁失败
            System.out.println(Thread.currentThread().getName() + ",获取锁失败，原因时间超时!!!");
            return;
        }
        System.out.println(Thread.currentThread().getName() + "获取锁成功,锁id identifier:" + identifier + "，执行业务逻辑");
        try {
            Thread.sleep(30);
        } catch (Exception e) {

        }
        // 释放锁
        boolean releaseLock = lockRedis.releaseLock("itmayiedu", identifier);
        if (releaseLock) {
            System.out.println(Thread.currentThread().getName() + "释放锁成功,锁id identifier:" + identifier);
        }

    }

}
