package io.sansam.sort;

import java.util.Arrays;

public class QuickSort implements Sort{

    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length <= 2) return array;

        sort(array, 0, array.length - 1);
        return array;
    }

    private void sort(int[] array, int left, int right) {
        if (left >= right) return;

        int low = left;
        int high = right;
        // 基准是左边 则先从右找， 基准是右边，则先从左找
        // 如果基准在左 先从左找，找到后又从右找，这是可能会遗漏某些元素
        int key = array[high];

        while (low < high) {
            while (low < high && array[low] <= key) {
                low++;
            }
            array[high] = array[low];

            while (high > low && array[high] >= key) {
                high--;
            }
            array[low] = array[high];
        }
        array[low] = key;

        sort(array, left, low - 1);
        sort(array, low +1, right);
    }




    public static void main(String[] args) {
        int [] a = {1, 2, 6, 4, 5};

        Sort sort = new QuickSort();
        System.out.println(Arrays.toString(sort.sort(a)));
    }
}
