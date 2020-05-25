package io.sansam.point.array;

/**
 * <p>
 * P10Fibonacci
 * </p>
 *
 * @author houcb
 * @since 2020/5/21 3:13 下午
 */
public class P10Fibonacci {

    public static void main(String[] args) {
        System.out.println(fib1(20));
//        System.out.println(fib2(100));
    }

    public static long fib1(long n) {
        if (n < 0) return -1;
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    public static long fib2(long n) {
        if (n < 0) return -1;
        if (n == 0) return 0;
        if (n == 1) return 1;
        long res = 0, first = 0, second = 1;
        for (long i = 2; i <= n; i++) {
            res = first + second;
            first = second;
            second = res;
        }
        return res;
    }
}
