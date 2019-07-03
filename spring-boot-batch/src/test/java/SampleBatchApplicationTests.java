import batch.config.BatchApplication;
import batch.config.SampleBatchApplication;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleBatchApplicationTests {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testDefaultSettings() throws Exception {
        assertThat(SpringApplication
                .exit(SpringApplication.run(BatchApplication.class))).isEqualTo(0);
        String output = this.outputCapture.toString();
        assertThat(output).contains("completed with the following parameters");
    }

}
