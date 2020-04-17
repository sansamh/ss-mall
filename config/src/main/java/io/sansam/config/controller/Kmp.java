package io.sansam.config.controller;

import java.util.Arrays;

/**
 * <p>
 * Kmp
 * </p>
 *
 * @author houcb
 * @since 2020/4/9 5:07 下午
 */
public class Kmp {

    // T 表示文本串 下标i
    // P 表示格式串 下标j


    /**
     * https://www.cnblogs.com/tangzhengyue/p/4315393.html
     *
     * @param pat 格式串
     * @return int[]
     */
    public static int[] getNext(String pat) {
        final char[] chars = pat.toCharArray();
        // next数组表示 当P[j] != T[i] 时，j需要指向的下标
        int[] next = new int[pat.length()];
        // 当第一位就不匹配的时候 需要T移动一位，我们设置为-1
        next[0] = -1;
        // k表示不匹配时 j需要移动到的下标 默认为-1
        int k = -1;
        // 格式串下标
        int j = 0;

        // 因为next第一位已经确定为-1，我们只需要根据j和next[j]就可以推导出next[j+1]， 所以 j < pat.length-1
        // 下标 0 1 2 k-1 k k+1 j-k j-k+1 j-1 j j+1
        // 如果 k = j，因为0~(k-1) == (j-k+1)~(j-1) -> 0-k == (k+1)~j -> k++
        while (j < pat.length() - 1) {
            // 如果k == -1说明需要将T向后移动一位，将P归0
            // 如果P[j] == P[k] 将k向后移动一位
            if (k == -1 || chars[j] == chars[k]) {
                // 可是我们知道，第j+1位是失配了的，如果我们回退j后，发现新的j(也就是此时的++k那位)跟回退之前的j也相等的话，必然也是失配。所以还得继续往前回退。
                // 如果P[j + 1] == P[k + 1]，回退后仍然失配，所以要继续回退
                if (chars[++j] == chars[++k]) {
                    next[j] = next[k];
                } else {
                    next[j] = ++k;
                }

            } else {
                // 如果 P[j] != P[k]，我们只能找到0-j之间最长的公共前后缀进行匹配
                // 0-j之间最长的公共前后缀长度就是next[k] 所以我们将k指针移到next[k] 继续递归匹配chars[j]和chars[k]
                k = next[k];
            }
        }
        System.out.println(Arrays.toString(next));
        return next;
    }

    /**
     * https://www.cnblogs.com/dusf/p/kmp.html
     *
     * @param pat 格式串
     * @param txt 文本串
     * @return int下标
     */
    public static int kmp(String pat, String txt) {
        char[] pChars = pat.toCharArray();
        char[] tChars = txt.toCharArray();

        int[] next = getNext(pat);
        int i = 0, j = 0;

        while (i < txt.length() && j < pat.length()) {
            // 如果匹配上 则都向后挪一位
            // 如果j == -1说明需要将T向后移动一位，将P归0
            if (j == -1 || pChars[j] == tChars[i]) {
                j++;
                i++;
            } else {
                // 否则找到j不匹配时，j应该移动到的下标位置
                j = next[j];
            }
        }
        // 如果匹配上了 j == pat.length() 返回i - j
        if (j == pat.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String pat = "abcdeiabcd";
        String txt = "kabcdeiabcdefg";
        System.out.println(kmp(pat, txt));

    }
}
