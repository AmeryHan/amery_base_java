import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ameryhan
 * @date 2019/7/30 13:03
 */
@Configuration
public class AppConfig {

    @Bean
    public String hello() {
        return "hello_xyz";
    }

}
