package redis;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Protocol;

import java.util.Set;


@Accessors(chain = true)
public class SentinelRedisConnectionFactoryBuilder {
	public static final String JMX_NAMEBASE = "redis.client.jedis:type=XXXXSentinelPool,name=";

	public static final String POOL_NAME = ".poolName";
	public static final String SENTINELS = ".sentinels";
	public static final String PASSWORD = ".password";
	public static final String MAX_TOTAL = ".maxTotal";
	public static final String MAX_IDLE = ".maxIdle";
	public static final String MIN_IDLE = ".minIdle";
	public static final String GLOBAL_MAX_WAIT_MILLIS = "redis.global.maxWaitMillis";
	public static final String TIMEOUT = ".timeout";

	private final Environment environment;
	private final String propertiesPrefix;

	@Getter
	@Setter
	private String poolName;
	@Getter
	@Setter
	private Set<String> sentinels;
	@Getter
	@Setter
	private String password;
	@Getter
	@Setter
	private int maxTotal = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;
	@Getter
	@Setter
	private int maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;
	@Getter
	@Setter
	private int minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;
	@Getter
	@Setter
	private long maxWaitMillis = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;
	@Getter
	@Setter
	private int timeout = Protocol.DEFAULT_TIMEOUT;
	@Getter
	@Setter
	private int database = Protocol.DEFAULT_DATABASE;


	public SentinelRedisConnectionFactoryBuilder(@NonNull Environment environment, @NonNull String propertiesPrefix) {
		this.environment = environment;
		this.propertiesPrefix = propertiesPrefix;

		populateProperties();
	}

	private String getRequiredEnvProperty(String postfix) {
		return environment.getRequiredProperty(propertiesPrefix + postfix);
	}

	private boolean containsProperty(String postfix) {
		return environment.containsProperty(propertiesPrefix + postfix);
	}

	private <T> T getRequiredEnvProperty(String postfix, Class<T> clazz) {
		return environment.getRequiredProperty(propertiesPrefix + postfix, clazz);
	}

	private void populateProperties() {
		setPoolName(getRequiredEnvProperty(POOL_NAME));

		String sentinelsString = getRequiredEnvProperty(SENTINELS);
		Set<String> sentinels = Sets.newHashSet(sentinelsString.split(","));
		setSentinels(sentinels);

		setPassword(getRequiredEnvProperty(PASSWORD));
		setMaxTotal(getRequiredEnvProperty(MAX_TOTAL, Integer.class));

		if (containsProperty(MAX_IDLE)) {
			setMaxIdle(getRequiredEnvProperty(MAX_IDLE, Integer.class));
		} else {
			setMaxIdle(getMaxTotal());
		}

		setMinIdle(getRequiredEnvProperty(MIN_IDLE, Integer.class));
		setMaxWaitMillis(environment.getRequiredProperty(GLOBAL_MAX_WAIT_MILLIS, Long.class));

		setTimeout(getRequiredEnvProperty(TIMEOUT, Integer.class));
	}

	public SentinelRedisConnectionFactory build() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setJmxNamePrefix(poolName);
		poolConfig.setJmxNameBase(JMX_NAMEBASE);

		JedisSentinelPool pool = new JedisSentinelPool(poolName, sentinels, poolConfig, timeout, normalizePassword(password));

		SentinelRedisConnectionFactory redisConnectionFactory = new SentinelRedisConnectionFactory(pool);
		redisConnectionFactory.setDatabase(database);
		return redisConnectionFactory;
	}

	private String normalizePassword(String password) {
		return StringUtils.defaultIfBlank(password, null);
	}

}
