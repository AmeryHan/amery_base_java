package disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author ameryhan
 * @date 2019/8/21 13:42
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者:" + event.getValue());
    }

}
