package io.sansam.point.array;

/**
 * <p>
 * P12RobotMove
 * </p>
 *
 * @author houcb
 * @since 2020/5/25 3:17 下午
 */
public class P13RobotMove {

    public static int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        boolean[] visited = new boolean[rows * cols];
        return helper(threshold, 0, 0, rows, cols, visited);
    }

    private static int helper(int threshold, int row, int col, int rows, int cols, boolean[] visited) {
        int index = row * cols + col;
        if (row < 0 || col < 0 || row >= rows || col >= cols
                || visited[index] || cal(row) + cal(col) > threshold) {
            return 0;
        }
        visited[index] = true;
        int count = 1 + helper(threshold, row + 1, col, rows, cols, visited)
                + helper(threshold, row - 1, col, rows, cols, visited)
                + helper(threshold, row, col + 1, rows, cols, visited)
                + helper(threshold, row, col - 1, rows, cols, visited);
        return count;
    }

    private static int cal(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }
        return sum;
    }

    // test code

    public static void main(String[] args) {
        P13RobotMove demo = new P13RobotMove();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
        demo.test6();
        demo.test7();
        demo.test8();
        demo.test9();
    }

    void test(String testName, int threshold, int rows, int cols, int expected) {
        if (testName != null)
            System.out.print(testName + ":");

        if (movingCount(threshold, rows, cols) == expected)
            System.out.println("Passed.");
        else
            System.out.println("Failed.");
    }

    // 方格多行多列
    void test1() {
        test("Test1", 5, 10, 10, 21);
    }

    // 方格多行多列
    void test2() {
        test("Test2", 15, 20, 20, 359);
    }

    // 方格只有一行，机器人只能到达部分方格
    void test3() {
        test("Test3", 10, 1, 100, 29);
    }

    // 方格只有一行，机器人能到达所有方格
    void test4() {
        test("Test4", 10, 1, 10, 10);
    }

    // 方格只有一列，机器人只能到达部分方格
    void test5() {
        test("Test5", 15, 100, 1, 79);
    }

    // 方格只有一列，机器人能到达所有方格
    void test6() {
        test("Test6", 15, 10, 1, 10);
    }

    // 方格只有一行一列
    void test7() {
        test("Test7", 15, 1, 1, 1);
    }

    // 方格只有一行一列
    void test8() {
        test("Test8", 0, 1, 1, 1);
    }

    // 机器人不能进入任意一个方格
    void test9() {
        test("Test9", -10, 10, 10, 0);
    }


}
