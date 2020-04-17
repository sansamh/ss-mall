package io.sansam.config;

import java.util.Arrays;

/**
 * <p>
 * KMP
 * </p>
 *
 * @author houcb
 * @since 2020-04-17 16:51
 */
public class KMP {

    public static int kmp(String pat, String txt) {
        final char[] pats = pat.toCharArray();
        final char[] txts = txt.toCharArray();
        int j = 0, i = 0;
        int[] next = getNext(pat);
        while (i < txts.length && j < pats.length) {
            if (j == -1 || pats[j] == txts[i]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pats.length) {
            return i - j;
        }

        return -1;
    }

    private static int[] getNext(String pat) {
        final char[] chars = pat.toCharArray();
        int[] next = new int[pat.length()];
        int k = -1;
        next[0] = -1;
        int j = 0;

        while (j < pat.length() - 1) {
            if (k == -1 || chars[j] == chars[k]) {
                if (chars[++j] == chars[++k]) {
                    next[j] = next[k];
                } else {
                    next[++j] = ++k;
                }
            } else {
                k = next[k];
            }
        }
        System.out.println(Arrays.toString(next));
        return next;
    }

    public static void main(String[] args) {
        String pat = "abcdeiabcd";
        String txt = "kabcdeiabcdefg";
        System.out.println(kmp(pat, txt));
    }
}
