package io.sansam.point.array;

/**
 * <p>
 * P67StringToInt
 * </p>
 *
 * @author houcb
 * @since 2020-06-11 09:54
 */
public class P67StringToInt {

    static boolean isValid = false;

    public static int strToInt(String str) {
        if (str == null || str.length() == 0 || " ".equals(str)) {
            isValid = false;
            return 0;
        }

        char[] chars = str.toCharArray();

        long num = 0L;
        int temp;
        boolean minus = false;

        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && chars[i] == '-') {
                minus = true;
            } else if (i == 0 && chars[i] == '+') {
                minus = false;
            } else {
                temp = chars[i] - '0';
                if (temp > 9 || temp < 0) {
                    isValid = false;
                    return 0;
                }
                // 正数 123 第一次num = 0 * 10 + 1 = 1 第二次num = 1 * 10 + 2 = 12 第三次num = 12 * 10 + 3 = 123
                if (!minus) {
                    num = num * 10 + temp;
                }
                // 负数 第一次num= 0 * 10 - 1 = -1 第二次num = -1 * 10 - 2 = -12 第三次num = -12 * 10 - 3 = -123
                else {
                    num = num * 10 - temp;
                }
                // 判断是否溢出 [0x80000000, 0x7FFFFFFF] 包含两端
                if ((!minus && num > 0x7FFFFFFF)
                        || (minus && num < 0x80000000)) {
                    isValid = false;
                    return 0;
                }
                // 每走一步处理isValid
                isValid = true;
            }
        }


        return (int) num;

    }

    public static void main(String[] args) {
        System.out.println(strToInt("1948243") == 1948243);
        System.out.println(isValid == true);
        System.out.println(strToInt("+1948243") == 1948243);
        System.out.println(isValid == true);
        System.out.println(strToInt("-1948243") == -1948243);
        System.out.println(isValid == true);
        System.out.println(strToInt("-0") == 0);
        System.out.println(isValid == true);
        System.out.println(strToInt("-194+8243") == 0);
        System.out.println(isValid == false);
        System.out.println(strToInt("") == 0);
        System.out.println(isValid == false);
        System.out.println(strToInt(null) == 0);
        System.out.println(isValid == false);
        System.out.println(strToInt("999999999999999") == 0);
        System.out.println(isValid == false);
        System.out.println(strToInt("+") == 0);
        System.out.println(isValid == false);

        System.out.println(strToInt("2147483647") == 2147483647); //0x7FFFFFFF
        System.out.println(isValid == true);
        System.out.println(strToInt("2147483648") == 0);
        System.out.println(isValid == false);

        System.out.println(strToInt("-2147483648") == -2147483648); //0x80000000
        System.out.println(isValid == true);
        System.out.println(strToInt("-2147483649") == 0);
        System.out.println(isValid == false);
    }
}
