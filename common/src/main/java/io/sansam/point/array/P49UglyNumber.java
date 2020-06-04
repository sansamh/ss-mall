package io.sansam.point.array;

/**
 * <p>
 * P49UglyNumber
 * </p>
 *
 * @author houcb
 * @since 2020-06-04 11:09
 */

/**
 * 题目描述
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 * <p>
 * 分析
 * 首先从题目可以知道，对于一个丑数p，p*2、p*3、p*5都是丑数。
 * <p>
 * 那么从第一个丑数1开始，1*2、1*3、1*5都是丑数，最小的2是第二个丑数；
 * <p>
 * 对于第二个丑数2来说，又多出来三个需要被比较的数字，即2*2、2*3、2*5，再加上第一轮挑剩下的1*3、1*5，但是这里显然可以看出来，1*3<2*3，1*5<2*5，所以其实只需要比较2*2、1*3、1*5即可。<br>
 * ......
 * <p>
 * 按照这样的节奏比下去即可。
 */
public class P49UglyNumber {

    /**
     * """
     * 题目中丑数的定义是只包含因子2,3,5的数，特别地，1是第一个丑数。
     * <p>
     * 解法1：
     * 从1开始，逐个判断每个数字是不是丑数，如果是，则计数器加一，直到找到所要求的第n个丑数为止。
     * 但是这样做的话会对很多非丑数进行计算，时间复杂度太高。
     * <p>
     * 解法2：
     * 定义一个数组，用于按顺序保存已经找到的丑数，再定义三个指针p2, p3, p5，其中p2指向数组中第一个
     * 乘以2之后会比当前数组中末尾元素要大的数字；p3和p5同理。这样，当p2 * 2之后就会比当前最后一个
     * 丑数要大，而当p3 * 3 之后也会比最后一个丑数要大， p5同理。这样，当前最后一个丑数之后的第一个
     * 丑数就出现在p2 * 2, p3 * 3, p5 * 5之间，我们只需要比较这三个数的大小即可找到下一个丑数。
     * 注意每找到一个这样的丑数之后我们就要更新p2, p3, p5，直到我们找到足够多的丑数。
     * 这种方法是以空间换时间，我们维护了一个长度为n的数组，并最终返回这个数组的末尾元素。
     *
     * @param index
     * @return
     */
    public static int find(int index) {
        if (index <= 1) return 1;
        int[] numbers = new int[index];

        numbers[0] = 1;

        int t2 = 0;
        int t3 = 0;
        int t5 = 0;

        for (int i = 1; i < index; i++) {
            numbers[i] = Math.min(Math.min(numbers[t2] * 2, numbers[t3] * 3), numbers[t5] * 5);
            if (numbers[i] == numbers[t2] * 2) {
                t2++;
            }
            if (numbers[i] == numbers[t3] * 3) {
                t3++;
            }
            if (numbers[i] == numbers[t5] * 5) {
                t5++;
            }
        }
        return numbers[index - 1];
    }

    public static void main(String[] args) {
        System.out.println(find(10));
    }
}
