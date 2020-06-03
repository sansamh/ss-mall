package io.sansam.point.array;

/**
 * <p>
 * P44DigitsInSequenc
 * </p>
 *
 * @author houcb
 * @since 2020-06-02 17:12
 */

/**
 * 题目
 * 　　数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从0开始计数）是5，第13位是1，第19位是4，等等。请写一个函数求任意位对应的数字。
 * <p>
 * 思路
 * 　　逐一枚举数字，计算每个数字的位数相加，效率太低。
 * <p>
 * 　　观察规律：
 * <p>
 * 　　个位数的个数一共有10个，即0~9，共占了10*1位数字；
 * <p>
 * 　　两位数的个数一共有90个，即10~99，每个数字占两位，共占了90*2位数字；
 * <p>
 * 　　……
 * <p>
 * 　　m位数的个数一共有9*10^(m-1)个，每个数字占m位，占了9*10^(m-1)*m位数字。
 * <p>
 * 　　判断第n个对的数字是属于几位数，再从几位数中进行寻找。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（输入19、1000等）
 * <p>
 * 　　2.边界值测试（输入0、1等）
 */
public class P44DigitsInSequenc {

    public static void main(String[] args) {
        System.out.println(new P44DigitsInSequenc().digitAtIndex(1000));
    }

    public int digitAtIndex(int index) {
        if (index < 0) {
            return -1;
        }

        int digits = 1;
        while (true) {
            // 找到m位数 所占数字个数
            int numbers = numbersOfIntegers(digits);  //m位数的个数
            // 如果index小于m位数 每个数字所占的字符数 则index位于m位数之间
            if (index < numbers * digits)
                return getDigit(index, digits);
            // 否则index减去 m位数所占字符数
            index -= numbers * digits;
            digits++;
        }
    }

    private int getDigit(int index, int m) {
        int number = getFirstNumber(m) + index / m;  //对应的m位数
        int indexFromRight = m - index % m;  //在数字中的位置
        for (int i = 1; i < indexFromRight; i++)
            number /= 10;
        return number % 10;
    }

    /**
     * 返回m位数的总个数
     * 例如，两位数一共有90个：10~99；三位数有900个：100~999
     *
     * @param digits
     * @return
     */
    private int numbersOfIntegers(int digits) {
        if (digits == 1) {
            return 10;
        }
        // 9 * 10^(digits-1)
        return (int) (9 * Math.pow(10, digits - 1));
    }

    /**
     * m位数的第一个数字
     *
     * @param m
     * @return
     */
    private int getFirstNumber(int m) {
        if (m == 1)
            return 0;
        return (int) Math.pow(10, m - 1);
    }
}
