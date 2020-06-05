package io.sansam.point.array;

/**
 * <p>
 * P53NumberOfK
 * </p>
 *
 * @author houcb
 * @since 2020-06-05 09:43
 */

/**
 * 题目
 * 　　统计一个数字在排序数组中出现的次数。例如输入排序数组{1, 2, 3, 3,3, 3, 4, 5}和数字3，由于3在这个数组中出现了4次，因此输出4。
 * <p>
 * 思路
 * 　　分析：对于例子来说，如果采用二分法找到某一个3后，再往前遍历和往后遍历到第一个和最后一个3，在长度为n的数组中有可能出现O(n)个3，因此这样的扫描方法时间复杂度为O(n)，效率与从头到尾扫描一样，速度太慢。
 * <p>
 * 　　这题关键是找到第一个和最后一个3，因此我们尝试改进二分法：中间数字比3大或者小的情况与之前类似，关键是中间数字等于3的情况，这时可以分类讨论如下：
 * <p>
 * 　　1）如果中间数字的前一个数字也等于3，说明第一个3在前面，继续在前半段查找第一个3；
 * <p>
 * 　　2）如果中间数字的前一个数字不等于3，说明该位置是第一个3；
 * <p>
 * 　　3）如果中间数字的后一个数字也等于3，说明最后一个3在后面，继续在后半段查找最后一个3；
 * <p>
 * 　　2）如果中间数字的后一个数字不等于3，说明该位置是最后一个3；
 * <p>
 * <p>
 * <p>
 * 附加：牛客网上还有一种算法：如果找数字k的次数，由于数组是整数，可以直接找k-0.5和k+0.5应该在数组中哪个位置，这种方法就不用讨论这么多情况了。（不过double类型的大小比较不知道是否会增加太多时间消耗）。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（数字出现次数为0、1、2等）
 * <p>
 * 　　2.边界值测试（数组只有一个数字，查找数字为第一个或者最后一个）
 * <p>
 * 　　2.特殊测试（null）
 */
public class P53NumberOfK {

    public static int getNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int first = getFirst(array, 0, array.length - 1, k);
        if (first < 0) {
            return 0;
        }
        int last = getLast(array, first, array.length - 1, k);
        return last - first + 1;
    }

    private static int getLast(int[] array, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) >> 1;
        if (array[mid] == k) {
            // mid为最后一位 或者 mid-1位不等于k 则mid为最后一位k
            if (mid == array.length - 1 || array[mid + 1] != k) {
                return mid;
            }
            // 否则继续往后找k
            else {
                start = mid + 1;
            }
        }
        // 中位数小于k 则在中位数右边找
        else if (array[mid] < k) {
            start = mid + 1;
        }
        // 中位数大于k 则在左边找
        else {
            end = mid - 1;
        }

        return getLast(array, start, end, k);
    }

    private static int getFirst(int[] array, int start, int end, int k) {
        // 根本没找到k
        if (start > end) {
            return -1;
        }
        int mid = (start + end) >> 1;
        if (array[mid] == k) {
            // mid为第一位 或者 mid-1位不等于k 则mid为第一位k
            if (mid == 0 || array[mid - 1] != k) {
                return mid;
            }
            // 否则继续在前半部分寻找第一位k
            else {
                end = mid - 1;
            }
        }
        // 中位数比k小 则第一位k在右边
        else if (array[mid] < k) {
            start = mid + 1;
        }
        // 中位数大于k 左边寻找
        else {
            end = mid - 1;
        }
        return getFirst(array, start, end, k);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(array, 3));
    }
}
