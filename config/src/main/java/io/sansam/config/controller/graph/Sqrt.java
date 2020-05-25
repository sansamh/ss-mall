package io.sansam.config.controller.graph;

/**
 * <p>
 * Sqrt
 * </p>
 *
 * @author houcb
 * @since 2020/5/9 11:02 上午
 */
public class Sqrt {

    public static void main(String[] args) {
        System.out.println(sqrt(8));
        System.out.println(Math.sqrt(8));
        System.out.println(2 + ((3 - 2) / 2));
    }

    public static int sqrt(int x) {
//        if (x == 0 || x == 1) return x;
//        long l = 0L;
//        long mid = 0L;
//        long r = x;
//        while (l <= r) {
//            mid = l + ((r - l) >> 1);
//            if (mid*mid == x) {
//                return (int)mid;
//            }
//            else if (mid*mid > x) {
//                r = mid - 1;
//            }
//            else {
//                l = mid ;
//            }
//        }
//
//        return (int)mid;

        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;

    }
}
