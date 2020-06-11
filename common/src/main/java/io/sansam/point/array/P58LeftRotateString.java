package io.sansam.point.array;

/**
 * <p>
 * P58LeftRotateString
 * </p>
 *
 * @author houcb
 * @since 2020-06-09 14:34
 */

/**
 * 题目
 * 　　字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如输入字符串"abcdefg"和数字2，该函数将返回左旋转2位得到的结果"cdefgab"。
 * <p>
 * 思路
 * 　　最初的想法是令chars[i] = chars[i+n]，将后面的数字都往前移，最后面空出的位置放入前面的数字，如abcdef，n=2时，将c放入a的位置，e放入c的位置，a放入e的位置，b也同理，就可以得到cdefab了。但是这没有考虑到最后空出的位置是否正确，例如abcdefg中，同样的方法将会得到cdeba，答案错误，就是因为后面的位置对应不上。思路错误！
 * <p>
 * 　　正确思路：本题思路和上一道题翻转单词顺序的原理一模一样，只是上一道题有空格，这道题没空格，其实这道题还更简单。先分别翻转前半部分字符串和后半部分字符串，最后翻转整个字符串即可。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（对长度为n的字符串，左旋转-1,0,1,2,n-1,n,n+1位）
 * <p>
 * 　　2.边界值测试（null）
 */
public class P58LeftRotateString {

    /**
     * abcdefg n=2
     * <p>
     * 先翻转0 - n-1 bacdefg
     * 再反转n - length - 1 bagfedc
     * 再反转0 - length-1 cdefgab
     *
     * @param chars
     * @param n
     * @return
     */
    public static String leftRotateString(char[] chars, int n) {
        if (chars == null || chars.length == 0 || n > chars.length) {
            return String.valueOf(chars);
        }
        reverseArray(chars, 0, n - 1);
        reverseArray(chars, n, chars.length - 1);
        reverseArray(chars, 0, chars.length - 1);

        return String.valueOf(chars);
    }

    private static void reverseArray(char[] chars, int start, int end) {
        char temp;
        while (start < end) {
            temp = chars[start];
            chars[start++] = chars[end];
            chars[end--] = temp;
        }
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println(leftRotateString(s.toCharArray(), 2));
    }

}
