package io.sansam.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>
 * ExeTest
 * </p>
 *
 * @author houcb
 * @since 2020-07-03 17:13
 */
public class ExeTest {


    /**
     * submit runnable/callable 出现异常 如果不调用future.get() 则异常被吞没 并且导致线程阻塞
     */

    static ExecutorService pool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        testSubmit();
    }

    public static void testSubmit() {
//        Future<?> future = pool.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("submit runnable");
//                int i = 1 / 0;
//            }
//        });

        Future<?> future = pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("submit ruCallablennable");
                int i = 1 / 0;
                return i;
            }
        });

//        Object o = null;
//        try {
//            o = future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println(o.toString());


    }
}
