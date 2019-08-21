package load.overrideload;

import com.google.common.collect.Lists;
import load.ConfigPropertiesLoaderComposite;
import load.basic.ConfigPropertiesLoader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Properties;

@Slf4j
public class ConfigOverrideConfigPropertiesLoader implements ConfigPropertiesLoader {

    //config call not config part

    public static final String OVERRIDE_PROPERTY_NAME = "config.override";

    public ConfigOverrideConfigPropertiesLoader(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        String configOverrides = applicationContext.getEnvironment().getProperty(OVERRIDE_PROPERTY_NAME);
        setPaths(Lists.newArrayList(StringUtils.commaDelimitedListToStringArray(configOverrides)));
    }

    private final ConfigurableApplicationContext applicationContext;

    private ConfigPropertiesLoaderComposite composite = new ConfigPropertiesLoaderComposite();

    @Setter
    private List<String> paths;

    @Override
    public Properties loadProperties() {

        for (String name : paths) {
            composite.addConfigPropertiesLoader(new ConfigOverridePropertiesLoader(applicationContext, name));
        }
        return composite.loadProperties();
    }

}
