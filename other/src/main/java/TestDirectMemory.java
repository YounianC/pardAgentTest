import java.nio.ByteBuffer;

public class TestDirectMemory {

    public static void main(String[] args) {
        // -verbose:gc -XX:+PrintGCDetails -XX:MaxDirectMemorySize=40M -XX:+DisableExplicitGC,也会报OOM(Direct buffer memory)
        while (true) {
            ByteBuffer.allocateDirect(10 * 1024 * 1024);
        }
    }
}