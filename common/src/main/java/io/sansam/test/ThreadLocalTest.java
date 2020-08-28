package io.sansam.test;

import sun.misc.VM;

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
     *
     * ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
     *             table = new Entry[INITIAL_CAPACITY];
     *             int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
     *             table[i] = new Entry(firstKey, firstValue);
     *             size = 1;
     *             setThreshold(INITIAL_CAPACITY);
     *         }
     * threadlocalmap 底层是一个Entry数组 初始size为16 将当前threadlocal和val构建一个entry对象 根据threadlocal的hashcode取模找到数组下标
     *
     * entry为一个weakrefrence对象 当key当前线程threadlocal空间被gc时 val可能为强引用不会被gc 导致内存泄漏
     */

    ThreadLocal<Integer> integerThreadLocal = ThreadLocal.withInitial(() -> new Random().nextInt());
    ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "hello world");

    public static void main(String[] args) {
        new ThreadLocalTest().doTest();
        System.out.println(VM.maxDirectMemory() / 1024 / 1024);
    }

    public void doTest() {
        Integer integer = integerThreadLocal.get();
        String s = stringThreadLocal.get();
        System.out.println(s + "\t" + integer);

    }

}
