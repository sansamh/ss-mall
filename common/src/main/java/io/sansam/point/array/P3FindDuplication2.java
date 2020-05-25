package io.sansam.point.array;

/**
 * <p>
 * FindDuplication2
 * </p>
 *
 * @author houcb
 * @since 2020/5/14 6:18 下午
 */
public class P3FindDuplication2 {

    /**
     * 长度n+1 数字范围1-n
     *
     * @param arr
     * @return
     */
    public static int getDuplicate(int[] arr) {
        if (arr == null) return -1;
        int n = arr.length;
        if (n == 1) return arr[0];

        for (int i = 0; i < n; i++) {
            if (arr[i] < 1 || arr[i] > n - 1) {
                return -1;
            }
        }
        int start = 0;
        int end = n - 1;
        int mid, count;
        while (start <= end) {
            mid = start + ((end - start) >> 1);
            count = countingNumber(arr, start, mid);
            if (start == end) {
                if (count > 0) {
                    return count;
                } else {
                    break;
                }
            }
            if (count > (mid - start) + 1) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    private static int countingNumber(int[] arr, int start, int mid) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= start || arr[i] <= mid) {
                ++res;
            }
        }
        return res;
    }
}