import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class LongEventProducer implements Runnable {
    private final RingBuffer<LongEvent> ringBuffer;

    LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(bb.getLong(0));  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    @Override
    public void run() {
        ByteBuffer bb = ByteBuffer.allocate(8);
        while (true) {
            bb.putLong(0, (long) (Math.random() * 100));
            onData(bb);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}