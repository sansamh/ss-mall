package io.sansam.point.array;

/**
 * <p>
 * P42GreatestSumOfSubarrays
 * </p>
 *
 * @author houcb
 * @since 2020-06-02 16:07
 */

/**
 * 题目
 * 　　输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整/数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
 * <p>
 * 思路
 * 　　分析规律，从第一个数字开始累加，若走到某一个数字时，前面的累加和为负数，说明不能继续累加了，要从当前数字重新开始累加。在累加过程中，将每次累加和的最大值记录下来，遍历完成后，返回该数字。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（输入数组有正有负，全负数，全正数）
 * <p>
 * 　　2.特殊输入测试（null）
 * <p>
 * 收获
 * 　　1.复杂度要求为O(n)，考虑是否可以从头开始遍历，找规律。
 */
public class P42GreatestSumOfSubarrays {

    public static boolean isValid = true;

    public static int findMaxSumOfSubArrays(int[] arr) {
        if (arr == null || arr.length == 0) {
            isValid = false;
            return 0;
        }
        int curSum = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (curSum <= 0) {
                curSum = arr[i];
            } else {
                curSum += arr[i];
            }
            if (curSum > sum) {
                sum = curSum;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(findMaxSumOfSubArrays(arr));

    }
}
