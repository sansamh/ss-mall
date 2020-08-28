package io.sansam.test;

import java.util.Random;

/**
 * <p>
 * ThreadLocalTest
 * </p>
 *
 * @author houcb
 * @since 2020-08-28 17:04
 */
public class ThreadLocalTest {

    /**
     * threadlocal.get() 获取当前线程里的threadlocalmap对象 如果不存在则创建一个new ThreadLocalMap(this, firstValue) this是当前threadlocal对象
     * 获取到ThreadLocalMap对象之后，再根据当前threadlocal对象获取对应的值
     */

    ThreadLocal<Integer> integerThreadLocal = ThreadLocal.withInitial(() -> new Random().nextInt());
    ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "hello world");

    public static void main(String[] args) {
        new ThreadLocalTest().doTest();
    }

    public void doTest() {
        Integer integer = integerThreadLocal.get();
        String s = stringThreadLocal.get();
        System.out.println(s + "\t" + integer);

    }

}
