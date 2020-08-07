package io.sansam.point.dp;

/**
 * <p>
 * LongestContinueSub
 * </p>
 *
 * @author houcb
 * @since 2020-07-31 11:31
 */
public class LongestContinueSub {

    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int size = 1;
        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                size++;
            } else {
                max = Math.max(size, max);
                size = 1;
            }
        }

        return Math.max(size, max);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7};
        System.out.println(findLengthOfLCIS(arr));
    }
}
