package batch.config.selector;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@EnableFkbBatchProcessing(jobName = "chunkJob-reader-processor-writer")
@EnableBatchProcessing
public class FkbSpringBatchApplication {

    /**
     * job bean 可以初始化，但是job不能launch
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(FkbSpringBatchApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        System.setProperty("111", "222");
        System.out.println("two");

        context.close();
    }
}
