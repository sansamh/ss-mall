package io.sansam.point.array;

/**
 * <p>
 * P59MaxInSlidingWindow
 * </p>
 *
 * @author houcb
 * @since 2020-06-09 14:56
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 题目
 * 　　给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。例如，如果输入数组{2, 3, 4, 2, 6, 2, 5, 1}及滑动窗口的大小3，那么一共存在6个滑动窗口，它们的最大值分别为{4, 4, 6, 6, 6, 5}
 * <p>
 * 思路
 * 　　蛮力直接在每个滑动窗口依次比较找出最大值，时间复杂度太高。
 * <p>
 * 　　我们考虑把每个可能成为最大值的数字记录下来，就可以快速的得到最大值。
 * <p>
 * 　　思路：建立一个两端开口的队列，放置所有可能是最大值的数字（存放的其实是对应的下标），且最大值位于队列开头。从头开始扫描数组，
 * <p>
 * 　　如果遇到的数字比队列中所有的数字都大，那么它就是最大值，其它数字不可能是最大值了，将队列中的所有数字清空，放入该数字，该数字位于队列头部；
 * <p>
 * 　　如果遇到的数字比队列中的所有数字都小，那么它还有可能成为之后滑动窗口的最大值，放入队列的末尾；
 * <p>
 * 　　如果遇到的数字比队列中最大值小，最小值大，那么将比它小数字不可能成为最大值了，删除较小的数字，放入该数字。
 * <p>
 * 　　由于滑动窗口有大小，因此，队列头部的数字如果其下标离滑动窗口末尾的距离大于窗口大小，那么也删除队列头部的数字。
 * <p>
 * 　　注：队列中存放的是下标，以上讲的 队列头部的数字 均指 队列头部的下标所指向的数字。写代码时不要弄混了。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（数组数字递增、递减、无序）
 * <p>
 * 　　2.边界值测试（滑动窗口大小位0、1、大于或者等于数组长度）
 * <p>
 * 　　3.特殊输入测试（null）
 */
public class P59MaxInSlidingWindow {

    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> max = new ArrayList<Integer>();
        if (num == null || num.length <= 0 || size <= 0 || size > num.length)
            return max;
        ArrayDeque<Integer> indexDeque = new ArrayDeque<Integer>();

        // 假设num= {2, 4, 2, 2, 6, 2, 5, 1} size = 3 则先找到第0位和第1位之间的最大值的下标 即1
        for (int i = 0; i < size - 1; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
        }
        // 从第size - 1位开始
        for (int i = size - 1; i < num.length; i++) {
            // 先从尾部开始清除小于num[i]的值
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            // 如果i - 第一个值的下标已经大于等于 size了 因为要插入一个数据 所以必须清除第一个元素
            if (!indexDeque.isEmpty() && (i - indexDeque.getFirst()) >= size) {
                indexDeque.removeFirst();
            }
            // 将此下标插入
            indexDeque.addLast(i);
            // 获取此区间的最大值
            max.add(num[indexDeque.getFirst()]);

        }


        return max;
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 2, 2, 6, 2, 5, 1};
        System.out.println(maxInWindows(nums, 3));
    }
}
