package io.sansam.point.array;

/**
 * <p>
 * P57ContinuousSquenceWithSum
 * </p>
 *
 * @author houcb
 * @since 2020-06-08 17:28
 */

import java.util.ArrayList;

/**
 * 题目
 * 　　输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）。例如输入15，由于1+2+3+4+5=4+5+6=7+8=15，所以结果打印出3个连续序列1～5、4～6和7～8。
 * <p>
 * 思路
 * 　　指针法：
 * <p>
 * 　　类似(57-1) 和为s的两个数字的方法，用两个指针small和big分别代表序列的最大值和最小值。令small从1开始，big从2开始。
 * <p>
 * 　　当从small到big的序列的和小于s时，增加big，使序列包含更多数字；（记得更新序列之和）
 * <p>
 * 　　当从small到big的序列的和大于s时，增加small，使序列去掉较小的数字；（记得更新序列之和）
 * <p>
 * 　　当从small到big的序列的和等于s时，此时得到一个满足题目要求的序列，输出，然后继续将small增大，往后面找新的序列。
 * <p>
 * 　　序列最少两个数字，因此，当small到了s/2时，就可以结束判断了。
 * <p>
 * 　　数学分析法：
 * <p>
 * 　　参考自牛客网，丁满历险记的答案。
 * <p>
 * 　　对于一个长度为n的连续序列，如果它们的和等于s，有：
 * <p>
 * 　　1）当n为奇数时，s/n恰好是连续序列最中间的数字，即n满足 (n&1)==1 && s%n==0
 * <p>
 * 　　2）当n为偶数时，s/n恰好是连续序列中间两个数字的平均值，小数部分为0.5，即n满足 (s%n)*2==n （判断条件中包含了n为偶数的判断）
 * <p>
 * 　　得到满足条件的n后，相当于得到了序列的中间数字s/n，所以可以得到第一个数字为 (s / n) - (n - 1) / 2，结合长度n可以得到所有数字。
 * <p>
 * 　　此外，在什么范围内找n呢？我们知道n至少等于2，那至多等于多少？n最大时，序列从1开始，根据等差数列的求和公式根据等差数列的求和公式：S = (1 + n) * n / 2，可以得到n应该小于sqrt(2s)，所以只需要从n=2到sqrt(2s)来判断满足条件的n，继而输出序列。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（存在/不存在和为s的序列）
 * <p>
 * 　　2.边界值测试（s=3）
 */
public class P57ContinuousSquenceWithSum {
    /**
     * 双指针
     *
     * @param sum
     * @return
     */
    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (sum < 3) {
            return list;
        }
        int small = 1;
        int middle = (1 + sum) >> 1;
        int big = 2;
        int curSum = small + big;

        while (small < middle) {
            if (curSum < sum) {
                big++;
                curSum += big;
            } else if (curSum > sum) {
                curSum -= small;
                small++;
            } else {
                list.add(handleResult(small, big));
                big++;
                curSum += big;
            }
        }
        return list;
    }


    private static ArrayList<Integer> handleResult(int small, int big) {
        ArrayList<Integer> list = new ArrayList<>();
        while (small <= big) {
            list.add(small++);
        }

        return list;
    }

    //方法二：数学分析法
    public static ArrayList<ArrayList<Integer>> findContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> sequenceList = new ArrayList<ArrayList<Integer>>();
        if (sum <= 0)
            return sequenceList;

        for (int n = (int) Math.sqrt(2 * sum); n >= 2; n--) {
            if (((n & 1) == 1 && sum % n == 0) || ((n & 1) == 0 && (sum % n) * 2 == n)) {
                ArrayList<Integer> sequence = new ArrayList<>();
                for (int j = 0, k = (sum / n) - (n - 1) / 2; j < n; j++, k++) {
                    sequence.add(k);
                }
                sequenceList.add(sequence);
            }
        }
        return sequenceList;
    }

    public static void main(String[] args) {
        System.out.println(findContinuousSequence(100));
    }
}
