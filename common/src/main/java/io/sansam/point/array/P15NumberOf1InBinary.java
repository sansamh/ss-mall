package io.sansam.point.array;

public class P15NumberOf1InBinary {
    // 2.注意：负数右移还是负数！即如果对n=0x8000 0000右移，最高位的1是不会变的。如果这道题目通过令n=n>>1来计算n中1的个数，该数最终会变成0xFFFF FFFF而陷入死循环！
    //　3.把一个整数减1，再和原来的整数做与运算，会把该整数最右边的1变成0。这种方法一定要牢牢记住，很多情况下都可能用到，例如：
    //
    //　　1）一句话判断一个整数是否为2的整数次方；
    //
    //　　2）对两个整数m和n，计算需要改变m二进制表示中的几位才能得到n。
    //
    //　4.与数字操作有关的题目，测试时注意边界值的问题。对于32位数字，其正数的边界值为1、0x7FFFFFFF，负数的边界值为0x80000000、0xFFFFFFFF。
    //
    //　5.几个细节问题
    //
    //　　1）flag=flag<<1，而不是只写一句flag<<1;
    //
    //　　2）flag&n！=0，而非flag&n==1； 也就不能写成count+=(flag&1)了
    //
    //　　3）if语句中，不能写为if(flag&n!=0) ，而要写成 if((flag&n)!=0)，需要注意一下

    public static int NumberOf1_Solution1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    public static int NumberOf1_Solution2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & n - 1;
        }
        return count;
    }

    void test(String testName, int n, int expected) {
        if (testName != null)
            System.out.println(testName + ":");
        if (NumberOf1_Solution1(n) == expected) {
            System.out.print("    soluton1:" + "passed  ");
        } else {
            System.out.print("    solution1:" + "failed  ");
        }

        if (NumberOf1_Solution2(n) == expected) {
            System.out.println("soluton2:" + "passed  ");
        } else {
            System.out.println("solution2:" + "failed  ");
        }
    }

    void test1() {
        test("Test for 0", 0, 0);
    }

    void test2() {
        test("Test for 1", 1, 1);
    }

    void test3() {
        test("Test for 10", 10, 2);
    }

    void test4() {
        test("Test for 0x7FFFFFFF", 0x7FFFFFFF, 31);
    }

    void test5() {
        test("Test for 0xFFFFFFFF", 0xFFFFFFFF, 32);
    }

    void test6() {
        test("Test for 0x80000000", 0x80000000, 1);
    }

    public static void main(String[] args) {
        P15NumberOf1InBinary demo = new P15NumberOf1InBinary();
//        demo.test1();
//        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
        demo.test6();
    }
}
