import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        System.out.println("newInstance...");
        return new LongEvent();
    }
}