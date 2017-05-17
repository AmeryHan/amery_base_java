import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;

import java.util.Collections;
import java.util.List;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;


@Slf4j
public class ConfigurationPropertiesApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	//@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		//PropertiesPropertySource propertySource = new PropertiesPropertySource(CONFIGURATION_PROPERTIES_SOURCE_NAME, transformedProperties);
		//applicationContext.getEnvironment().getPropertySources().addLast(propertySource);

		//log.info("loaded properties : {}", transformedProperties);

	}

	@Deprecated
	protected void addPropertiesPaths(ResourceLoader resourceLoader, String profile, List<String> paths) {
		paths.addAll(getAfterProjectPropertiesPaths());
	}

	@Deprecated
	protected String getLocationWithProfile(String location, String profile) {
		return location.replaceAll("\\$\\{" + ACTIVE_PROFILES_PROPERTY_NAME + "}", profile);
	}


	protected List<String> getAfterProjectPropertiesPaths() {
		return Collections.emptyList();
	}

}
