package quartz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author ahan
 */
@SpringBootApplication
@RestController
public class JobSchedulerApplication {
    private static Logger logger = LoggerFactory.getLogger(JobSchedulerApplication.class);

    @Autowired
    private LaunchJobResolver launchJobResolver;

    @Bean(name = "quartz-datasource")
    @ConfigurationProperties(prefix = "spring.quartz-datasource")
    public DataSource quartzDataSource() {
        return DataSourceBuilder.create().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(JobSchedulerApplication.class, args);
    }

    @GetMapping("/launch")
    public void launch(String name) {
        try {
            launchJobResolver.launchWithCron();
        } catch (Exception e) {
            logger.error("launch failed", e);
        }
    }
}
