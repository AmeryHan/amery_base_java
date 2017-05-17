package other;

import load.ConfigPropertiesLoaderComposite;
import load.inter.ConfigPropertiesLoader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Properties;

@Slf4j
public class PathsConfigPropertiesLoader implements ConfigPropertiesLoader {

	public PathsConfigPropertiesLoader(ConfigurableApplicationContext applicationContext, List<String> paths, boolean required) {
		this.applicationContext = applicationContext;
		this.paths = paths;
		this.required = required;
	}

	private final ConfigurableApplicationContext applicationContext;

	private List<String> paths;

	@Setter
	private boolean required = true;

	private ConfigPropertiesLoaderComposite composite = new ConfigPropertiesLoaderComposite();

	@Override public Properties loadProperties() {

		for (String name : paths) {
			composite.addConfigPropertiesLoader(new PropertiesLocationWithProfilePropertiesLoader(applicationContext, name, required));
		}
		return composite.loadProperties();
	}
}
