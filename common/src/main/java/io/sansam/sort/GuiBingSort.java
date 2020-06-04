package io.sansam.sort;

import io.sansam.util.SortUtil;

/**
 * <p>
 * GuiBingSort
 * </p>
 *
 * @author houcb
 * @since 2020-06-04 15:50
 */
public class GuiBingSort implements Sort {

    public static void main(String[] args) {
        Sort sort = new GuiBingSort();
        int[] array = {2, 5, 7, 3, 1, 9, 8, 4, 6};
        sort.sort(array);
    }

    /**
     * 归并排序是一种概念上最简单的排序算法，与快速排序一样，归并排序也是基于分治法的。
     * 归并排序将待排序的元素序列分成两个长度相等的子序列，为每一个子序列排序，
     * 然后再将他们合并成一个子序列。合并两个子序列的过程也就是两路归并。
     *
     * @param array
     */

    @Override
    public void sort(int[] array) {
        if (array == null || array.length <= 0) {
            System.out.println("error input array");
        }
        if (array.length == 1) {
            SortUtil.printArray(array);
        }

        helper(array, 0, array.length - 1);

        SortUtil.printArray(array);
    }

    private void helper(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;
        // 排序左边
        helper(array, start, mid);
        // 排序右边
        helper(array, mid + 1, end);

        // 合并
        int i = mid;
        int j = end;
        int[] temp = new int[end - start + 1];
        int k = end - start;

        while (i >= start && j >= mid + 1) {
            if (array[i] > array[j]) {
                temp[k] = array[i];
                i--;
            } else {
                temp[k] = array[j];
                j--;
            }
            k--;
        }

        while (i >= start) {
            temp[k--] = array[i--];
        }
        while (j >= mid + 1) {
            temp[k--] = array[j--];
        }

        // 复制temp到元素组
        for (int n = 0; n < temp.length; n++) {
            array[n + start] = temp[n];
        }

    }
}
