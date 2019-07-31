package jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author ameryhan
 * @date 2019/7/30 20:32
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ResourceConfig resourceConfig() {
        ResourceConfig config = new ResourceConfig();
        config.register(MyResource.class);
        return config;
    }
}
