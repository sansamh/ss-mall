package io.sansam.point.array;

import java.util.Arrays;

/**
 * <p>
 * P21ReorderArray
 * </p>
 *
 * @author houcb
 * @since 2020/5/27 2:22 下午
 */


/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * <p>
 * <p>
 * 思路
 * 　　对于任意一个整数数组，设置一个指针，从前往后走，如果遇到奇数则指针后移，遇到偶数时，希望把该偶数放在数组后面；因此，再设置一个指针，从后往前走，遇到偶数时指针前移，遇到奇数时，则恰好可以与前面的指针所指的偶数进行调换。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（数组中奇偶数交替出现；数组中先奇数后偶数；数组中先偶数后奇数）
 * <p>
 * 　　2.特殊测试（null，空数组，一个数据的数组）
 */
public class P21ReorderArray {

    public static void reOrderArray(int[] array) {
        if (array == null || array.length <= 1) return;

        int ps = 0;
        int pe = array.length - 1;
        int temp;
        while (ps < pe) {
            while (ps < array.length && (array[ps] & 1) != 0) {
                ps++;
            }
            while (pe >= 0 && (array[pe] & 1) == 0) {
                pe--;
            }
            if (ps < pe) {
                temp = array[ps];
                array[ps] = array[pe];
                array[pe] = temp;
            }
        }
    }

    public static void main(String[] args) {
        P21ReorderArray demo = new P21ReorderArray();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
        demo.test6();
        demo.test7();
    }

    /**
     * 采用类插入排序 保证奇偶数相对位置不变
     *
     * @param array
     */
    public static void reOrderArray2(int[] array) {
        if (array == null || array.length < 2) return;
        int temp, j;
        for (int i = 0; i < array.length; i++) {
            // 奇数
            if ((array[i] & 1) != 0) {
                j = i;
                temp = array[j];
                while (j > 0 && (array[j - 1] & 1) == 0) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = temp;
            }
        }
    }

    void test1() {
        int[] array = null;
        System.out.println("原始数组：" + Arrays.toString(array));
        reOrderArray(array);
        System.out.println("调整结果：" + Arrays.toString(array));
        System.out.println();
    }

    void test2() {
        int[] array = {};
        System.out.println("原始数组：" + Arrays.toString(array));
        reOrderArray(array);
        System.out.println("调整结果：" + Arrays.toString(array));
        System.out.println();
    }

    void test3() {
        int[] array = {-2, 4, -6, 1, -3, 5};
        System.out.println("原始数组：" + Arrays.toString(array));
        reOrderArray(array);
        System.out.println("调整结果：" + Arrays.toString(array));
        System.out.println();
    }

    void test4() {
        int[] array = {-1, 3, -5, 2, -4, 6};
        System.out.println("原始数组：" + Arrays.toString(array));
        reOrderArray(array);
        System.out.println("调整结果：" + Arrays.toString(array));
        System.out.println();
    }

    void test5() {
        int[] array = {-1, 2, -3, 4, -5, 6};
        System.out.println("原始数组：" + Arrays.toString(array));
        reOrderArray(array);
        System.out.println("调整结果：" + Arrays.toString(array));
        System.out.println();
    }

    void test6() {
        int[] array = {2, 2, 1, 3, 4, 1};
        System.out.println("原始数组：" + Arrays.toString(array));
        reOrderArray(array);
        System.out.println("调整结果：" + Arrays.toString(array));
        System.out.println();
    }

    void test7() {
        int[] array = {1};
        System.out.println("原始数组：" + Arrays.toString(array));
        reOrderArray(array);
        System.out.println("调整结果：" + Arrays.toString(array));
        System.out.println();
    }
}
