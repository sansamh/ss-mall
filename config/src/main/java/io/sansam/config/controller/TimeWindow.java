package io.sansam.config.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

/**
 * <p>
 * TimeWindow
 * </p>
 *
 * @author houcb
 * @since 2020/4/2 2:18 下午
 */
@Slf4j
public class TimeWindow {

    /**
     * token容器
     */
    private static ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
    /**
     * 时间段内最大请求数
     */
    private long max;
    /**
     * 时间段间隔
     */
    private int seconds;

    public TimeWindow(int max, int seconds) {
        this.max = max;
        this.seconds = seconds;
        new Thread(() -> {
            while (true) {
                try {
                    // 清理数据间隔为 窗口期/2
                    Thread.sleep((seconds / 2) * 1000L);
                } catch (InterruptedException e) {
                    log.error("清理token异常", e);
                }
                cleanInvalidToken();
                log.info("[" + System.currentTimeMillis() + "]清理Token结束 size = " + queue.size());

            }
        }).start();
    }

    public static void main(String[] args) {
        TimeWindow timeWindow = new TimeWindow(3, 3);
        IntStream.range(0, 3).forEach((i -> new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(new Random().nextInt(20) * 100);
                        } catch (InterruptedException e) {
                            log.error("获取token异常", e);
                        }
                        log.info("线程[" + Thread.currentThread().getName() + "]开始获取令牌");
                        boolean token = timeWindow.takeToken();
                        log.info("线程[" + Thread.currentThread().getName() + "]获取令牌 " + token);

                    }
                }).start())

        );
    }

    /**
     * 清理过期token
     */
    private void cleanInvalidToken() {
        queue.forEach(x -> log.info("当前有效token[" + x + "]"));
        long now = System.currentTimeMillis();
        log.info("清理有效期小于[" + now + "]的token");
        queue.removeIf(x -> x + seconds * 1000 <= now);
    }

    /**
     * 获取令牌
     *
     * @return boolean
     */
    public boolean takeToken() {
        if (sizeOfValidToken() >= max) {
            return false;
        }
        synchronized (queue) {
            if (sizeOfValidToken() >= max) {
                return false;
            }
            return queue.add(System.currentTimeMillis());
        }
    }

    /**
     * 获取当前时间点到seconds之间的有效token数
     *
     * @return long
     */
    private long sizeOfValidToken() {
        long point = System.currentTimeMillis() - seconds * 1000L;
        long count = queue.stream().map(x -> x >= point).count();
        log.info("时间段[" + point + "]到现在有线程数[" + count + "]");
        return count;
    }
}
