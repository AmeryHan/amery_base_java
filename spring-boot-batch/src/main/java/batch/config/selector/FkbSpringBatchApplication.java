package batch.config.selector;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableFkbBatchProcessing(jobName = "taskletJob")
@EnableBatchProcessing
public class FkbSpringBatchApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FkbSpringBatchApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        System.setProperty("111", "222");
        System.out.println("two");

        //context.close();
    }
}
