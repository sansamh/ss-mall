package io.sansam.point.array;

import io.sansam.util.ArrayUtil;

/**
 * <p>
 * FindDuplication
 * </p>
 *
 * @author houcb
 * @since 2020/5/14 5:11 下午
 */
public class FindDuplication {

    public static int getDuplicate(int[] arr) {
        int res = -1;
        if (arr == null) {
            return res;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 || arr[i] >= arr.length) {
                return res;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) {
                    return arr[i];
                }
                ArrayUtil.swap(arr, i, arr[i]);
            }

        }

        return res;
    }

    // ==================================测试代码==================================

    public static void main(String[] args) {
        FindDuplication f = new FindDuplication();
        f.test1();
        f.test2();
        f.test3();
        f.test4();
    }

    /**
     * 数组为null
     */
    public void test1() {
        System.out.print("test1：");
        int[] a = null;
        int dup = getDuplicate(a);
        System.out.println("重复数字为：" + dup);
    }

    /**
     * 数组无重复数字
     */
    public void test2() {
        System.out.print("test2：");
        int[] a = {0, 1, 2, 3};
        int dup = getDuplicate(a);
        System.out.println("重复数字为：" + dup);
    }

    /**
     * 数组数字越界
     */
    public void test3() {
        System.out.print("test3：");
        int[] a = {1, 2, 3, 4};
        int dup = getDuplicate(a);
        System.out.println("重复数字为：" + dup);
    }

    /**
     * 数组带重复数字
     */
    public void test4() {
        System.out.print("test4：");
        int[] a = {1, 2, 3, 2, 4};
        int dup = getDuplicate(a);
        System.out.println("重复数字为：" + dup);
    }

}
