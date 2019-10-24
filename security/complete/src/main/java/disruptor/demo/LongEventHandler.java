package disruptor.demo;

import com.lmax.disruptor.EventHandler;

/**
 * @Auther: BigDaddy
 * @Date: 2019/10/24 22:45
 * @Description: 事件消费者，也就是一个事件处理器。这个事件处理器简单地把事件中存储的数据打印到终端
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {

        System.out.println("消费者:"+longEvent.getValue());
    }
}
