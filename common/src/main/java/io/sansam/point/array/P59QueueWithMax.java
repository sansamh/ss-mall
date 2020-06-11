package io.sansam.point.array;

/**
 * <p>
 * P59QueueWithMax
 * </p>
 *
 * @author houcb
 * @since 2020-06-09 16:20
 */

import java.util.ArrayDeque;

/**
 * 题目
 * 　　请定义一个队列并实现函数max得到队列里的最大值，要求函数max、push_back和pop_front的时间复杂度都是O(1)。
 * <p>
 * 思路
 * 　　与滑动窗口的最大值一题相似，利用一个双端队列来存储当前队列里的最大值以及之后可能的最大值。
 * <p>
 * 　　在定义题目要求功能的队列时，除了定义一个队列data存储数值，还需额外用一个队列maxmium存储可能的最大值；此外，还要定义一个数据结构，用于存放数据以及当前的index值，用于删除操作时确定是否删除maxmium中最大值。
 * <p>
 * 　　具体实现见代码，代码进行了一些测试，应该没有什么问题。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　尾部插入不同大小数字，删除头部数字。插入删除同时获取最大值
 */
public class P59QueueWithMax {

    private static ArrayDeque<Integer> data = new ArrayDeque<>();
    private static ArrayDeque<Integer> max = new ArrayDeque<>();

    public static void pushBack(Integer e) {
        data.addLast(e);
        // num = 1 如果max为空 需要插入当前原色
        int num = 1;
        while (!max.isEmpty() && max.getLast() < e) {
            max.removeLast();
            num++;
        }

        while (num != 0) {
            max.addLast(e);
            num--;
        }
    }

    public static Integer popFront() {
        if (data.isEmpty()) {
            return null;
        }
        max.removeFirst();
        return data.removeFirst();
    }

    public static Integer max() {
        if (max.isEmpty()) {
            return null;
        }
        return max.peekFirst();
    }

    public static void main(String[] args) {
        P59QueueWithMax.pushBack(3);
        System.out.println(P59QueueWithMax.max());

        P59QueueWithMax.pushBack(5);
        System.out.println(P59QueueWithMax.max());

        P59QueueWithMax.pushBack(6);
        System.out.println(P59QueueWithMax.max());

        P59QueueWithMax.pushBack(4);
        System.out.println(P59QueueWithMax.max());

        System.out.println(P59QueueWithMax.popFront());
        System.out.println(P59QueueWithMax.max());

        System.out.println(P59QueueWithMax.popFront());
        System.out.println(P59QueueWithMax.max());

        System.out.println(P59QueueWithMax.popFront());
        System.out.println(P59QueueWithMax.max());

        System.out.println(P59QueueWithMax.popFront());
        System.out.println(P59QueueWithMax.max());

        System.out.println(P59QueueWithMax.popFront());
        System.out.println(P59QueueWithMax.max());
    }
}
