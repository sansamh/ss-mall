package io.sansam.point.array;

/**
 * <p>
 * P60DicesProbability
 * </p>
 *
 * @author houcb
 * @since 2020-06-09 16:45
 */

import java.text.NumberFormat;

/**
 * 题目
 * 　　把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * 思路
 * 　　对于n个骰子，要计算出每种点数和的概率，我们知道投掷n个骰子的总情况一共有6^n种，因此只需要计算出某点数和的情况一共有几种，即可求出该点数之和的概率。
 * <p>
 * 　　方法一：基于递归的方法，效率较低
 * <p>
 * 　　易知，点数之和s的最小值为n，最大值为6*n，因此我们考虑用一个大小为（6*n-n+1）的数组存放不同点数之和的情况个数，那么，如果点数之和为x，那么把它出现的情况总次数放入数组种下标为x-n的元素里。
 * <p>
 * 　　确定如何存放不同点数之和的次数后，我们要计算出这些次数。我们把n个骰子分为1个骰子和n-1个骰子，这1
 * <p>
 * 个骰子可能出现1~6个点数，由该骰子的点数与后面n-1个骰子的点数可以计算出总点数；而后面的n-1个骰子又可以分为1个和n-2个，把上次的点数，与现在这个骰子的点数相加，再和剩下的n-2个骰子的点数相加可以得到总点数……，即可以用递归实现。在获得最后一个骰子的点数后可以计算出几个骰子的总点数，令数组中该总点数的情况次数+1，即可结束遍历。
 * <p>
 * 　　方法二：基于循环求骰子点数，时间性能好
 * <p>
 * 　　用数组存放每种骰子点数和出现的次数。令数组中下标为n的元素存放点数和为n的次数。我们设置循环，每个循环多投掷一个骰子，假设某一轮循环中，我们已知了各种点数和出现的次数；在下一轮循环时，我们新投掷了一个骰子，那么此时点数和为n的情况出现的次数就等于上一轮点数和为n-1,n-2,n-3,n-4,n-5,n-6的情况出现次数的总和。从第一个骰子开始，循环n次，就可以求得第n个骰子时各种点数和出现的次数。
 * <p>
 * 　　我们这里用两个数组来分别存放本轮循环与下一轮循环的各种点数和出现的次数，不断交替使用。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（1，2，3，4个骰子）
 * <p>
 * 　　2.特殊测试（0个）
 * <p>
 * 　　3.性能测试（11个）
 */
public class P60DicesProbability {

    private static int MAX_SIZE = 6;

    /**
     * 递归
     *
     * @param number 骰子个数
     */
    public static void printProbability1(int number) {
        if (number <= 0) return;

        // 总数出现的范围为 number*1 到 number*6 之间
        int[] pos = new int[number * MAX_SIZE - number + 1];

        // 先固定一个 计算剩下的
        for (int i = 1; i <= MAX_SIZE; i++) {
            calcPos(pos, number, number - 1, i);
        }

        // 总共出现的次数
        int total = (int) Math.pow(6, number);
        for (int i = 0; i < pos.length; i++) {
            double ratio = (double) pos[i] / total;
            NumberFormat format = NumberFormat.getPercentInstance();
            format.setMaximumFractionDigits(2);//设置保留几位小数
            System.out.println("点数和为" + (i + number) + "的概率为:" + format.format(ratio));
        }
    }

    /**
     * @param pos
     * @param number  骰子共个数
     * @param leftNum 剩余骰子个数
     * @param sum     当前的数字和
     */
    private static void calcPos(int[] pos, int number, int leftNum, int sum) {
        // 剩余骰子为0 sum - number 为此sum在pos中的下标
        if (leftNum == 0) {
            pos[sum - number]++;
            return;
        }
        for (int i = 1; i <= MAX_SIZE; i++) {
            // 递归调用leftNum - 1, sum = sum+i
            calcPos(pos, number, leftNum - 1, sum + i);
        }
    }


    /**
     * 计算每种点数出现的次数
     *
     * @param number
     */
    public static void printProbability2(int number) {
        if (number <= 0) return;

        int[][] pos = new int[2][number * MAX_SIZE + 1];
        // 第一个骰子出现的情况
        for (int i = 1; i <= MAX_SIZE; i++) {
            pos[0][i] = 1;
        }

        int flag = 0;
        // 当前是第几个骰子
        for (int cur = 2; cur <= number; cur++) {
            // 前面的数据清零 因为curNum个骰子 最小数为curNum
            for (int i = 0; i < cur; i++) {
                pos[1 - flag][i] = 0;
            }
            // curNum个骰子 i的范围为 curNum - curNum*6之间
            for (int i = cur; i <= cur * MAX_SIZE; i++) {
                // 和为i的次数 等于 前一个骰子和为 i-1 i-2 i-3 i-4 i-5 i-6的和（因为本次骰子可以摇出1 - 6）
                // 但是当i <= 6的时候 假设i=4 此时前一个骰子摇出的456 本次都是无用的 因为本次最小为1 加起来超过4了
                // 所以 j <= 6 && j <= 4 ，当 i=4 j=4的时候 pos[flag][0] = 0
                for (int j = 1; j <= 6 && j <= i; j++) {
                    pos[1 - flag][i] += pos[flag][i - j];
                }
            }
            // 切换数组
            flag = 1 - flag;
        }

        int totalP = (int) Math.pow(MAX_SIZE, number);  //所有情况总共出现的次数
        for (int i = number; i <= number * 6; i++) {
            double ratio = (double) pos[flag][i] / totalP;
            NumberFormat format = NumberFormat.getPercentInstance();
            format.setMaximumFractionDigits(8);//设置保留几位小数
            System.out.println("点数和为" + (i) + "的概率为:" + format.format(ratio));
        }

    }

    public static void main(String[] args) {
        printProbability2(3);
    }
}
