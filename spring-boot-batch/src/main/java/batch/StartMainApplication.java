package batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;

import javax.sql.DataSource;

/**
 * @author EDZ
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JndiDataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class StartMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartMainApplication.class, args);
    }
}
