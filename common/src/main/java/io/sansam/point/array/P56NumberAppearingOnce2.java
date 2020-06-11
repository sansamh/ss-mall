package io.sansam.point.array;

/**
 * <p>
 * P56NumberAppearingOnce2
 * </p>
 *
 * @author houcb
 * @since 2020-06-08 15:35
 */

/**
 * 题目
 * 　　在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * 思路
 * 　　这道题中数字出现了三次，无法像56-1) 数组中只出现一次的两个数字一样通过利用异或位运算进行消除相同个数字。但是仍然可以沿用位运算的思路。
 * <p>
 * 　　将所有数字的二进制表示的对应位都加起来，如果某一位能被三整除，那么只出现一次的数字在该位为0；反之，为1。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（唯一出现的数字是0，正数，负数；重复出现的数字是0，正数，负数）
 * <p>
 * 收获
 * 　　1.判断某个数x的第n位（如第3位）上是否为1，
 * <p>
 * 　　　　1）通过 x&00000100 的结果是否为0 来判断。（不能根据是否等于1来判断）
 * <p>
 * 　　　　2）通过（x>>3)&1 是否为0 来判断
 * <p>
 * 　　2.通过number&bitMask的结果是否为0（不能用1判断），bitMask=1不断左移，可以将一个数的二进制存储到32位的数组中。
 * <p>
 * int number=100;
 * int bitMask=1;
 * for(int j=31;j>=0;j--) {
 * int bit=number&bitMask;  //注意arr[i]&bitMask不一定等于1或者0，有可能等于00010000
 * if(bit!=0)
 * bits[j]=1;
 * bitMask=bitMask<<1;
 * }
 * 　　3.通过以下代码实现二进制转化为数字（注意左移语句的位置）：
 * <p>
 * int result=0;
 * for(int i=0;i<32;i++) {
 * result=result<<1;
 * result+=bits[i];
 * //result=result<<1;  //不能放在后面，否则最前面一位就没了
 * }
 */
public class P56NumberAppearingOnce2 {

    public static int findNumberAppearingOnce(int[] arr) {
        if (arr == null || arr.length < 4) return -1;
        int[] bits = new int[32];

        int bitMask;
        for (int i = 0; i < arr.length; i++) {
            bitMask = 1;
            for (int j = 31; j >= 0; j--) {
                // 此位不为0
                if ((arr[i] & bitMask) != 0) {
                    bits[j] += 1;
                }
                bitMask = bitMask << 1;
            }
        }

        int result = 0;

        for (int i = 0; i < bits.length; i++) {
            // 如果此位能被3整除 则唯一数的此位为0 否则为1
            // 三个相同数此位相加肯定能被3整除
            result = result << 1;
            result += (bits[i] % 3);

            //result = result<<1;  //不能放在后面，否则最前面一位就没了
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-4, 2, 2, 2, 3, 3, 3, 5, 5, 5, 8, 8, 8};
        System.out.println(findNumberAppearingOnce(arr));
    }
}
