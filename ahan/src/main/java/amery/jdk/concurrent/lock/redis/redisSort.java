package amery.jdk.concurrent.lock.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;

import java.util.List;

/**
 * @author ameryhan
 * @date 2019/8/26 13:11
 */
public class redisSort {

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

    public static void main(String[] args) {

        Jedis jedis = pool.getResource();

        String key = "item-list-key";

        String sortKey = "item-sort";

        SortingParams sortingParams = new SortingParams();

// 这里是根据价格排序 如果想要根据销量排序 则将price改为sales即可

        sortingParams.by(sortKey + "-*->price");

        sortingParams.desc();

// 将自身id输出
        sortingParams.get("#");

// 将价格输出
        sortingParams.get(sortKey + "-*->price");

// 将销量输出
        sortingParams.get(sortKey + "-*->sales");
        List<String> sort = jedis.sort(key, sortingParams);

        for (String str : sort) {
            System.out.println(str);
        }
        //key, field, value
        //jedis.hset("item-sort-"+item.getItemId(),"price",item.getItemPrice().toString());
    }
}
