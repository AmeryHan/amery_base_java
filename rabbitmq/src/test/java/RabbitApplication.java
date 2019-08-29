import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.boot.MsgProducer;
import spring.boot.RabbitConfig;

/**
 * @author ameryhan
 * @date 2019/8/29 14:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RabbitConfig.class})
public class RabbitApplication {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void main() {
        MsgProducer msgProducer = new MsgProducer((rabbitTemplate));
        msgProducer.sendMsg("hhh");
    }
}
