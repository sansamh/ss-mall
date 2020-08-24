package io.sansam.basic;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>
 * ClassLoaderTest
 * </p>
 *
 * @author houcb
 * @since 2020-08-17 17:25
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

//        loader.loadClass("io.sansam.basic.Worker");
//        System.out.println("------------");
//
//        Class.forName("io.sansam.basic.Worker");
//        System.out.println("------------");
//
//        Class.forName("io.sansam.basic.Worker", true, loader);
//
//
        BigDecimal start = new BigDecimal(1);
        BigDecimal end = new BigDecimal(200000);
        BigDecimal stop = new BigDecimal(0.1);

        int step = 0;
        while (start.compareTo(end) < 0) {
            step++;
            start = NumberUtil.add(start, NumberUtil.mul(start, stop)).setScale(3, RoundingMode.HALF_DOWN);
            System.out.println("step = [" + step + "]" + " money = [" + start.toPlainString() + "]");
        }
        System.out.println(step);
    }
}
