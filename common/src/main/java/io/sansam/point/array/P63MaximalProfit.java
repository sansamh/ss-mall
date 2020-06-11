package io.sansam.point.array;

/**
 * <p>
 * P63MaximalProfit
 * </p>
 *
 * @author houcb
 * @since 2020-06-10 16:05
 */

/**
 * 题目
 * 　　假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖交易该股票可能获得的利润是多少？例如一只股票在某些时间节点的价格为{9, 11, 8, 5,7, 12, 16, 14}。如果我们能在价格为5的时候买入并在价格为16时卖出，则能收获最大的利润11。
 * <p>
 * 思路
 * 　　遍历每一个数字，并保存之前最小的数字，两者差最大即为最大利润。
 *
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（数组递增/递减/无序）
 * <p>
 * 　　2.特殊测试（null，空数组）
 * <p>
 * 　　3.边界值测试（数组仅两个数字）
 */
public class P63MaximalProfit {

    public static int MaxDiff(int[] arr) {
        if (arr == null || arr.length <= 1) return -1;

        int min = arr[0];
        int max = arr[1] - min;
        int cur;

        // 考虑利润为负数 因为要 每次都和前面最小的数进行比较
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < min) {
                min = arr[i - 1];
            }
            cur = arr[i] - min;
            if (cur > max) {
                max = cur;
            }
        }

        return max;

    }

    //简单快速测试下
    public static void main(String[] args) {
        int[] arr1 = null;
        System.out.println(MaxDiff(arr1) == -1);

        int[] arr2 = {};
        System.out.println(MaxDiff(arr2) == -1);

        int[] arr3 = {16, 16, 16, 16, 16};
        System.out.println(MaxDiff(arr3) == 0);

        int[] arr4 = {1, 2, 4, 7, 11, 16};
        System.out.println(MaxDiff(arr4) == 15);

        int[] arr5 = {16, 11, 7, 4, 2, 1};
        System.out.println(MaxDiff(arr5) == -1);
        System.out.println(MaxDiff(arr5));

        int[] arr6 = {9, 11, 5, 7, 16, 1, 4, 2};
        System.out.println(MaxDiff(arr6) == 11);

        int[] arr7 = {2, 4};
        System.out.println(MaxDiff(arr7) == 2);

        int[] arr8 = {4, 2};
        System.out.println(MaxDiff(arr8) == -2);
        System.out.println(MaxDiff(arr8));

    }
}
