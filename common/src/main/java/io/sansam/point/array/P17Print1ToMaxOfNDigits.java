package io.sansam.point.array;

import java.util.Arrays;

/**
 * <p>
 * P17Print1ToMaxOfNDigits
 * </p>
 *
 * @author houcb
 * @since 2020/5/25 5:34 下午
 */
public class P17Print1ToMaxOfNDigits {

    public static void print1ToMaxOfNDigits(int n) {
        if (n <= 0) return;
        char[] numbers = new char[n];
        Arrays.fill(numbers, '0');

        while (!increment(numbers)) {
            printChars(numbers);
        }
    }

    private static void printChars(char[] numbers) {
        for (char number : numbers) {
            if (number == '0') {
                continue;
            }
            System.out.print(number);
        }

    }

    private static boolean increment(char[] numbers) {
        int overTake = 0;
        int cur;
        for (int i = numbers.length - 1; i >= 0; i--) {
            cur = (numbers[i] - '0') + overTake;
            if (i == numbers.length - 1) {
                cur++;
            }
            if (cur >= 10) {
                if (i == 0) {
                    // 越界了
                    return true;
                }
                overTake = 1;
                cur -= 10;
                numbers[i] = (char) (cur + '0');
            } else {
                numbers[i] = (char) (cur + '0');
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P17Print1ToMaxOfNDigits demo = new P17Print1ToMaxOfNDigits();
//        demo.test(-1);
//        demo.test(0);
//        demo.test(1);
        demo.test(2);
    }

    /**
     * 生成数字
     */
    private void makeNumber(int n, char[] number, int nNumber, int index) {
        if (index == number.length - 1) {
            number[index] = (char) (nNumber + '0');
//            printCharNumber2(number); // 打印数字代码与第一个方法一样
            return;
        } else {
            number[index] = (char) (nNumber + '0');
            for (int i = 0; i <= 9; i++) {
                makeNumber(n, number, i, index + 1);
            }
        }
    }

    // ========测试代码=============
    void test(int nDigits) {
        System.out.println("===test begin===");
        System.out.println("method1:");
        print1ToMaxOfNDigits(nDigits);
        System.out.println("method2:");
//        print1ToMaxOfNDigits2(nDigits);
        System.out.println("===test over===");
    }
}
