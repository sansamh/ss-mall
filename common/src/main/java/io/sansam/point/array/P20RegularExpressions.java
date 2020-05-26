package io.sansam.point.array;

/**
 * <p>
 * P20RegularExpressions
 * </p>
 *
 * @author houcb
 * @Description 面试题19：正则表达式匹配
 * @author yongh
 * @date 2018年9月21日 上午8:12:06
 * @since 2020/5/26 5:48 下午
 */


/**
 *
 * @Description 面试题19：正则表达式匹配
 *
 * @author yongh
 * @date 2018年9月21日 上午8:12:06
 */


/**
 *
 * 题目：
 * 请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'
 * 表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题
 * 中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"
 * 和"ab*ac*a"匹配，但与"aa.a"及"ab*a"均不匹配。
 *
 * 思路
 * 　　使用函数matchCore(char[] str, int indexOfStr, char[] pattern, int indexOfPattern) 来实现每一步的比较（递归）。
 *
 * 　　（1）当模式中第二个字符不为“*”时：若当前字符相等，则字符串和模式都后移一个字符，继续调用函数进行比较；若不相等，则返回false。
 *
 * 　　（2）当模式中第二个字符为“*”时：若当前字符不相等，则模式后移两个字符，继续比较；若当前字符相等，则有三种情况：
 *
 * 　　　　1）字符串字符位置不变，模式后移两个字符，继续比较；  //x*被忽略
 *
 * 　　　　2）字符串后移一个字符，模式后移两个字符，继续比较；
 *
 * 　　　　3）字符串后移一个字符，模式字符位置不变，继续比较。
 *
 * 　　三种情况使用“||”进行并列比较。
 *
 * 注意点
 *
 * 　　时刻要注意数组是否越界！
 *
 * 测试算例
 *
 * 　　1.功能测试（模式中包含普通字符、“.”、“*”；匹配情况；不匹配情况）
 *
 * 　　2.特殊测试（null，空字符串）
 *
 *
 *
 */

public class P20RegularExpressions {

    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        return doMath(str, 0, pattern, 0);
    }

    private static boolean doMath(char[] str, int si, char[] pattern, int pi) {
        // 同时到达最后 完成匹配
        if (si == str.length && pi == pattern.length) {
            return true;
        }
        // 模式串结束 字符串还没结束
        if (pi == pattern.length && si < str.length) {
            return false;
        }
        // 字符相同 或者遇到 . 继续匹配下个字符
        if (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            return doMath(str, si + 1, pattern, pi + 1);
        }

        if (si < str.length && pattern[pi] == '*') {
            // 判断pi前一个字符是什么
            if (pi == 0) {
                return doMath(str, si, pattern, pi + 1);
            } else {
                char c = pattern[pi - 1];
                if (str[si] != c) {
                    si++;
                } else {
                    while (si < str.length && str[si] == c) {
                        si++;
                    }
                }
                return doMath(str, si, pattern, pi + 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P20RegularExpressions demo = new P20RegularExpressions();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
        demo.test6();
        demo.test7();
    }

    void test(String testName, char[] str, char[] pattern, boolean expected) {
        System.out.print(testName + ":");
        if (match(str, pattern) == expected)
            System.out.println("passed!");
        else
            System.out.println("failed!");
    }

    void test1() {
        char[] str = {};
        char[] pattern = {'.'};
        test("test1", str, pattern, false);
    }

    void test2() {
        char[] str = {};
        char[] pattern = {'.', '*'};
        test("test2", str, pattern, true);
    }

    void test3() {
        char[] str = {'a'};
        char[] pattern = {'.', '*'};
        test("test3", str, pattern, true);
    }

    void test4() {
        char[] str = {};
        char[] pattern = {};
        test("test4", str, pattern, true);
    }

    void test5() {
        char[] str = null;
        char[] pattern = null;
        test("test5", str, pattern, false);
    }

    void test6() {
        char[] str = {'a', 'b', 'b'};
        char[] pattern = {'a', 'b', 'b', '*', 'b'};
        test("test6", str, pattern, true);
    }

    void test7() {
        char[] str = {'a'};
        char[] pattern = {'a', 'a', '*'};
        test("test7", str, pattern, true);
    }
}
