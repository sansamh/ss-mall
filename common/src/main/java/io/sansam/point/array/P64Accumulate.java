package io.sansam.point.array;

/**
 * <p>
 * P64Accumulate
 * </p>
 *
 * @author houcb
 * @since 2020-06-10 16:42
 */

/**
 * 题目
 * 　　求1+2+…+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * <p>
 * 思路
 * <p>
 * <p>
 * 　　不能使用乘除法，不能使用循环语句、判断语句。可以考虑的有   单目运算符：++和--,双目运算符：+,-，移位运算符<<和>>，关系运算符>,<等，逻辑运算符&&，||,&,|,^，赋值=
 * <p>
 * 　　最有可能使用到的就是逻辑运算符了。如果记得它们有短路特性的话，就可以当作if来使用了。
 * <p>
 * 例如：对于A && B，如果A为假，那么就不执行B了；而如果A为真，就会执行B。
 * <p>
 * 　　　对于A || B，如果A为真，那么就会不执行B了；而如果A为假，就会执行B。
 * <p>
 * 　　因此我们使用递归来代替循环，用逻辑运算符&&或者||来代替判断语句。
 * <p>
 * 　　代码实现功能为：当n大于1时，和为f(n)=f(n-1)+n，n=1时，f(n)=1
 */
public class P64Accumulate {

    public static int getSum(int n) {
        if (n <= 1) return n;
        int sum = n;
        // 短路与 前一个为true 执行第二个， 前一个为false 不执行第二个
        // n = 1时 直接返回 sum = n = 1
        boolean res = (n > 1) && ((sum += getSum(n - 1)) > 0);
//        // 等同于
//        if (n > 0) {
//            sum += getSum(n-1);
//        }
        // 短路或者 前一个为false 进行第二个，前一个为true 不执行第二个
//        boolean res = (n == 1) || (sum += getSum(n - 1)) > 0;

        return sum;
    }


    public static void main(String[] args) {
        System.out.println(getSum(100));
    }
}
