import test.ClassMatchTest1;
import test.ClassMatchTest2;

import java.lang.reflect.Method;

public class ClassMatchTestMain {

    public static void main(String[] args) {
        ClassMatchTest1 classMatchTest1 = new ClassMatchTest1();
        final ClassMatchTest2 classMatchTest2 = new ClassMatchTest2();

        Method method = null;
        try {
            method = String.class.getMethod("toString");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        while (true) {
            //classMatchTest1.test11();
            //classMatchTest1.test12();
            classMatchTest2.test21(method);


            /*final Method finalMethod = method;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    classMatchTest2.test21(finalMethod);
                }
            }).start();*/

           try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
