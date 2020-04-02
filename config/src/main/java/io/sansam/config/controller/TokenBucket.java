package io.sansam.config.controller;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * <p>
 * TokenBucket 令牌桶
 * 规定固定容量的桶,token 以固定速度往桶内填充,当桶满时 token 不会被继续放入,每过来一个请求把 token 从桶中移除,如果桶中没有 token 不能请求
 * </p>
 *
 * @author houcb
 * @since 2020/4/2 3:29 下午
 */
@Slf4j
public class TokenBucket {

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
     * 生成token的速率 per second
     */
    private double rate;
    private Lock lock = new ReentrantLock();

    public TokenBucket(double total, double rate) {
        this.total = total;
        this.rate = rate;
        // 生成桶的时间
        this.time = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        TokenBucket bucket = new TokenBucket(3, 1);

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
     * 获取当前桶中token数量
     * currentSize + (上次获取token的时间到现在应该生成多少令牌)
     * currentSize + (now - time) * rate
     * 如果currentSize超过了总数total，则取currentSize = total
     *
     * @return boolean
     */
    public boolean limit() {
        lock.lock();

        long now = System.currentTimeMillis();
        double c = currentSize.addAndGet((now - time) / 1000d * rate);
        time = now;
        currentSize.set(Math.min(c, total));
        log.info(System.currentTimeMillis() + " 桶中token数量 " + currentSize.get());
        try {
            log.info(System.currentTimeMillis() + " lock后桶中token数量 " + currentSize.get());
            if (currentSize.get() < 1) {
                return false;
            } else {
                currentSize.addAndGet(-1d);
                return true;
            }
        } finally {
            log.info(System.currentTimeMillis() + " 释放lock后桶中token数量 " + currentSize.get());
            lock.unlock();
        }

    }

}
