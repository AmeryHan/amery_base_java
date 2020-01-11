package amery.nacos;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class Config {

    @Bean
    public Foo foo() {
        return new Foo("from config");
    }
}
