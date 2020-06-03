package io.sansam.point.array;

/**
 * <p>
 * P47MaxValueOfGifts
 * </p>
 *
 * @author houcb
 * @since 2020-06-03 15:44
 */

import java.util.Arrays;

/**
 * 题目
 * 　　在一个m×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向左或者向下移动一格直到到达棋盘的右下角。给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物？
 * <p>
 * 思路
 * 　　动态规划：定义f(i,j)为到达(i,j)位置格子时能拿到的礼物总和的最大值，则有：f(i,j)=max{f(i,j),f(i,j)}+values(i,j)。
 * <p>
 * 　　同上道题一样，如果直接使用递归会产生大量的重复计算，因此，创建辅助的数组来保存中间计算结果。
 * <p>
 * 　　辅助数组不用和m*n的二维数组一样大，只需要保存上一层的最大值就可以。代码中使用长度为列数n的一位数组作为辅助数组，注释部分为二维辅助数组。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（多行多列，一行多列，多行一列，一行一列）
 * <p>
 * 　　2.特殊测试（null）
 */
public class P47MaxValueOfGifts {

    /**
     * 二维辅助数组
     *
     * @param values
     * @return
     */
    public static int maxValue1(int[][] values) {
        if (values == null || values.length == 0) return 0;

        int rows = values.length;
        int cols = values[0].length;

        int[][] maxArr = new int[rows][cols];
        maxArr[0][0] = values[0][0];

        int up;
        int left;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                up = left = 0;
                if (i > 0) {
                    up = maxArr[i - 1][j];
                }
                if (j > 0) {
                    left = maxArr[i][j - 1];
                }
                maxArr[i][j] = Math.max(up, left) + values[i][j];
            }
        }
        return maxArr[rows - 1][cols - 1];
    }

    /**
     * 一维辅助数组
     * <p>
     * 　每次计算拿到的礼物最大值的时候，最大值的坐标只依赖(i-1,j)and(i,j-1)两个格子，
     * 因此第i-2行及以上所有的格子礼物最大值没有必要保存下来，因此可以用一维数组代替二维数组，
     * <p>
     * 一维数组的长度为棋盘的列数，当我们计算坐标为(i,j)格子能够拿到礼物的最大价值f(i,j)的时候，
     * 数组前j个数字分别是f(i,0),f(i,1)...,f(i,j-1)，
     * 数组从下标为j的数字开始到最后一个数字，分别为f(i-1,j),f(i-1,j+1),...,f(i-1,n-1)
     * ，也就是数组前面j个数字分别是当前第i行前面j个格子礼物的最大值（同行左边），而后的数字分别保存前面第i-1行n-j个格子礼物的最大值（上一行右边）。
     *
     * @param values
     * @return
     */
    public static int maxValue2(int[][] values) {
        if (values == null || values.length == 0) return 0;

        int rows = values.length;
        int cols = values[0].length;

        int[] maxArr = new int[cols];

        int up;
        int left;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                up = left = 0;
                if (i > 0) {
                    up = maxArr[j];
                }
                if (j > 0) {
                    left = maxArr[j - 1];
                }
                maxArr[j] = Math.max(up, left) + values[i][j];
            }
            System.out.println(Arrays.toString(maxArr));
        }
        return maxArr[cols - 1];
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}};
        System.out.println(maxValue1(arr));
        System.out.println(maxValue2(arr));

    }
}
