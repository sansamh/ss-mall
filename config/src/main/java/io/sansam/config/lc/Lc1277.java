package io.sansam.config.lc;

/**
 * <p>
 * Lc1277
 * </p>
 *
 * @author houcb
 * @since 2020/5/14 3:53 下午
 */
public class Lc1277 {

    public static void main(String[] args) {
        int[][] arr = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};
        System.out.println(countSquares(arr));
    }

    public static int countSquares(int[][] matrix) {
        int res = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    res += 1;
                } else {
                    matrix[i][j] = Math.min(matrix[i - 1][j], Math.min(matrix[i - 1][j - 1], matrix[i][j - 1])) + 1;
                    res += matrix[i][j];
                }
            }
        }
        return res;

    }
}
