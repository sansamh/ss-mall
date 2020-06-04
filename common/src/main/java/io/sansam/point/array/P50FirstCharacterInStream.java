package io.sansam.point.array;

/**
 * <p>
 * P50FirstCharacterInStream
 * </p>
 *
 * @author houcb
 * @since 2020-06-04 14:34
 */

/**
 * 题目
 * 　　请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是'g'。当从该字符流中读出前六个字符"google"时，第一个只出现一次的字符是'l'。
 * <p>
 * 思路
 * 　　字符只能一个一个从字符流中读出来，因此要定义一个容器来保存字符以及其在字符流中的位置。
 * <p>
 * 　　为尽可能高效解决问题，要在O(1)时间内往数据容器中插入字符，及其对应的位置，因此这个数据容器可以用哈希表来实现，以字符的ASCII码作为哈希表的键值key，字符对应的位置作为哈希表的值value。
 * <p>
 * 　　开始时，哈希表的值都初始化为-1，当读取到某个字符时，将位置存入value中，如果之前读取过该字符（即value>=0），将value赋值为-2，代表重复出现过。最后对哈希表遍历，在value>=0的键值对中找到最小的value，该value即为第一个只出现一次的字符，ASCII码为key的字符即为所求字符。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（读入一个字符；读入多个字符；所有字符都唯一；所有字符重复）
 * <p>
 * 　　2.特殊测试（读入0个字符）
 * <p>
 * 收获
 * 　　1.对于数据流、字符流等，需要定义数据容器来保存记录。
 * <p>
 * 　　　　流和串的区别：
 * <p>
 * 　　　　1）串：字符串已经保存下来了，能够读取遍历，因此在字符串中第一个只出现一次的字符中，只需要存下每个字符出现的个数，然后直接在字符串中遍历；
 * <p>
 * 　　　　2）流：字符流没有存下来，无法进行遍历，因此在本题中，只能在数据容器哈希表中遍历，而且哈希表中存放的是对应字符的位置，而不是个数。
 * <p>
 * 　　2.记得会用构造函数来初始化参数；
 * <p>
 * 　　3.Integer.MAX_VALUE=2^31-1，是32位操作系统（4字节）中最大的符号型整型常量。
 */
public class P50FirstCharacterInStream {

    // 字符出现的index
    private static int index = 0;
    // 存放字符出现的次数
    private static int[] container = new int[256];

    public P50FirstCharacterInStream() {
        for (int i = 0; i < container.length; i++) {
            container[i] = -1;
        }
    }

    public static void insert(char ch) {
        if (container[(int) ch] == -1) {
            container[(int) ch] = index;
        } else {
            container[(int) ch] = -2;
        }
        // 处理一个字符 index++
        index++;
    }

    public static char getFirst() {
        int min = Integer.MAX_VALUE;
        char ch = '\0';
        for (int i = 0; i < container.length; i++) {
            if (container[i] >= 0 && container[i] < min) {
                min = container[i];
                ch = (char) i;
            }
        }
        System.out.println("min index = " + min + " min char = " + ch);
        return ch;
    }

    public static void main(String[] args) {
        //字符转化为ASCII码
        char ch_a = 'a';
        int code_a = (int) ch_a; // =ASCII码97

        //ASCII码转化为字符
        char copyCh_a = (char) code_a;  // =ASCII码97对应的字符'a'

        //字符形式数字转化为整型
        char c1 = '2';
        int n1 = c1 - '0';  //=2, 由'2'和'1'的ASCII码相减得到

        //数字转化为字符形式
        char copyC1 = (char) (n1 + '0');  //='2' ,由'0'的ASCII码加2得到'2'的ASCII码
        System.out.println(5);
    }


}
