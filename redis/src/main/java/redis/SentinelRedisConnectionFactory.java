package redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisExceptionConverter;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;


@Slf4j
public class SentinelRedisConnectionFactory implements InitializingBean, DisposableBean, RedisConnectionFactory {
	private Pool<Jedis> pool = null;
	private int dbIndex = 0;
	private boolean convertPipelineAndTxResults = true;


	public SentinelRedisConnectionFactory(JedisSentinelPool pool) {
		this.pool = pool;
	}


	protected Jedis fetchJedisConnector() {
		try {
			return pool.getResource();
		} catch (Exception ex) {
			throw new RedisConnectionFailureException("Cannot get Jedis connection", ex);
		}
	}


	protected JedisConnection postProcessConnection(JedisConnection connection) {
		return connection;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
		try {
			pool.destroy();
		} catch (Exception ex) {
			log.warn("Cannot properly close Jedis pool", ex);
		} finally {
			pool = null;
		}
	}

	public JedisConnection getConnection() {
		Jedis jedis = fetchJedisConnector();
		JedisConnection connection = new JedisConnection(jedis, pool, dbIndex);
		connection.setConvertPipelineAndTxResults(convertPipelineAndTxResults);
		return postProcessConnection(connection);
	}


	public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
		return new JedisExceptionConverter().convert(ex);
	}


	public int getDatabase() {
		return dbIndex;
	}


	public void setDatabase(int index) {
		Assert.isTrue(index >= 0, "invalid DB index (a positive index required)");
		this.dbIndex = index;
	}

	public boolean getConvertPipelineAndTxResults() {
		return convertPipelineAndTxResults;
	}


	public void setConvertPipelineAndTxResults(boolean convertPipelineAndTxResults) {
		this.convertPipelineAndTxResults = convertPipelineAndTxResults;
	}
}
