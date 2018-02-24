
import com.lagou.pard.dependencies.com.lmax.disruptor.EventFactory;
import com.lagou.pard.dependencies.com.lmax.disruptor.EventHandler;
import com.lagou.pard.dependencies.com.lmax.disruptor.RingBuffer;
import com.lagou.pard.dependencies.com.lmax.disruptor.TimeoutBlockingWaitStrategy;
import com.lagou.pard.dependencies.com.lmax.disruptor.dsl.Disruptor;
import com.lagou.pard.dependencies.com.lmax.disruptor.dsl.ProducerType;
import com.lagou.pard.dependencies.com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DisruptorTest {
    private RingBuffer<MessageHolder> ringBuffer;


    public static void main(String[] args) {
        new DisruptorTest().init();
    }

    public void init() {
        MessageHoderFactory messageHoderFactory = new MessageHoderFactory();
        Disruptor<MessageHolder> disruptor = new Disruptor<>(messageHoderFactory, 32, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new TimeoutBlockingWaitStrategy(1, TimeUnit.SECONDS));
        ringBuffer = disruptor.getRingBuffer();
        disruptor.handleEventsWith(new WorkerEventHandler());
        disruptor.start();

        new Thread(new Provider()).start();
    }

    private class Provider implements Runnable {
        private int count = 1;
        @Override
        public void run() {
            while (true) {
                long sequence = ringBuffer.next();
                try {
                    ringBuffer.get(sequence).setMessage("" + new Random().nextInt() % 2);
                    System.out.println("Created.." + count ++);
                } finally {
                    try {
                        ringBuffer.publish(sequence);
                    } finally {
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class MessageHolder {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private class MessageHoderFactory implements EventFactory<MessageHolder> {
        @Override
        public MessageHolder newInstance() {
            return new MessageHolder();
        }
    }

    private class WorkerEventHandler implements EventHandler<MessageHolder> {
        @Override
        public void onEvent(MessageHolder holder, long sequence, boolean endOfBatch) throws Exception {
            Object message = holder.getMessage();
            if(message.equals("0")){
                Thread.sleep(2000);
            }

            System.out.println(message);
        }
    }
}



