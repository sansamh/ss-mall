package io.sansam.point.array;

/**
 * <p>
 * P38StringPermutation
 * </p>
 *
 * @author houcb
 * @since 2020/5/29 4:43 下午
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * 题目
 * 　　输入一个字符串，打印出该字符串中字符的所有排列。例如输入字符串abc，则打印出由字符a、b、c所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。（本文代码采用ArrayList<String>接收返回的字符串，并要求不出现重复字符串）
 * <p>
 * 思路
 * 　　将字符串看成两部分，一部分是第一个字符，另一部分是后面的所有字符。
 * <p>
 * 　　首先确定第一个字符，该字符可以是字符串中的任意一个；固定第一个字符后，求出后面所有字符的排列（相同步骤，采用递归）。
 * <p>
 * 　　实现第一个字符的改变，只需要将第一个字符和后面所有字符进行交换即可（最早自己想的是从原始字符串拿出第i个字符，然后合并剩下的字符到后面，其实就是个交换的过程，自己开始时想得太复杂了）。要记得字符串输出后要将字符交换回来，变回原始的字符串。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（有多个重复字母的字符串、所有字符相同的字符串、一个字符或者多个字符的普通字符串）
 * <p>
 * 　　2.特殊测试（字符串为null、“”）
 * <p>
 * 收获
 * 　　1.要对字符串进行修改，可以将字符串转化为字符数组进行修改，也可以考虑使用StringBuilder类。
 * <p>
 * 　　2.list.contains()方法可以直接判断是否有重复字符串；Collections.sort(list)可以将list中的字符串进行排序。
 * <p>
 * 　　3.字符串和字符数组间的转化：str.toCharArray()     String.valueOf(strArray)
 * <p>
 * 　　4.数组在递归过程中进行了交换后，最终要记得交换回来（代码最后几行）
 */
public class P38StringPermutation {

    public static ArrayList<String> permutation(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if (str == null || str.length() == 0)
            return list;
        permutationCore(str.toCharArray(), 0, list);
        Collections.sort(list);  //将list中的字符串排序
        return list;
    }

    private static void permutationCore(char[] toCharArray, int start, ArrayList<String> list) {
        if (start == toCharArray.length - 1) {
            if (!list.contains(String.valueOf(toCharArray))) {
                list.add(String.valueOf(toCharArray));
            }
        } else {
            for (int i = start; i < toCharArray.length; i++) {
                swap(toCharArray, start, i);
                permutationCore(toCharArray, start + 1, list);
                swap(toCharArray, start, i);
            }
        }
    }

    public static void swap(char[] strArr, int a, int b) {
        char temp = strArr[a];
        strArr[a] = strArr[b];
        strArr[b] = temp;
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(permutation(s));
    }

}
