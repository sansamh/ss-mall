package io.sansam.point.array;

/**
 * <p>
 * P30StackWithMin
 * </p>
 *
 * @author houcb
 * @since 2020/5/28 3:59 下午
 */

import java.util.Stack;

/**
 * 题目
 * 　　定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。在该栈中，调用min、push及pop的时间复杂度都是O(1)。
 * <p>
 * 思路
 * 　　最初想法是定义一个成员变量min来存放最小元素，但是当最小元素弹出后，min就需要相应改变，所以必须把每次的最小值都存储下来。考虑采用一个辅助栈来存放最小值：
 * <p>
 * 　　　　栈  3，4，2，5，1
 * <p>
 * 　    辅助栈 3， 3，2，2，1
 * 　　（压入时，把每次的最小元素（之前最小元素与新入栈元素的较小值）保存起来放到辅助栈中）
 * <p>
 * 测试算例
 * <p>
 * 　　1.新压入数字更大
 * <p>
 * 　　2.新压入数字最小
 * <p>
 * 　　3.弹出数字最小
 * <p>
 * 　　4.弹出数字不是最小
 */
public class P30StackWithMin {

    static Stack<Integer> dataStack = new Stack<>();
    static Stack<Integer> minStack = new Stack<>();

    public static void push(Integer e) {
        dataStack.push(e);
        // 辅助栈保存 当前数据栈里最小的数据
        if (minStack.isEmpty() || minStack.peek() > e) {
            minStack.push(e);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public static Integer pop() {
        if (dataStack.isEmpty()) return null;
        minStack.pop();
        return dataStack.pop();
    }

    public static Integer min() {
        return minStack.pop();
    }
}
