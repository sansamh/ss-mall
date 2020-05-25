package io.sansam.point.array;

/**
 * <p>
 * P11StringPathInMatrix
 * </p>
 *
 * @author houcb
 * @since 2020/5/25 2:21 下午
 */
public class P12StringPathInMatrix {

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length == 0
                || rows <= 0 || cols <= 0
                || str == null || str.length == 0) {
            return false;
        }
        boolean[] visited = new boolean[rows * cols];
        int strLen = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (helper(rows, cols, row, col, matrix, str, strLen, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean helper(int rows, int cols, int row, int col, char[] matrix, char[] str, int strLen, boolean[] visited) {
        // 判断row和col的边界值
        // 判断当前字符和字符串字符是否一致
        // 判断当前节点是否访问过
        // 当前节点在数组中的下标 = row * cols + col;(row col 从0开始)
        int index = row * cols + col;
        if (row < 0 || col < 0
                || row >= rows || col >= cols
                || matrix[index] != str[strLen]
                || visited[index]) {
            return false;
        }
        // 找到了 并且是最后一个字符了
        if (strLen == str.length - 1) {
            visited[index] = true;
            return true;
        }
        // 当前节点设置为true
        visited[index] = true;
        // 接下来向上下左右四个方向继续找
        boolean found = helper(rows, cols, row + 1, col, matrix, str, strLen + 1, visited)
                || helper(rows, cols, row - 1, col, matrix, str, strLen + 1, visited)
                || helper(rows, cols, row, col + 1, matrix, str, strLen + 1, visited)
                || helper(rows, cols, row, col - 1, matrix, str, strLen + 1, visited);
        // 如果没有找到 则恢复当前的状态
        if (!found) {
            visited[index] = false;
        }
        return found;
    }

    // A B T G
    // C F C S
    // J D E H

    public static void main(String[] args) {
        P12StringPathInMatrix demo = new P12StringPathInMatrix();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
        demo.test6();
        demo.test7();
    }

    // A B T G
    // C F C S
    // J D E H

    // BFCTB
    public void test1() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "BFCTB".toCharArray();
        if (!hasPath(matrix, rows, cols, str))
            System.out.println("Test1 passed.");
        else
            System.out.println("Test1 failed.");
    }

    // BFCE
    public void test2() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "BFCE".toCharArray();
        if (hasPath(matrix, rows, cols, str))
            System.out.println("Test2 passed.");
        else
            System.out.println("Test2 failed.");
    }

    // matrix=null
    public void test3() {
        char[] matrix = null;
        int rows = 0;
        int cols = 0;
        char[] str = "BFCE".toCharArray();
        if (!hasPath(matrix, rows, cols, str))
            System.out.println("Test3 passed.");
        else
            System.out.println("Test3 failed.");
    }

    // A

    // str=null
    public void test4() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = null;
        if (!hasPath(matrix, rows, cols, str))
            System.out.println("Test4 passed.");
        else
            System.out.println("Test4 failed.");
    }

    // A

    // A
    public void test5() {
        char[] matrix = "A".toCharArray();
        int rows = 1;
        int cols = 1;
        char[] str = "A".toCharArray();
        if (hasPath(matrix, rows, cols, str))
            System.out.println("Test5 passed.");
        else
            System.out.println("Test5 failed.");
    }

    // AAAA
    // AAAA
    // AAAA

    // B
    public void test6() {
        char[] matrix = "A".toCharArray();
        int rows = 1;
        int cols = 1;
        char[] str = "B".toCharArray();
        if (!hasPath(matrix, rows, cols, str))
            System.out.println("Test6 passed.");
        else
            System.out.println("Test6 failed.");
    }

    // AAAAAAAAAAAA
    public void test7() {
        char[] matrix = "AAAAAAAAAAAA".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "AAAAAAAAAAAA".toCharArray();
        if (hasPath(matrix, rows, cols, str))
            System.out.println("Test7 passed.");
        else
            System.out.println("Test7 failed.");
    }


}
