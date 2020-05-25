package io.sansam.point.array;

import java.util.Arrays;

/**
 * <p>
 * P3FindInPartiallySortedMatrix
 * </p>
 *
 * @author houcb
 * @since 2020/5/15 11:44 上午
 */
public class P4FindInPartiallySortedMatrix {

    public static void main(String[] args) {
        P4FindInPartiallySortedMatrix f = new P4FindInPartiallySortedMatrix();
        f.test1(); // 注意下标是从0开始的
        f.test2();
        f.test3();
        f.test4();
        f.test5();
    }

    public static int[] find(int[][] matrix, int a) {
        // 从右上角开始 row = 0; col = length-1;
        // i < target -> row++;
        // i > target -> col--;
        int[] res = {-1, -1};
        if (matrix == null) return res;
        int row = matrix.length;
        int col = matrix[0].length;
        if (matrix[row - 1][col - 1] < a) return res;

        int x = 0;
        int y = col - 1;
        while (x < row && y >= 0) {
            if (matrix[x][y] == a) {
                res = new int[]{x, y};
                break;
            } else if (matrix[x][y] > a) {
                y--;
            } else {
                x++;
            }
        }
        return res;
    }

    public static int[] find2(int[][] matrix, int a) {
        // 从左下角开始 row = length-1; col = 0;
        // i > taget -> row--;
        // i < target -> col++;
        int[] res = {-1, -1};
        if (matrix == null) return res;
        int row = matrix.length;
        int col = matrix[0].length;
        if (matrix[row - 1][col - 1] < a) return res;

        int x = 0;
        int y = col - 1;
        while (x < row && y >= 0) {
            if (matrix[x][y] == a) {
                res = new int[]{x, y};
                break;
            } else if (matrix[x][y] > a) {
                y--;
            } else {
                x++;
            }
        }
        return res;
    }


    // 1 2 8 9
    // 2 4 9 12
    // 4 7 10 13
    // 6 8 11 15
    // 要查找的数在数组中
    public void test1() {
        System.out.println("test1：");
        int[][] matrix = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int[] index = find(matrix, 7);
        System.out.println(Arrays.toString(index));
    }

    // 1 2 8 9
    // 2 4 9 12
    // 4 7 10 13
    // 6 8 11 15
    // 要查找的数不在数组中
    public void test2() {
        System.out.println("test2：");
        int[][] matrix = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int[] index = find(matrix, 5);
        System.out.println(Arrays.toString(index));

    }

    // 数组为空
    public void test3() {
        System.out.println("test3：");
        int[][] matrix = null;
        int[] index = find(matrix, 7);
        System.out.println(Arrays.toString(index));

    }

    // 1 2 8 9
    // 4 3 9 12
    // 4 7 10 13
    // 6 8 11 15
    // 数组不满足大小规则
    public void test4() {
        System.out.println("test4：");
        int[][] matrix = {{1, 2, 8, 9}, {4, 3, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int[] index = find(matrix, 7);
        System.out.println(Arrays.toString(index));

    }

    // 数组每行长度不一致
    public void test5() {
        System.out.println("test5：");
        int[][] matrix = {{1, 2, 8}, {4, 3, 9, 12}, {4, 7, 10}, {6, 8, 11, 15}};
        int[] index = find(matrix, 7);
        System.out.println(Arrays.toString(index));

    }
}
