package batch.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author EDZ
 */
@SpringBootApplication()
public class BatchApplication {

    public static void main(String[] args) {
        //用--jobName传入不同jobName, 调用不同的job
        //System.setProperty("jobName", "taskletJob");
        System.setProperty("jobName", "chunkJob-reader-processor-writer");
        //SpringApplication.run(BatchApplication.class, args);
        ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(BatchApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        configurableApplicationContext.close();
    }
}
