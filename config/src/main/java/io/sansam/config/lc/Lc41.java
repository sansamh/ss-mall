package io.sansam.config.lc;

/**
 * <p>
 * Lc41
 * </p>
 *
 * @author houcb
 * @since 2020/5/13 9:51 上午
 */
public class Lc41 {

    public static void main(String[] args) {
        int[] arr = {3, 4, -1, -1};
        System.out.println(new Lc41().firstMissingPositive(arr));
    }

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }

        return len + 1;
    }

    public void swap(int[] nums, int s, int t) {
        if (s == t) return;
        int temp = nums[s];
        nums[s] = nums[t];
        nums[t] = temp;
    }
}
