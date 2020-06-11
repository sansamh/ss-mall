package io.sansam.point.array;

/**
 * <p>
 * P57TwoNumbersWithSum
 * </p>
 *
 * @author houcb
 * @since 2020-06-08 17:12
 */

import java.util.ArrayList;

/**
 * 题目
 * 　　输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，输出任意一对即可。
 * <p>
 * 思路
 * 　　从头开始遍历数字，确定一个数字后，对后面的数字遍历，判断和是否为s，这种方法复杂度为O(n^2)，效率太低。
 * <p>
 * 　　我们考虑到，如果一个数字比较小，那么另一个数字一定比较大，同时数字为递增排列；所以，我们设置两个指针，一个指针small从第一个数字（最小）出发，另一个指针big从最后一个数字（最大）出发：
 * <p>
 * 　　当small加big的和小于s时，只需要将small指向后一个数字（更大），继续判断；
 * <p>
 * 　　当small加big的和大于s时，只需要将big指向前一个数字（更小），继续判断；
 * <p>
 * 　　当small加big的和等于s时，求解完成。
 * <p>
 * 　　由于是从两边往中间移动，所以不会有跳过的情况，时间复杂度为O(n)。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（存在/不存在和为s的一对数字）
 * <p>
 * 　　2.特殊输入测试（null）
 */
public class P57TwoNumbersWithSum {

    public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array == null || array.length == 0) {
            return list;
        }
        int left = 0;
        int right = array.length - 1;
        int ans;
        while (left < right) {
            ans = array[left] + array[right];
            if (ans > sum) {
                right--;
            } else if (ans < sum) {
                left++;
            } else {
                // 如果数字相等 继续让left++ 求出所有解
                list.add(left);
                list.add(right);
                left++;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 4, 6, 9, 11, 15};
        int sum = 10;
        System.out.println(findNumbersWithSum(array, sum));
    }
}
