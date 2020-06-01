package io.sansam.point.array;

/**
 * <p>
 * P39MoreThanHalfNumber
 * </p>
 *
 * @author houcb
 * @since 2020-06-01 14:59
 */

/**
 * 题目
 * 　　数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1, 2, 3, 2, 2, 2, 5, 4, 2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * <p>
 * 思路
 * 　　思路一：数字次数超过一半，则说明：排序之后数组中间的数字一定就是所求的数字。
 * <p>
 * 　　利用partition()函数获得某一随机数字，其余数字按大小排在该数字的左右。若该数字下标刚好为n/2，则该数字即为所求数字；若小于n/2，则在右边部分继续查找；反之，左边部分查找。
 * <p>
 * 　　思路二：数字次数超过一半，则说明：该数字出现的次数比其他数字之和还多
 * <p>
 * 　　遍历数组过程中保存两个值：一个是数组中某一数字，另一个是次数。遍历到下一个数字时，若与保存数字相同，则次数加1，反之减1。若次数=0，则保存下一个数字，次数重新设置为1。由于要找的数字出现的次数比其他数字之和还多，那么要找的数字肯定是最后一次把次数设置为1的数字。
 * <p>
 * 　　也可以这样理解（来源：牛客网 cm问前程）：
 * <p>
 * 　　采用阵地攻守的思想：
 * 　　第一个数字作为第一个士兵，守阵地；count = 1；
 * 　　遇到相同元素，count++;
 * 　　遇到不相同元素，即为敌人，同归于尽,count--；当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是主元素。
 * 　　再加一次循环，记录这个士兵的个数看是否大于数组一般即可。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（存在或者不存在超过数组长度一半的数字）
 * <p>
 * 　　2.特殊测试（null、1个数字）
 */
public class P39MoreThanHalfNumber {

    public static boolean isValid = false;

    /**
     * 方法二 遍历数组 遇到相同的数字 count++ ，不同数字的话如果count==0 则更改结果值res，如果count!=0 不更改res count--
     *
     * @param arr
     * @return
     */
    public static int moreThanHalf(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int res = -1;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == res) {
                count++;
            } else {
                if (count == 0) {
                    res = arr[i];
                    count++;
                } else {
                    count--;
                }
            }
        }
        if (count > 0) {
            int num = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == res) {
                    num++;
                }
            }
            if (num * 2 > arr.length) {
                isValid = true;
                return res;
            }
        }

        return -1;
    }

    /**
     * 方法一
     *
     * @param arr
     * @return
     */
    public static int moreThanHalf2(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int low = 0;
        int high = arr.length - 1;
        int index = partion(arr, low, high);
        while (index != arr.length >> 1) {
            if (index < arr.length >> 1) {
                low = index + 1;
                index = partion(arr, low, high);
            } else {
                high = index - 1;
                index = partion(arr, low, high);
            }
        }
        //判断次数是否超过一半
        int num = arr[index];
        int times = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                times++;
            }
        }
        if (times * 2 > arr.length) {
            isValid = true;
            return num;
        }

        return -1;
    }

    private static int partion(int[] arr, int low, int high) {
        int base = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= base) {
                high--;
            }
            int temp = arr[high];
            arr[high] = arr[low];
            arr[low] = temp;
            while (low < high && arr[low] <= base) {
                low++;
            }
            temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
        }
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};


        System.out.println(moreThanHalf2(arr));
    }
}
