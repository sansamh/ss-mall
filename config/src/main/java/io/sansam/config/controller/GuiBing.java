package io.sansam.config.controller;

import java.util.Arrays;

/**
 * <p>
 * GuiBing
 * </p>
 *
 * @author houcb
 * @since 2020-04-23 14:12
 */
public class GuiBing {

    public static void sort(int[] arr, int low, int high, int[] temp) {
        if (low >= high) {
            return;
        }
        int mid = (high + low) / 2;

        sort(arr, low, mid, temp);
        sort(arr, mid + 1, high, temp);
        merge(arr, low, mid, high, temp);
    }

    private static void merge(int[] arr, int low, int mid, int high, int[] temp) {
        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= high) {
            temp[k++] = arr[j++];
        }
        k = 0;

        while (low <= high) {
            arr[low++] = temp[k++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 1, 7, 9, 8, 5};
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }
}
