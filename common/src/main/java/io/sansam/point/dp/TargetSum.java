package io.sansam.point.dp;

/**
 * <p>
 * TargetSum
 * </p>
 *
 * @author houcb
 * @since 2020-07-27 14:30
 */
public class TargetSum {

    int result = 0;

    public static void main(String[] args) {
        final TargetSum targetSum = new TargetSum();
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(targetSum.findTargetSumWaysBack(nums, target));
    }

    public int findTargetSumWaysBack(int[] nums, int s) {
        if (nums == null || nums.length == 0) return -1;

        findBack(nums, 0, s);

        return result;
    }

    private void findBack(int[] nums, int index, int rest) {
        if (index == nums.length) {
            if (rest == 0) {
                result++;
            }
            return;
        }
        // 给nums[index]这个数字+号 则rest -= nums[index]
        rest -= nums[index];
        findBack(nums, index + 1, rest);

        // 撤销+号
        rest += nums[index];

        // 给nums[index]这个数字-号 则rest += nums[index]
        rest += nums[index];
        findBack(nums, index + 1, rest);

        // 撤销-号
        rest -= nums[index];

    }
}
