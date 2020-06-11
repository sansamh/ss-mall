package io.sansam.point.array;

/**
 * <p>
 * P62LastNumberInCircle
 * </p>
 *
 * @author houcb
 * @since 2020-06-10 14:40
 */

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 题目 约瑟夫环
 * 　　0, 1, …, n-1这n个数字排成一个圆圈，从数字0开始每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 思路
 * 　　方法一：采用链表来存放数据，每次对长度取余来实现循环
 * <p>
 * 　　将所有数字放入LinkedList链表中（LinkedList比ArrayList更适合增删操作）。假设当前删除的结点下标为removeIndex，则下一个要删除的结点的下标为：(removeIndex+m-1)%list.size()，通过取余符号可以实现类型循环的操作。
 * <p>
 * 　　注：没必要用循环链表，反而会更麻烦了。
 * <p>
 * 　　方法二：数学推导规律
 * <p>
 * 　　n个数字的圆圈，不断删除第m个数字，我们把最后剩下的数字记为f(n,m)。
 * <p>
 * 　　n个数字中第一个被删除的数字是(m-1)%n， 我们记作k，k=(m-1)%n。
 * <p>
 * 　　那么剩下的n-1个数字就变成了：0,1,……k-1,k+1,……,n-1，我们把下一轮第一个数字排在最前面，并且将这个长度为n-1的数组映射到0~n-2。
 * <p>
 * 　　原始数字：k+1,……,   n-1,       0,    1,……k-1
 * <p>
 * 　　映射数字：0    ,……,n-k-2, n-k-1, n-k,……n-2
 * <p>
 * 　　把映射数字记为x，原始数字记为y，那么映射数字变回原始数字的公式为 y=(x+k+1)%n。
 * <p>
 * 　　在映射数字中，n-1个数字，不断删除第m个数字，由定义可以知道，最后剩下的数字为f(n-1,m)。我们把它变回原始数字，由上一个公式可以得到最后剩下的原始数字是（f(n-1,m)+k+1)%n，而这个数字就是也就是一开始我们标记为的f(n,m)，所以可以推得递归公式如下：
 * <p>
 * 　　　　　　　　　　f(n,m) =（f(n-1,m)+k+1)%n
 * <p>
 * 　　将k=(m-1)%n代入，化简得到：
 * <p>
 * 　　　　　　　　　　f(n,m) =（f(n-1,m)+m)%n
 * <p>
 * 　　　　　　　　　　f(1,m) = 0
 * <p>
 * 　　代码中可以采用循环或者递归的方法实现该递归公式。时间复杂度为O(n)，空间复杂度为O(1)。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（m大于/小于/等于n）
 * <p>
 * 　　2.特殊测试（n、m<=0）
 * <p>
 * 　　3.性能测试（n=4000,n=997）
 * <p>
 * 收获
 * <p>
 * 着重复习数学推导公式和链表余数算法
 * <p>
 * 　　1.对于下标循环一圈类似的问题，通过%可以很好地实现循环，而不需要我们自己构造循环链表；
 * <p>
 * 　　2.(a%n+b)%n=(a+b)%n
 * <p>
 * 　　3.尽量学会本题的数学方法，特别是要掌握好数字间映射的方法。
 * <p>
 * 　　4.公式法中，last=(last+m)% i;  //这里是i不是n！！！
 */
public class P62LastNumberInCircle {


    /**
     * 数组
     *
     * @param n 环中的数字
     * @param m 步长
     * @return
     */
    public static int lastNumberByArray(int n, int m) {
        if (n < 1 || m < 1) return -1;

        // people数组代表 当前所有人 true未淘汰
        boolean[] people = new boolean[n];
        Arrays.fill(people, true);

        // 当前剩余的人
        int left = n;
        // 步长 当step = m时重置为0
        int step = 0;
        // 下标 下标等于总人数时重置为0
        int index = 0;

        while (left > 1) {
            // 当前人未淘汰
            if (people[index]) {
                step++;
                // 当前人需要淘汰
                if (step == m) {
                    // 置为淘汰 步长归0 剩余人-1
                    people[index] = false;
                    step = 0;
                    left--;
                }
            }
            // 移动下标 如果下标到了最后 重置为0
            index++;

            if (index == n) {
                index = 0;
            }
        }

        for (int i = 0; i < people.length; i++) {
            if (people[i]) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 链表
     *
     * @param n 环中的数字
     * @param m 步长
     * @return
     */
    public static int lastNumberByList(int n, int m) {
        if (n < 1 || m < 1) return -1;

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int index = 0;
        while (list.size() > 1) {
            index = (index + m - 1) % list.size();
            list.remove(index);
        }

        return list.get(0);
    }


    /**
     * 数学推导
     *
     * @param n 环中的数字
     * @param m 步长
     * @return
     */
    public static int lastNumberByMath(int n, int m) {
        if (n < 1 || m < 1) return -1;

        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    public static void main(String[] args) {
        System.out.println(lastNumberByArray(10, 2));
        System.out.println(lastNumberByList(10, 2));
        System.out.println(lastNumberByMath(10, 2));
    }
}
