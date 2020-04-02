package io.sansam.config.controller;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * <p>
 * LeakyBucket 漏桶 桶中请求随着时间而减少
 * 规定固定容量的桶,有水进入,有水流出. 对于流进的水（请求的数量）我们无法估计进来的数量、速度,对于流出的水我们可以控制速度.
 * </p>
 *
 * @author houcb
 * @since 2020/4/2 4:53 下午
 */
@Slf4j
public class LeakyBucket {

    /**
     * 桶中目前的数量
     */
    private static AtomicDouble currentSize = new AtomicDouble(0d);
    /**
     * 上次获取token 的时间
     */
    private long time;
    /**
     * 桶的容量
     */
    private double total;
    /**
     * 桶中请求处理的速率 per second
     */
    private double rate;
    private Lock lock = new ReentrantLock();

    public LeakyBucket(double total, double rate) {
        this.total = total;
        this.rate = rate;
        // 生成桶的时间
        this.time = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        LeakyBucket bucket = new LeakyBucket(3, 1);

        IntStream.range(0, 3).forEach((i -> new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(20) * 100);
                } catch (InterruptedException e) {
                    log.error("获取token线程异常", e);
                }
                final boolean limit = bucket.limit();
                log.info("线程[" + Thread.currentThread().getName() + "]获取令牌 " + limit);
            }
        }).start()));
    }

    /**
     * 获取当前桶中请求数量 桶中请求随着时间而减少
     * currentSize - (上次获取的时间到现在应该减少多少请求)
     * currentSize - （(now - time) * rate）
     * 如果currentSize + 1小于total，则取currentSize++ 请求可以处理 返回true
     *
     * @return boolean
     */
    public boolean limit() {
        lock.lock();

        long now = System.currentTimeMillis();
        double c = currentSize.addAndGet(-(now - time) / 1000d * rate);
        currentSize.set(Math.max(0, c));
        time = now;
        log.info(System.currentTimeMillis() + " 桶中请求数量 " + currentSize.get());
        try {
            log.info(System.currentTimeMillis() + " lock后桶中请求数量 " + currentSize.get());
            if (currentSize.doubleValue() + 1 < total) {
                currentSize.set(currentSize.addAndGet(1d));
                return true;
            } else {
                return false;
            }
        } finally {
            log.info(System.currentTimeMillis() + " 释放lock后桶中请求数量 " + currentSize.get());
            lock.unlock();
        }
    }
}
