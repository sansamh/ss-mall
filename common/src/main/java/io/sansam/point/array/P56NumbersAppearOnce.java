package io.sansam.point.array;

/**
 * <p>
 * P56NumbersAppearOnce
 * </p>
 *
 * @author houcb
 * @since 2020-06-05 16:58
 */

/**
 * 题目
 * 　　一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * 思路
 * 　　记住：两个相同的数字异或等于0.
 * <p>
 * 　　如果数组中只有一个数字只出现一次，我们从头到尾异或每个数字，那么最终的结果刚好是那个只出现一次的数字。
 * <p>
 * 　　而本题里数组中有两个数字只出现一次，如果能够将数组分为两部分，两部分中都只有一个数字只出现一次，那么就可以解决该问题了。
 * <p>
 * 　　求解方法：
 * <p>
 * 　　我们依旧从头到尾异或每个数字，那么最终的结果就是这两个只出现一次的数字的异或结果，由于两个数不同，因此这个结果数字中一定有一位为1，把结果中第一个1的位置记为第n位。因为是两个只出现一次的数字的异或结果，所以这两个数字在第n位上的数字一定是1和0。
 * <p>
 * 　　 接下来我们根据数组中每个数字的第n位上的数字是否为1来进行分组，恰好能将数组分为两个都只有一个数字只出现一次的数组，对两个数组从头到尾异或，就可以得到这两个数了。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（数组中有多对重复的数字；无重复的数字）
 * <p>
 * 收获
 * 　　1.当一个数字出现两次（或者偶数次）时，用异或^ 可以进行消除。一定要牢记 异或的这个功能！
 * <p>
 * 　　2.将一组数字分为两组，可以根据某位上是否为1来进行分组，即根据和1相与（&1）的结果来进行分组。
 * <p>
 * 　　3.判断某个数x的第n位（如第3位）上是否为1，
 * <p>
 * 　　　　1）通过 x&00000100 的结果是否为0 来判断。（不能根据是否等于1来判断）
 * <p>
 * 　　　　2）通过（x>>3)&1 是否为0 来判断
 * <p>
 * 　　4.将某个数x右移m位，一定要写成 x=x>>m；而不能只写成 x>>m；这个语句
 */
public class P56NumbersAppearOnce {

    public static int[] findNumsAppearOnce(int[] array) {
        int[] res = new int[2];
        if (array == null || array.length < 2) {
            return res;
        }
        // 一次异或 得到结果
        int xor = array[0];
        for (int i = 1; i < array.length; i++) {
            xor ^= array[i];
        }
        // 找到异或结果中第一位为1的位置 判断是否超出32位溢出
        int index = 0;
        while ((xor & 1) == 0 && (index <= 4 * 8)) {
            index++;
            xor = xor >> 1;
        }
        // 循环数组 找到第index为1和不为1的数字 分别异或得到结果
        for (int i = 0; i < array.length; i++) {
            if ((array[i] >> index & 1) == 0) {
                res[0] ^= array[i];
            } else {
                res[1] ^= array[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] data = new int[]{6, 4, 4, 6, 3, 2, 5, 5};
        int[] result = findNumsAppearOnce(data); // 4,6
        System.out.println(result[0]);
        System.out.println(result[1]);
    }


}
