package io.sansam.util;

/**
 * <p>
 * ArrayUtil
 * </p>
 *
 * @author houcb
 * @since 2020/5/14 5:13 下午
 */
public class ArrayUtil {

    public static void swap(int[] arr, int s, int t) {
        int temp = arr[s];
        arr[s] = arr[t];
        arr[t] = temp;
    }
}
