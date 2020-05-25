package io.sansam.point.array;

/**
 * <p>
 * P13CuttingRope
 * </p>
 *
 * @author houcb
 * @since 2020/5/25 3:50 下午
 */


// 题目：给你一根长度为n绳子，请把绳子剪成m段（m、n都是整数，n>1并且m≥1）。
// 每段的绳子的长度记为k[0]、k[1]、……、k[m]。k[0]*k[1]*…*k[m]可能的最大乘
// 积是多少？例如当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此
// 时得到最大的乘积18。

public class P14CuttingRope {

    public static void main(String[] args) {
        P14CuttingRope demo = new P14CuttingRope();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
        demo.test6();
        demo.test7();

    }

    public int maxProductAfterCutting_solution1(int length) {
        if (length <= 0) return 0;
        if (length == 1) return 0;
        if (length == 2) return 1;
        if (length == 3) return 2;

        // 初始化f(2)的时候，是为了计算n>3的情况，因此如果有长度为2的肯定至少剪了一刀，这时候长度为2的最大乘积是2，初始化f(3)也是同理。
        int[] product = new int[length + 1];
        product[0] = 0;
        product[1] = 1;
        product[2] = 2;
        product[3] = 3;

        int cur;
        int max = 2;
        for (int i = 4; i <= length; i++) {
            for (int j = 1; j <= i / 2; j++) {
                cur = product[j] * product[i - j];
                if (cur > max) {
                    max = cur;
                }
            }
            product[i] = max;
        }
        return product[length];
    }

    public int maxProductAfterCutting_solution2(int length) {
        if (length <= 0) return 0;
        if (length == 1) return 0;
        if (length == 2) return 1;
        if (length == 3) return 2;

        int timeOf3, timeOf2;
        timeOf3 = length / 3;

        if (length % 3 == 1) {
            timeOf3--;
        }
        timeOf2 = (length - (timeOf3 * 3)) / 2;
        return (int) (Math.pow(3, timeOf3) * Math.pow(2, timeOf2));
    }

    // =====测试代码======
    void test(String testName, int length, int expected) {
        if (testName != null)
            System.out.println(testName + ":");
        if (maxProductAfterCutting_solution1(length) == expected) {
            System.out.print("    动态规划:" + "passed  ");
        } else {
            System.out.print("    动态规划:" + "failed  ");
        }

        if (maxProductAfterCutting_solution2(length) == expected) {
            System.out.println("贪婪算法:" + "passed  ");
        } else {
            System.out.println("贪婪算法:" + "failed  ");
        }
    }

    void test1() {
        test("test1", 1, 0);
    }

    void test2() {
        test("test2", 2, 1);
    }

    void test3() {
        test("test3", 3, 2);
    }

    void test4() {
        test("test4", 4, 4);
    }

    void test5() {
        test("test5", 5, 6);
    }

    void test6() {
        test("test6", 10, 36);
    }

    void test7() {
        test("test7", 50, 86093442);
    }
}
