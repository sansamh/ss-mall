package io.sansam.point.array;

/**
 * <p>
 * P46TranslateNumbersToStrings
 * </p>
 *
 * @author houcb
 * @since 2020-06-03 11:08
 */

/**
 * 题目
 * 　　给定一个数字，我们按照如下规则把它翻译为字符串：0翻译成"a"，1翻译成"b"，……，11翻译成"l"，……，25翻译成"z"。一个数字可能有多个翻译。例如12258有5种不同的翻译，它们分别"bccfi", "bwfi", "bczi", "mcfi" 和"mzi" 。请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * 思路
 * 　　看到题目，很容易想到使用递归：用f(i)来表示从第i位开始的不同翻译数目，可以得到有：f(i)=f(i+1)+g(i,i+1)*f(i+2)。i和i+1位数字拼起来在10~25范围内时g(i,i+1)的值为1，否则为0。
 * <p>
 * 　　但是存在重复的子问题，所以递归并非最佳方法，我们从数字的末尾开始计算f(i)，自下而上解决问题，就可以消除重复的子问题了。先算f(len-1)，f(len-2)，再根据公式f(i)=f(i+1)+g(i,i+1)*f(i+2)往前逐步推导到f(0)，这就是最终要求的结果。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（1个数字；多个数字）
 * <p>
 * 　　2.特殊测试（负数，0，含25、26等）
 * <p>
 * 收获
 * 　　1.递归方法，我们试着用公式描述会比较清晰
 * <p>
 * 　　2.递归是自上而下解决问题，如果遇到重复的子问题时，考虑自下而上求解，不用递归
 * <p>
 * 　　3.g(i,i+1)不仅要判断<=25，还要判断>=10，别漏了
 */
public class P46TranslateNumbersToStrings {

    public static int translatenNumber2String(int num) {
        if (num <= 0) return -1;
        String s = String.valueOf(num);

        int[] count = new int[s.length()];

        //从后往前递归
        for (int i = s.length() - 1; i >= 0; i--) {
            // 最后一个数字 count[i] = 1
            if (i == s.length() - 1) {
                count[i] = 1;
            } else {
                //  f(i) = f(i-1) + g(i, i-1)*f(i-2)
                count[i] = count[i + 1];
                // 判断g(i, i-1);
                if (canDoTrans(s, i)) {
                    if (i == s.length() - 2) {
                        count[i] += 1;
                    } else {
                        count[i] += count[i + 2];

                    }
                }
            }
        }
        return count[0];
    }

    private static boolean canDoTrans(String s, int i) {
        int a = s.charAt(i) - '0';
        int b = s.charAt(i + 1) - '0';
        int sum = a * 10 + b;
        if (10 <= sum && sum <= 25) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(translatenNumber2String(12258));
    }
}
