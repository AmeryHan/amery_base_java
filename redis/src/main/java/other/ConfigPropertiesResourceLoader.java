package other;

import load.inter.ConfigPropertiesLoader;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ConfigPropertiesResourceLoader implements ConfigPropertiesLoader {

	public ConfigPropertiesResourceLoader() {
	}

	@Getter
	@Setter
	private Resource resource;

	@Getter
	@Setter
	private boolean required = true;

	@Override public Properties loadProperties() {
		try {

			if (resource.exists()) {
				log.info("local config properties resource : {}", resource);
				return PropertiesLoaderUtils.loadProperties(resource);
			} else {
				if (required) {
					throw new FileNotFoundException(String.format("file not found error. : %s", resource));
				}
				log.warn("file not found : {}", resource);
				return new Properties();
			}
		} catch (IOException ex) {
			log.error("Failed to load configuration properties. Resource - " + resource.toString() , ex);
			throw new IllegalStateException("Failed to load configuration properties. Resource - " + resource.toString(), ex);
		}
	}
}
