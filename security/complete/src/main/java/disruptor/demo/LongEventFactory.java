package disruptor.demo;

import com.lmax.disruptor.EventFactory;

/**
 * @Auther: BigDaddy
 * @Date: 2019/10/24 22:44
 * @Description: 需要让Disruptor为我们创建事件
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
