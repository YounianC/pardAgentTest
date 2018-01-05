import com.lmax.disruptor.EventHandler;

import java.util.concurrent.atomic.AtomicLong;

public class LongEventHandler implements EventHandler<LongEvent> {

    private AtomicLong count = new AtomicLong(0);

    LongEventHandler() {
        System.out.println("new LongEventHandler created");
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event,count:" + count.incrementAndGet());
    }
}