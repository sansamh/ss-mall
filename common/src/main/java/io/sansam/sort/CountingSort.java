package io.sansam.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountingSort implements Sort{


    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length < 2) return array;

        // 1. 找到最大值 从0开始
        int max = array[0];
        for (int i : array) {
            max = Math.max(max, i);
        }
        max += 1;

        // 2. 申请max大小的数组,统计每个数出现的次数
        int[] timesArr = new int[max];
        for (int i : array) {
            timesArr[i]++;
        }

        // 3. 将2的数组 转换为每个数在 排序数组中所占的第一个位置的下标
        // 统计数组变形 后面的元素等于前面元素之和 目的是定位在结果数组中的排位
        int[] indexArr = new int[max];
        indexArr[0] = timesArr[0];
        for (int i = 1; i < timesArr.length; i++) {
            indexArr[i] = indexArr[i-1] + timesArr[i];
        }

        // 4. 逆序输出原数组 找到每个数的位置
        int[] res = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int num = array[i];
            int index = indexArr[num];
            res[index-1] = num;
            indexArr[num]--;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2,5,3,0,2,3,0,3};
        System.out.println(Arrays.toString(new CountingSort().sort(arr)));
    }
}
