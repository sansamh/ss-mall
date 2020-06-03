package io.sansam.point.array;

/**
 * <p>
 * P48LongestSubstringWithoutDup
 * </p>
 *
 * @author houcb
 * @since 2020-06-03 17:36
 */

import java.util.Arrays;

/**
 * 题目
 * 　　请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。假设字符串中只包含从'a'到'z'的字符。
 * <p>
 * 思路
 * 　　动态规划法：定义函数f(i)为：以第i个字符为结尾的不含重复字符的子字符串的最大长度。
 * <p>
 * 　　（1）当第i个字符之前未出现过，则有：f(i)=f(i-1)+1
 * <p>
 * 　　（2）当第i个字符之前出现过，记该字符与上次出现的位置距离为d
 * <p>
 * 　　　　1）如果d<=f(i-1)，则有f(i)=d；
 * <p>
 * 　　　　2）如果d>f(i-1)，则有f(i)=f(i-1)+1；
 * <p>
 * 　　我们从第一个字符开始遍历，定义两个int变量preLength和curLength来分别代表f(i-1)和f(i)，再创建一个长度为26的pos数组来存放26个字母上次出现的位置，即可根据上述说明进行求解。
 * <p>
 * 　　注意：每次最大长度和字母出现位置要记得更新。
 * <p>
 * 　　另一种思路：遍历每个字符，把当前字符看成子字符串的末尾结点，同时更新开头结点，详细代码见Longest Substring Without Repeating Characters
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（一个或者多个字符，全部字符不同/相同）
 * <p>
 * 　　2.特殊测试（null，空字符串）
 * <p>
 * 收获
 * 　　1.函数f(i)为：以第i个字符为结尾的不含重复字符的子字符串的最大长度。而不是以第i个字符作为开头。第i个字符作为结尾可以方便与下一个字符进行联系。
 * <p>
 * 　　2.学会用长度为26的数组来存放26个字母所在的位置下标。
 */
public class P48LongestSubstringWithoutDup {

    public static int maxLength(String str) {
        if (str == null || str.length() == 0) return 0;

        int pre = 0;
        int cur = 0;
        int max = 0;
        int[] position = new int[26];
        Arrays.fill(position, -1);

        int letterPosition;
        for (int i = 0; i < str.length(); i++) {
            letterPosition = str.charAt(i) - 'a';
            if (position[letterPosition] < 0 || i - position[letterPosition] > pre) {
                cur = pre + 1;
            } else {
                cur = i - position[letterPosition];
            }
            position[letterPosition] = i;
            if (cur > max) {
                max = cur;
            }
            pre = cur;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxLength("arabcacfr") == 4);
        System.out.println(maxLength("a") == 1);
        System.out.println(maxLength("aaa") == 1);
        System.out.println(maxLength("abcdef") == 6);
        System.out.println(maxLength("") == 0);
        System.out.println(maxLength(null) == 0);
    }

}
