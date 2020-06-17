package io.sansam.basic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

interface Subject {

    String sayHello(String name, LocalDateTime time);

    Map<String, Object> sayGoodBye(String name, LocalDateTime time);
}

/**
 * <p>
 * MyDynamicProxy
 * </p>
 *
 * @author houcb
 * @since 2020-06-16 17:30
 */
public class MyDynamicProxyJDK {

    public static void main(String[] args) {

        Subject real = new RealSubject();

        // handler执行真正调用的方法
        MyInvocationHandler handler = new MyInvocationHandler(real);

        Subject proxy = (Subject) Proxy.newProxyInstance(real.getClass().getClassLoader(),
                real.getClass().getInterfaces(),
                handler);

        String name = "张三";

        System.out.println(proxy.sayHello(name, LocalDateTime.now()));

        System.out.println(proxy.sayGoodBye(name, LocalDateTime.of(2020, 6, 26, 20, 0)));

    }

}

class RealSubject implements Subject {

    private Map<String, Object> timeMap = new HashMap<>();

    @Override
    public String sayHello(String name, LocalDateTime time) {
        timeMap.put(name, time);
        return "hello " + name + " success!";
    }

    @Override
    public Map<String, Object> sayGoodBye(String name, LocalDateTime time) {
        timeMap.put("workTime", Duration.between(time, (LocalDateTime) timeMap.get(name)).toHours());
        return timeMap;
    }
}

class MyInvocationHandler implements InvocationHandler {

    private Object subject;

    public MyInvocationHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用打卡前做日志记录");
        System.out.println(method);

        Object res = method.invoke(subject, args);

        System.out.println("调用打卡后做日志记录");

        return res;
    }
}
