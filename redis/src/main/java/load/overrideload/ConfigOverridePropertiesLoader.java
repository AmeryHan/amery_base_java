package load.overrideload;

import load.basic.ConfigPropertiesResourceLoader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Properties;

@Slf4j
public class ConfigOverridePropertiesLoader extends ConfigPropertiesResourceLoader {

    public ConfigOverridePropertiesLoader(ResourceLoader resourceLoader, String path) {
        this.resourceLoader = resourceLoader;
        this.path = path;
    }

    private final ResourceLoader resourceLoader;

    @Setter
    private String path;

    @Override
    public Properties loadProperties() {
        Resource resource = resourceLoader.getResource(refinePath(path));
        setResource(resource);
        setRequired(true);

        return super.loadProperties();
    }

    private static String refinePath(String configoverride) {
        if (!configoverride.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)) {
            String filepath = new File(configoverride).getAbsolutePath();
            return ResourceUtils.FILE_URL_PREFIX + filepath;
        }
        return configoverride;
    }
}
