package batch.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author EDZ
 */
@SpringBootApplication()
public class BatchApplication {

    public static void main(String[] args) {
        System.setProperty("jobName", "taskletJob");
        //System.setProperty("jobName", "chunkJob-reader-processor-writer");
        SpringApplication.run(BatchApplication.class, args);
    }
}
