package test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import other.ConfigLoadExecutor;

import java.util.Properties;

@Slf4j
public class ConfigurationPropertiesApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        System.setProperty("spring.profiles.active", "develop");
        System.setProperty("config.override", "classpath:project-develop-amery.xml");

        Properties properties;

        ConfigLoadExecutor executor = ConfigLoadExecutor.of(applicationContext);

        try {
            properties = executor.execute();
        } catch (Exception e) {
            throw new IllegalStateException("Fail to initialize configuration properties", e);
        }

        PropertiesPropertySource propertySource = new PropertiesPropertySource("source name", properties);
        applicationContext.getEnvironment().getPropertySources().addLast(propertySource);

    }

}
