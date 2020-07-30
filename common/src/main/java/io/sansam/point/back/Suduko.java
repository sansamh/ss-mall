package io.sansam.point.back;

/**
 * <p>
 * Suduko
 * </p>
 *
 * @author houcb
 * @since 2020-07-30 14:23
 */
public class Suduko {

    public static void main(String[] args) {


        char[][] borad = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.',
                '9',
                '8',
                '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.',
                '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        new Suduko().solveSudoku(borad);

        for (char[] chars : borad) {
            for (char c : chars) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {

        boolean res = solve(board, 0, 0);
        System.out.println(res);
    }

    private boolean solve(char[][] board, int row, int col) {
        int rm = 9;
        int cm = 9;
        // 当走到最后一列的时候 计算下一行的0列
        if (col == cm) {
            return solve(board, row + 1, 0);
        }
        // row == rm时全部解出了
        if (row == rm) {
            return true;
        }

        for (int i = row; i < rm; i++) {
            for (int j = col; j < cm; j++) {
                if (board[i][j] != '.') {
                    return solve(board, i, j + 1);
                }

                for (char c = '1'; c <= '9'; c++) {
                    if (!isValid(board, i, j, c)) {
                        continue;
                    }
                    board[i][j] = c;
                    if (solve(board, i, j + 1)) {
                        return true;
                    }
                    board[i][j] = '.';
                }
                return false;
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        int max = 9;

        for (int i = 0; i < max; i++) {
            // 竖线不能有相同
            if (board[i][col] == c) {
                return false;
            }
            // 横向不能有相同
            if (board[row][i] == c) {
                return false;
            }
        }

        int maxc = 0;
        int minc = 0;
        int maxr = 0;
        int minr = 0;
        int cn = (col) / 3;
        int rn = (row) / 3;
        int[] cols = getMinAndMax(cn, minc, maxc);
        minc = cols[0];
        maxc = cols[1];

        int[] rows = getMinAndMax(rn, minr, maxr);
        minr = rows[0];
        maxr = rows[1];

        for (int i = minr; i <= maxr; i++) {
            for (int j = minc; j <= maxc; j++) {
                if (board[i][j] == c) {
                    return false;
                }
            }
        }


        return true;
    }

    private int[] getMinAndMax(int num, int min, int max) {
        if (num == 0) {
            min = 0;
            max = 2;
        }
        if (num == 1) {
            min = 3;
            max = 5;
        }
        if (num == 2) {
            min = 6;
            max = 8;
        }
        return new int[]{min, max};
    }
}
