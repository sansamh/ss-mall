package io.sansam.point.array;

/**
 * <p>
 * P41StreamMedian
 * </p>
 *
 * @author houcb
 * @since 2020-06-02 14:53
 */

import java.util.PriorityQueue;

/**
 * 题目
 * 　　如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * <p>
 * 思路
 * 　　所谓数据流，就是不会一次性读入所有数据，只能一个一个读取，每一步都要求能计算中位数。
 * <p>
 * 　　将读入的数据分为两部分，一部分数字小，另一部分大。小的一部分采用大顶堆存放，大的一部分采用小顶堆存放。当总个数为偶数时，使两个堆的数目相同，则中位数=大顶堆的最大数字与小顶堆的最小数字的平均值；而总个数为奇数时，使小顶堆的个数比大顶堆多一，则中位数=小顶堆的最小数字。
 * <p>
 * 　　因此，插入的步骤如下：
 * <p>
 * 　　1.若已读取的个数为偶数（包括0）时，两个堆的数目已经相同，将新读取的数插入到小顶堆中，从而实现小顶堆的个数多一。但是，如果新读取的数字比大顶堆中最大的数字还小，就不能直接插入到小顶堆中了 ，此时必须将新数字插入到大顶堆中，而将大顶堆中的最大数字插入到小顶堆中，从而实现小顶堆的个数多一。
 * <p>
 * 　　2若已读取的个数为奇数时，小顶堆的个数多一，所以要将新读取数字插入到大顶堆中，此时方法与上面类似。
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（读入奇/偶数个数字）
 * <p>
 * 　　2.边界值测试（读入0个、1个、2个数字）
 * <p>
 * <p>
 * 收获
 * 　　1.最大最小堆可以用PriorityQueue实现，PriorityQueue默认是一个小顶堆，通过传入自定义的Comparator函数可以实现大顶堆：
 * <p>
 * PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11,new Comparator<Integer>(){ //大顶堆，容量11
 *
 * @Override public int compare(Integer i1,Integer i2){
 * return i2-i1; //降序排列
 * }
 * });
 * 　　PriorityQueue的常用方法有：poll(),offer(Object),size(),peek()等。
 * <p>
 * 　　2.平均值应该定义为double，且（a+b）/2.0 。
 * <p>
 * 　　3.往最大堆中插入数据时间复杂度是O(logn)，获取最大数的时间复杂度是O(1)。
 * <p>
 * 　　4.这道题关键在于分成两个平均分配的部分，奇偶时分别插入到最大最小堆中，利用最大最小堆性质的插入方法要掌握。
 */
public class P41StreamMedian {

    // 用两个指针来表达中位数 如果总数是奇数 两指针指向同一元素 否则指向中间两个元素
    // 以指针划分左右两部分 左边都是小于等于右边的 右边都是大于等于左边的 因此找到左边最大的和右边最小的 取平均数就是中位数

    // 小顶堆 使用基础类型包装类 默认是以升序排列 堆顶是最小元素，最小堆存放 右边部分 取出右边部分最小的值
    private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 大顶堆 自定义了比较器 最大堆存放 左边部分 取出左边部分最大的值
    private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

    /**
     * 插入方法
     *
     * @param e
     */
    public static void insert(Integer e) {
        if (e == null) return;

        // 当前两个容器的总共有偶数个元素 包括0
        if (((minHeap.size() + maxHeap.size()) & 1) == 0) {
            // 插入最小堆
            // 需要判断 最大堆的堆顶和当前值的大小 如果大顶堆元素更大 需要将插入元素放进大顶堆 然后取大顶堆堆顶元素 放进小顶堆
            if (!maxHeap.isEmpty() && maxHeap.peek() > e) {
                maxHeap.offer(e);
                e = maxHeap.poll();
            }
            minHeap.offer(e);
        }
        // 容器元素总数为奇数 因为小顶堆多一个元素 所以插入大顶堆
        else {
            // 判断小顶堆堆顶数据和当前元素 如果小顶堆元素更小 则将插入元素放进小顶堆 再取出小顶堆堆顶元素 插入大顶堆中
            if (!minHeap.isEmpty() && minHeap.peek() < e) {
                minHeap.offer(e);
                e = minHeap.poll();
            }
            maxHeap.offer(e);
        }
    }

    /**
     * 获取中位数的方法
     *
     * @return
     */
    public static double getMid() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            throw new RuntimeException("data is empty");
        }
        double res;
        // 偶数个元素 取最大堆和最小堆的堆顶 求平均值
        if (((minHeap.size() + maxHeap.size()) & 1) == 0) {
            res = (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        // 奇数个元素 最小堆多一个元素 取最小堆堆顶值
        else {
            res = minHeap.peek();
        }
        return res;
    }

}
