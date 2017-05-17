package test;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ResourceLoader;
import other.ConfigLoadExecutor;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;


@Slf4j
public class ConfigurationPropertiesApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	//@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		System.setProperty("spring.profiles.active", "develop");
		System.setProperty("config.override", "classpath:project-develop-redwood.xml");

		String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
		String profile = activeProfiles[0];

		List<String> afterProjectConfigPaths = Lists.newArrayList();
		addPropertiesPaths(applicationContext, profile, afterProjectConfigPaths);

		Properties properties;

		ConfigLoadExecutor executor = ConfigLoadExecutor.of(applicationContext);
		executor.setAfterProjects(afterProjectConfigPaths);

		try {
			properties = executor.execute();
		} catch (Exception e) {
			throw new IllegalStateException("Fail to initialize configuration properties", e);
		}

		PropertiesPropertySource propertySource = new PropertiesPropertySource("source name", properties);
		applicationContext.getEnvironment().getPropertySources().addLast(propertySource);

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
