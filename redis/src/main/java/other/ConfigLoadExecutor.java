package other;

import load.ConfigPropertiesLoaderComposite;
import load.basic.ConfigPropertiesLoader;
import load.overrideload.ConfigOverrideConfigPropertiesLoader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Properties;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
@SuppressWarnings("PMD.UnusedPrivateField")
public class ConfigLoadExecutor {

	public static final String PARENT_PROPERTIES_LOCATION = "classpath:parent-${" + ACTIVE_PROFILES_PROPERTY_NAME + "}.xml";

	public static final String PROJECT_PROPERTIES_LOCATION = "classpath:project-${" + ACTIVE_PROFILES_PROPERTY_NAME + "}.xml";

	private final ConfigurableApplicationContext applicationContext;

	private ConfigPropertiesLoaderComposite composite = new ConfigPropertiesLoaderComposite();

	@Getter
	@Setter
	private List<String> afterProjects;

	public Properties execute() {
		composite.addConfigPropertiesLoader(localProperties(PARENT_PROPERTIES_LOCATION));
		composite.addConfigPropertiesLoader(localProperties(PROJECT_PROPERTIES_LOCATION));
		composite.addConfigPropertiesLoader(configOverride());

		Properties properties = composite.loadProperties();
		return properties;
	}

	ConfigPropertiesLoader localProperties(String propertiesName) {
		return new PropertiesLocationWithProfilePropertiesLoader(applicationContext, propertiesName, true);
	}

	ConfigPropertiesLoader configOverride() {
		return new ConfigOverrideConfigPropertiesLoader(applicationContext);
	}

}
