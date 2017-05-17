package other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.util.Properties;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

@Slf4j
public class PropertiesLocationWithProfilePropertiesLoader extends ConfigPropertiesResourceLoader {

	public PropertiesLocationWithProfilePropertiesLoader(ConfigurableApplicationContext applicationContext, String propertiesName, boolean required) {
		this.applicationContext = applicationContext;
		this.propertiesName = propertiesName;
		this.setRequired(required);
	}

	private final ConfigurableApplicationContext applicationContext;

	private String propertiesName;

	@Override public Properties loadProperties() {
		String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
		String profile = activeProfiles[0];
		String path = getLocationWithProfile(propertiesName, profile);

		Resource resource = applicationContext.getResource(path);
		setResource(resource);
		return super.loadProperties();
	}

	protected String getLocationWithProfile(String location, String profile) {
		return location.replaceAll("\\$\\{" + ACTIVE_PROFILES_PROPERTY_NAME + "}", profile);
	}
}
