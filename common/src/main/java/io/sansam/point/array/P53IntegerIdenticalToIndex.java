package io.sansam.point.array;

/**
 * <p>
 * P53IntegerIdenticalToIndex
 * </p>
 *
 * @author houcb
 * @since 2020-06-05 10:47
 */

/**
 * 题目
 * 　　假设一个单调递增的数组里的每个元素都是整数并且是唯一的。请编程实现一个函数找出数组中任意一个数值等于其下标的元素。例如，在数组{-3, -1,1, 3, 5}中，数字3和它的下标相等。
 * <p>
 * 思路
 * 　　同53-1和53-2一样，不再从头到尾遍历，由于是排序数组，我们继续考虑使用二分查找算法：
 * <p>
 * 　　  1）当中间数字等于其下标时，中间数字即为所求数字；
 * <p>
 * 　　  2）当中间数字大于其下标时，在左半部分区域寻找；
 * <p>
 * 　　  2）当中间数字小于其下标时，在右半部分区域寻找；
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（包含/不包含与下标相等的数字）
 * <p>
 * 　　2.边界值测试（数字位于数组开头、中间或者结尾；仅一个数字数组）
 * <p>
 * 　　2.特殊测试（null）
 * <p>
 * 收获
 * 　　1.对于在排序数组中查找某些特定的数字，可以对二分法稍加改造，实现所需的功能。
 */
public class P53IntegerIdenticalToIndex {

    public static int getNumberSameAsIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] == mid) {
                return mid;
            } else if (arr[mid] < mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-3, -1, 1, 3, 5};
        System.out.println(getNumberSameAsIndex(arr));
    }
}
