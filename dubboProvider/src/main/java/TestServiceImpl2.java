import Service.TestService2;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/11.
 */
public class TestServiceImpl2 implements TestService2 {

    @Override
    public String getData(int count) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "DATA:" + new Date().toString();
    }
}
