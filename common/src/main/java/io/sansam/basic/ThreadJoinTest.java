package io.sansam.basic;

/**
 * <p>
 * ThreadJoinTest
 * </p>
 *
 * @author houcb
 * @since 2020-07-22 11:29
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("main thread start");

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("sub thread work over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread.join();

        System.out.println("main thread over");

    }
}
