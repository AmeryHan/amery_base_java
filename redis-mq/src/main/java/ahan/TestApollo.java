package ahan;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ahan"})
@EnableApolloConfig({"application", "ops.mongodb", "ops.mysql", "ops.redis"})
public class TestApollo {

    @Bean
    String jedisConnectionFactory() {
        System.out.println("----" + mongodb1);
        return "jedisConnectionFactory";
    }

    @Value("${mongodb1}")
    private String mongodb1;

}
