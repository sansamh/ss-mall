package io.sansam.point.array;

/**
 * <p>
 * P66ConstuctArray
 * </p>
 *
 * @author houcb
 * @since 2020-06-11 09:37
 */

/**
 * 题目
 * 　　给定一个数组A[0, 1, …, n-1]，请构建一个数组B[0, 1, …, n-1]，其中B中的元素B[i] =A[0]×A[1]×… ×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * <p>
 * 回到顶部
 * 思路
 * 　　无法使用除法，正常连乘的话时间复杂度为O(n^2)，效率非常低。
 * <p>
 * 　　考虑到计算每个B[i]时都会有重复，思考B[i]之间的联系，找出规律，提高效率。
 * <p>
 * <p>
 * <p>
 * 图片转自构建乘积数组 https://www.cnblogs.com/wxdjss/p/5448990.html
 * <p>
 * 　　如上图所示，可以发现：
 * <p>
 * 　　　　B[i]的左半部分(红色部分)和B[i-1]有关（将B[i]的左半部分乘积看成C[i]，有C[i]=C[i-1]*A[i-1]），
 * <p>
 * 　　　　B[i]的右半部分(紫色部分)与B[i+1]有关（将B[i]的右半部分乘积看成D[i]，有D[i]=D[i+1]*A[i+1]），
 * <p>
 * 　　因此我们先从0到n-1遍历，计算每个B[i]的左半部分；  然后定义一个变量temp代表右半部分的乘积，从n-1到0遍历，令B[i]*=temp，而每次的temp与上次的temp关系即为temp*=A[i+1]。
 * <p>
 * <p>
 * <p>
 * 测试代码
 * <p>
 * 　　1.功能测试（正、负、零）
 * <p>
 * 　　2.边界值测试（数组长度为2）
 * <p>
 * 　　3.特殊测试（null，数组长度为1，0）
 */
public class P66ConstuctArray {

    public static int[] multiply(int[] a) {
        if (a == null || a.length < 1) return a;

        int[] b = new int[a.length];

        b[0] = 1;
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        int temp = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            temp *= a[i + 1];
            b[i] *= temp;
        }
        return b;
    }
}
