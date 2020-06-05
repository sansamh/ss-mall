package io.sansam.point.array;

/**
 * <p>
 * P53MissingNumber
 * </p>
 *
 * @author houcb
 * @since 2020-06-05 10:35
 */

/**
 * 题目
 * 　　一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0到n-1之内。在范围0到n-1的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * <p>
 * 思路
 * 　　分析易知，数组形式如下：
 * <p>
 * <p>
 * <p>
 * 　　如果从头到尾依次比较值与小标是否相等，时间复杂度为O(n)，效率低。
 * <p>
 * 　　由于是排序数组，我们继续考虑使用二分查找算法，结合上图可知：
 * <p>
 * 　　　　当中间数字等于其下标时，我们在后半部分查找；
 * <p>
 * 　　　　当中间数字不等于其下标时，
 * <p>
 * 　　　　1）如果中间数字的前一个数字也不等于其下标，则在前半部分查找；
 * <p>
 * 　　　　2）如果中间数字的前一个数字等于其下标，则说明中间数字的下标即为我们所要找的数字。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（缺失数字位于数组开头、中间或者结尾）
 * <p>
 * 　　2.边界值测试（数字只有0或1）
 * <p>
 * 　　2.特殊测试（null）
 */
public class P53MissingNumber {

    public static int getMissingNumber(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = low + ((high - low) >> 1);
            // arr[m] != m 判断m是否为0或者arr[m-1] == m-1的话 m就是要找的
            if (arr[mid] != mid) {
                if (mid == 0 || arr[mid - 1] == mid - 1) {
                    return mid;
                }
                // 否则的话向前寻找
                else {
                    high = mid - 1;
                }
            }
            // arr[m] == m 则不匹配的元素在右边
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 4, 5, 6};
        System.out.println(getMissingNumber(arr));
    }
}
