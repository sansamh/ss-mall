package io.sansam.basic;

/**
 * <p>
 * Worker
 * </p>
 *
 * @author houcb
 * @since 2020-08-17 17:30
 */
public class Worker {

    static {
        System.out.println("worker static块执行");
    }

    Worker() {
        System.out.println("worker 初始化方法执行");
    }
}
