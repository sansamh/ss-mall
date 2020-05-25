package io.sansam.point.array;

/**
 * <p>
 * P16Power
 * </p>
 *
 * @author houcb
 * @since 2020/5/25 4:45 下午
 */
public class P16Power {

    boolean IsInvalid = false;

    private static double calc(double base, int absExponect) {
        if (absExponect == 0) return 1;
        if (absExponect == 1) return base;

        double sum;
//        for (int i = 0; i < absExponect; i++) {
//            sum *= base;
//        }

        // x的32次方等于 x的16次方 的平方
        // x的31次方等于 x的15次方 的平方 乘以 x
        sum = calc(base, absExponect >> 1);
        sum *= sum;

        // 奇偶判断
        if ((absExponect & 0x1) == 1) {
            // 奇数
            sum *= base;
        }

        return sum;
    }

    public static void main(String[] args) {
        P16Power demo = new P16Power();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
        demo.test6();
        demo.test7();
    }

    // 这道题很容易实现，但需要注意以下陷阱：
    // 1）0的负数次方不存在；
    // 2）0的0次方没有数学意义；
    // 3）要考虑exponent为负数的情况。所以可以对exponent进行分类讨论，在对base是否为0进行讨论。
    public double power(double base, int exponent) {
        IsInvalid = false;

        if (exponent > 0) {
            return calc(base, exponent);
        } else if (exponent < 0) {
            if (base == 0) {
                IsInvalid = true;
                return 0;
            }
            return 1 / calc(base, -exponent);
        } else {
            return 1;
        }
    }

    // ========测试代码========
    void test(String testName, double base, int exponent, double expected, boolean expectedFlag) {
        if (testName != null)
            System.out.print(testName + ":");
        if (power(base, exponent) == expected && IsInvalid == expectedFlag) {
            System.out.println("passed.");
        } else {
            System.out.println("failed.");
        }
    }

    void test1() {
        test("test1", 0, 6, 0, false);
    }

    void test2() {
        test("test2", 0, -6, 0, true);
    }

    void test3() {
        test("test3", 0, 0, 1, false);
    }

    void test4() {
        test("test4", 2, 6, 64, false);
    }

    void test5() {
        test("test5", 2, -3, 0.125, false);
    }

    void test6() {
        test("test6", 5, 0, 1, false);
    }

    void test7() {
        test("test7", -2, 6, 64, false);
    }

}
