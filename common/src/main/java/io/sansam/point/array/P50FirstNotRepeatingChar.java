package io.sansam.point.array;

/**
 * <p>
 * P50FirstNotRepeatingChar
 * </p>
 *
 * @author houcb
 * @since 2020-06-04 11:35
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 题目
 * 　　在字符串中找出第一个只出现一次的字符。如输入"abaccdeff"，则输出'b'。
 * <p>
 * 思路
 * 　　创建哈希表，键值key为字符，值value为出现次数。第一遍扫描：对每个扫描到的字符的次数加一；第二遍扫描：对每个扫描到的字符通过哈希表查询次数，第一个次数为1的字符即为符合要求的输出。
 * <p>
 * 　　由于字符（char）是长度为8的数据类型，共有256中可能，因此哈希表可以用一个长度为256的数组来代替，数组的下标相当于键值key，对应字符的ASCII码值；数组的值相当于哈希表的值value，用于存放对应字符出现的次数。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（存在/不存在只出现一次的字符；全部都为只出现一次的字符）
 * <p>
 * 　　2.特殊测试（null）
 */
public class P50FirstNotRepeatingChar {

    public static char firstNotRepeatingChar(String str) {
        if (str == null) return '\0';
        if (str.length() == 1) return str.charAt(0);

        Map<Character, Integer> map = new HashMap<>();
        Character character;
        // 遍历字符串 获取每个字符出现的次数
        for (int i = 0; i < str.length(); i++) {
            character = str.charAt(i);
            if (map.containsKey(character)) {
                map.put(character, map.get(character) + 1);
            } else {
                map.put(character, 1);
            }
        }
        // 遍历字符串 获取第一个出现一次的字符
        for (int i = 0; i < str.length(); i++) {
            character = str.charAt(i);
            if (map.get(character) == 1) {
                return character;
            }
        }

        return '\0';
    }

    public static void main(String[] args) {
        System.out.println((P50FirstNotRepeatingChar.firstNotRepeatingChar("google") == 'l'));
        System.out.println((P50FirstNotRepeatingChar.firstNotRepeatingChar("aabccdbd") == '\0'));
        System.out.println((P50FirstNotRepeatingChar.firstNotRepeatingChar("$abcdefg") == '$'));
        System.out.println((P50FirstNotRepeatingChar.firstNotRepeatingChar(null) == '\0'));
    }
}
