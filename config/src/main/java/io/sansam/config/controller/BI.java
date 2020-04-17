package io.sansam.config.controller;

import java.util.Arrays;

/**
 * <p>
 * BI
 * </p>
 *
 * @author houcb
 * @since 2020/4/7 5:16 下午
 */
public class BI {

    // https://www.cnblogs.com/tangzhengyue/p/4315393.html
    public static int[] next(String pat) {
        int[] next = new int[pat.length()];
        final char[] chars = pat.toCharArray();
        int j = 0;
        int k = -1;
        next[0] = -1;

        while (j < pat.length() - 1) {
            if (k == -1 || chars[j] == chars[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }

        return next;
    }

    public static int kmp(String pat, String txt) {
        final char[] patChars = pat.toCharArray();
        final char[] txtChars = txt.toCharArray();
        // i txt指针
        // j pat指针
        int i = 0, j = 0;
        final int[] next = getNext(pat);

        while (i < txt.length() && j < pat.length()) {
            if (j == -1 || patChars[j] == txtChars[i]) {
                j++;
                i++;
            } else {
                j = next[j];
            }
        }
        if (j == pat.length()) {
            return i - j;
        }

        return -1;
    }


    public static void bi(String pat, String txt) {
        char[] patChars = pat.toCharArray();
        char[] Txtchars = txt.toCharArray();
        // i为pat指针 j为txt指针
        int i = 0, j = 0;

        while (i < pat.length() && j < txt.length()) {
            if (patChars[i] == Txtchars[j]) {
                i++;
                j++;
            } else {
                j = j - i + 1;
                i = 0;
            }
        }
        if (i == pat.length()) {
            System.out.println("find start " + (j - i));
        } else {
            System.out.println("-1");
        }
    }

    public static void main(String[] args) {
        String pat = "abcdeiabcd";
        String txt = "abcdeiabcdefg";
        System.out.println(kmp(pat, txt));
    }

    /**
     * cnblogs.com/dusf/p/kmp.html
     *
     * @param pat
     * @return
     */
    public static int[] getNext(String pat) {
        final char[] chars = pat.toCharArray();
        int[] next = new int[pat.length()];
        next[0] = -1;
        // 格式串下标
        int j = 0;
        // next[j] = k，表示当T[i] != P[j]时，j指针的下一个位置。
        // 另一个非常有用且恒等的定义，因为下标从0开始的，k值实际是j位前的子串的最大重复子串的长度。
        // 请时刻牢记next数组的定义，下面的解释是死死地围绕着这个定义来解释的。
        int k = -1;

        while (j < pat.length() - 1) {
            if (k == -1 || chars[j] == chars[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        System.out.println(Arrays.toString(next));
        return next;
    }
}
