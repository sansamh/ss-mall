package io.sansam.point.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * EightQueen
 * </p>
 *
 * @author houcb
 * @since 2020-07-28 18:10
 */
public class EightQueen {

    static final String QUEEN = "Q";
    static final String NORMAL = ".";

    static List<List<String>> list = new ArrayList<>();

    public static List<List<String>> solveQueen(int n) {
        String[][] dp = new String[n][n];
        for (String[] arr : dp) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = NORMAL;
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = NORMAL;
            }
        }
        backtrack(dp, 0);
        return list;
    }

    private static void backtrack(String[][] dp, int row) {
        // 终止条件
        if (row == dp.length) {
            addRes(dp);
            return;
        }
        for (int i = 0; i < dp[row].length; i++) {
            if (!isValid(dp, row, i)) {
                continue;
            }
            dp[row][i] = QUEEN;
            backtrack(dp, row + 1);

            dp[row][i] = NORMAL;
        }
    }

    private static boolean isValid(String[][] dp, int row, int col) {
        int r = row;
        int c = col;

        // 判断同列
        if (row != 0) {
            while (r >= 0) {
                if (QUEEN.equals(dp[r][col])) {
                    return false;
                }
                r -= 1;
            }
        }
        // 左上角
        r = row;
        c = col;
        while (r != 0 && c != 0) {
            if (QUEEN.equals(dp[r - 1][c - 1])) {
                return false;
            }
            r--;
            c--;
        }

        // 右上角
        r = row;
        c = col;
        while (r != 0 && c < dp.length - 1) {
            if (QUEEN.equals(dp[r - 1][c + 1])) {
                return false;
            }
            r--;
            c++;
        }

        return true;
    }

    private static void addRes(String[][] dp) {
        List<String> res = new ArrayList<>();
        for (String[] ints : dp) {
            res.addAll(Arrays.asList(ints));
            res.add(",");
        }
        list.add(res);
    }

    public static void print(List<List<String>> lists) {
        for (List<String> list : lists) {
            for (String s : list) {
                if (",".equals(s)) {
                    System.out.println();
                } else {
                    System.out.print(s);
                }

            }
            System.out.println("---------------");
        }
    }

    public static void main(String[] args) {
        print(solveQueen(4));
    }
}
