package spring.boot;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author ameryhan
 * @date 2019/8/29 14:05
 */
@Component
public class MsgReceiver implements ChannelAwareMessageListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_A, containerFactory = "rabbitListenerContainerFactory")
    public void onMessage(Message message, Channel channel) throws Exception {
        logger.info("接收处理队列A当中的消息： " + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
