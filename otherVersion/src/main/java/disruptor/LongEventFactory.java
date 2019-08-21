package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author ameryhan
 * @date 2019/8/21 13:41
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {

        return new LongEvent();
    }

}
