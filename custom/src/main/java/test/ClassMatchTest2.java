package test;

import com.lagou.pard.agent.plugin.custom.CustomPluginHook;
import com.lagou.pard.agent.plugin.intercept.context.InstanceMethodInvokeContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassMatchTest2 implements CustomPluginHook {

    public void test21(Method method) {

        String str = "test String";
        try {
            String res = (String) method.invoke(str);
            System.out.println("invoke res :" + res);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("ClassMatchTest21 test");
    }

    public void test22() {
        System.out.println("ClassMatchTest22  test3");
    }

    @Override
    public String getRealOperationName(InstanceMethodInvokeContext methodInvokeContext) {
        Class<?>[] parameterTypes = methodInvokeContext.getParameterTypes();
        for(int i=0;i<parameterTypes.length;i++){
            if(parameterTypes[i] == Method.class){
                Method m = (Method) methodInvokeContext.getArguments()[i];
                return m.getName();
            }
        }
        return null;
    }
}
