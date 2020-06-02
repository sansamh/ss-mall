package io.sansam.point.array;

import java.util.ArrayList;

/**
 * <p>
 * P40KLeastNumbers
 * </p>
 *
 * @author houcb
 * @since 2020-06-01 17:01
 */

/**
 * 题目
 * 　　输入n个整数，找出其中最小的k个数。例如输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * 思路
 * 　　思路一：同剑指offer(39) 数组中出现次数超过一半的数字中使用partition()方法，基于数组的第k个数字调整，使得更小的k个数字都在数组左边即可。
 * <p>
 * 　　思路二：依次遍历n个整数，用一个容器存放最小的k个数字，每遇到比容器中最大的数字还小的数字时，将最大值替换为该数字。容器可以使用最大堆或者红黑树来实现。本文根据堆排序的原理来实现。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（数组中存在/不存在重复数字）
 * <p>
 * 　　2.边界值测试（k=1或者等于数组长度）
 * <p>
 * 　　2.特殊测试（null、k<1、k大于数组长度）
 *
 * 收获
 * 　　1.k小于等于0的情况别忘记了
 *
 * 　　2.方法二，只需要在原始数组中进行读入操作，而所有的写操作和判断都是在容器中进行的，不用反复读取原始数组，思想非常好。
 *
 * 　　3.记得要弄清楚是否可以改变原始输入的数组。
 *
 * 　　4.partition函数：即是快速排序的基础，也可以用来查找n个数中第k大的数字。
 *
 * 　　5.当涉及到频繁查找和替换最大最小值时，二叉树是非常合适的数据结构，要能想到堆和二叉树。
 *
 * 　　6. PriorityQueue重点：
 *
 * 　　　　* 大顶堆与小顶堆的构建: new Comporator()
 *
 * 　　　　* 如何插值: heap.offer(e)
 *
 * 　　　　* 如何获取堆顶的值: heap.peek(), heap.poll()
 *
 */
public class P40KLeastNumbers {

    /**
     * partion函数
     *
     * @param input
     * @param k
     * @return
     */
    public static ArrayList<Integer> getLeastNumbers_Solution1(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || k <= 0 || input.length < k) {
            return list;
        }
        int low = 0;
        int high = input.length - 1;
        int index = partion(input, low, high);

        while (index != k - 1) {
            if (index < k - 1) {
                low = index + 1;
            } else {
                high = index - 1;
            }
            index = partion(input, low, high);
        }

        while (index >= 0) {
            list.add(input[index--]);
        }
        return list;
    }

    private static int partion(int[] input, int low, int high) {
        int base = input[low];
        while (low < high) {
            while (low < high && input[high] >= base) {
                high--;
            }
            int temp = input[high];
            input[high] = input[low];
            input[low] = temp;

            while (low < high && input[low] <= base) {
                low++;
            }
            temp = input[low];
            input[low] = input[high];
            input[high] = temp;

        }

        return low;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 6, 2, 7, 3, 8};
        System.out.println(getLeastNumbers_Solution1(arr, 4));
    }

}
