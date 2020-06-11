package io.sansam.point.array;

/**
 * <p>
 * P58ReverseWordsInSentence
 * </p>
 *
 * @author houcb
 * @since 2020-06-08 17:48
 */

/**
 * 题目
 * 　　输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student."，则输出"student. a am I"。
 * <p>
 * 思路
 * 　　一开始自己觉得要用split()方法，但这要开辟新的数组，占内存空间，不行。
 * <p>
 * 　　首先实现翻转整个句子：只需要在首尾两端各放置一个指针，交换指针所指的数字，两端指针往中间移动即可。之后根据空格的位置，对每个单词使用同样的方法翻转即可。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（句子中有一个/多个单词，空格在开头、中间、结尾）
 * <p>
 * 　　2.边界值测试（null，空字符串，句子全为空格）
 */
public class P58ReverseWordsInSentence {

    public static String reverseSentence(char[] chars) {
        if (chars == null || chars.length <= 1) {
            return chars == null ? "" : String.valueOf(chars);
        }
        // 先翻转整个字符串
        reverseArray(chars, 0, chars.length - 1);

        // 再根据空白串翻转整个单词组
        int start = 0;
        int end = 0;
        // start指针指向需要翻转的第一个字符
        // end指针为空时 翻转 start 到 end - 1位置的字符 同时更新start = end + 1
        while (end < chars.length) {
            if (chars[end] == ' ') {
                reverseArray(chars, start, end - 1);
                // 更新start和end的位置 当前end为空字符 如果下一位不是空 start = end + 1 , end ++;
                // 下一位为空 因为start=end 进入判断后 不会翻转字符 继续start = end;
                start = end + 1;
            }
            end++;
        }
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
        String s = "I  am a  student.";
        System.out.println("is :" + reverseSentence(s.toCharArray()));
    }

}
