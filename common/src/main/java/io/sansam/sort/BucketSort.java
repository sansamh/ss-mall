package io.sansam.sort;


import java.util.Arrays;
import java.util.Random;

public class BucketSort implements Sort {


    private int bucketSize;

    private Sort quickSort = new QuickSort();

    public BucketSort(int bucketSize) {
        this.bucketSize = bucketSize;
    }


    @Override
    public int[] sort(int[] array) {
        if (this.bucketSize <= 0) throw new RuntimeException("bucketSize设置错误！");
        if (array == null || array.length <= 1) return array;

        // 1. 找到最大值和最小值
        int min = array[0], max = array[1];
        for (int i : array) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        // 2. 根据最大值和最小值和桶的大小 确定桶的数量
        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];

        // 每个桶元素的下标到哪了 indexArr[i] 表示第i个桶当前可以放的下标 也就是indexArr[i]最大为 第i个桶的size-1
        int[] indexArr = new int[bucketCount];

        // 3. 将数组中值分配到各个桶里
        for (int i : array) {
            int bucketIndex = (i - min) / bucketCount;

            int index = indexArr[bucketIndex];

            // 扩容
            if (index == bucketSize) {
                ensureCapacity(buckets, bucketIndex);
            }

            buckets[bucketIndex][index] = i;
            // 设置下标
            indexArr[bucketIndex] = index + 1;
        }

        // 4. 对每个桶排序
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (indexArr[i] == 0) continue;
            // 使用快排排序 去掉bucket里为0的数据
            buckets[i] = quickSort.sort(removeZero(buckets[i], indexArr[i]));
            // 将排序好的桶依次放进数组中
            for (int j = 0; j < buckets[i].length; j++) {
                array[k++] = buckets[i][j];
            }
        }


        return array;
    }

    private int[] removeZero(int[] bucket, int i) {
        // 下标从0开始
        int[] newArr = new int[i];

        System.arraycopy(bucket, 0, newArr, 0, i);
        return newArr;
    }

    private void ensureCapacity(int[][] buckets, int bucketIndex) {
        int originalSize = buckets[bucketIndex].length;
        int step = originalSize >> 1;
        int[] newBucket = new int[originalSize + step];
        System.arraycopy(buckets[bucketIndex], 0, newBucket, 0, originalSize);
        buckets[bucketIndex] = newBucket;
    }

    public static void main(String[] args) {
        int length = 100;

        int[] array = new int[length];


        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            // 随机生成 [1, 1000000] 的数据
            array[i] = rand.nextInt(length) + 1;
        }


        BucketSort bucketSort = new BucketSort(10);
        // 100 数据，10 个桶
        int[] sort = bucketSort.sort(array);
        System.out.println(Arrays.toString(sort));
    }

}
