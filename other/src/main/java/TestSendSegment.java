import com.lagou.pard.agent.remote.GRPCStreamServiceStatus;
import com.lagou.pard.common.trace.Span;
import com.lagou.pard.common.trace.SpanKind;
import com.lagou.pard.common.trace.SpanLayer;
import com.lagou.pard.common.trace.TraceSegment;
import com.lagou.pard.dependencies.io.grpc.ManagedChannel;
import com.lagou.pard.dependencies.io.grpc.netty.NettyChannelBuilder;
import com.lagou.pard.dependencies.io.grpc.stub.StreamObserver;
import com.lagou.pard.remote.grpc.proto.Downstream;
import com.lagou.pard.remote.grpc.proto.TraceSegmentObject;
import com.lagou.pard.remote.grpc.proto.TraceSegmentServiceGrpc;

import java.util.Date;
import java.util.Random;

public class TestSendSegment {

    public static void main(String[] args) {
        ManagedChannel channel = NettyChannelBuilder.forAddress("192.168.153.1", 12730).usePlaintext(true).build();
        System.out.println("build..");
        final TraceSegmentServiceGrpc.TraceSegmentServiceStub stub = TraceSegmentServiceGrpc.newStub(channel);
        System.out.println("newStub");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run...");

                final GRPCStreamServiceStatus steamStatus = new GRPCStreamServiceStatus(false);
                final StreamObserver<TraceSegmentObject> streamObserver = stub.collect(new StreamObserver<Downstream>() {
                    @Override
                    public void onNext(Downstream value) {
                    }

                    @Override
                    public void onError(Throwable t) {
                        steamStatus.finish();
                        System.out.println("onError..." + t.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        steamStatus.finish();
                    }
                });

                while (true) {
                    try {
                        streamObserver.onNext(generateSegment().serialize());
                        Thread.sleep(1);
                    } catch (Throwable t) {
                        System.out.println("======== " + t.getMessage());
                    }
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        //new Thread(runnable).start();
        //new Thread(runnable).start();
        //new Thread(runnable).start();
    }

    private static TraceSegment generateSegment() {
        final TraceSegment segment = new TraceSegment("testApplication");
        Span parentSpan = new Span(0, -1, new Date().getTime(), "test_tomcat" + new Random().nextInt());
        parentSpan.setComponent("tomcat");
        parentSpan.setError(false);
        parentSpan.setEndTime(new Date().getTime());
        parentSpan.setPeerHost("127.0.0.1");
        parentSpan.setPeers("");
        parentSpan.setPeerPort(1);
        parentSpan.setSpanKind(SpanKind.client);
        parentSpan.setSpanLayer(SpanLayer.http);
        parentSpan.setStatusCode(200);
        parentSpan.setTag("url", "http://localhost:8080/redis" + new Random().nextInt());
        segment.archive(parentSpan);

        for (int i = 1; i <= 20; i++) {
            Span span =  new Span(i, parentSpan, new Date().getTime() + i, "test_" + i);
            span.setComponent("Redis");
            span.setError(false);
            span.setEndTime(span.getStartTime() + 20);
            span.setPeerHost("127.0.0.1");
            span.setPeers("");
            span.setPeerPort(1);
            span.setSpanKind(SpanKind.client);
            span.setSpanLayer(SpanLayer.http);
            span.setStatusCode(200);
            span.setTag("db.type", "Redis");
            span.setTag("db.statement", "get username");
            segment.archive(span);
        }

        //System.out.println(segment.serialize());

        return segment;
    }
}
