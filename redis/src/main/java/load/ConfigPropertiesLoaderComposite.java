package load;

import com.google.common.base.Optional;
import load.inter.ConfigPropertiesLoader;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigPropertiesLoaderComposite implements ConfigPropertiesLoader {

	@Getter
	private List<ConfigPropertiesLoader> loaders = new ArrayList<>();

	@Override public Properties loadProperties() {
		Properties properties = new Properties();
		for (ConfigPropertiesLoader each : loaders) {
			properties.putAll(each.loadProperties());
		}
		return properties;
	}

	public boolean addConfigPropertiesLoader(ConfigPropertiesLoader configPropertiesLoader) {
		return loaders.add(configPropertiesLoader);
	}

	public boolean addConfigPropertiesLoader(Optional<ConfigPropertiesLoader> configPropertiesLoader) {
		if (configPropertiesLoader.isPresent()) {
			return loaders.add(configPropertiesLoader.get());
		}
		return false;

	}
}
