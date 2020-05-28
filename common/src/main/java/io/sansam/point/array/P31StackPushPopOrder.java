package io.sansam.point.array;

/**
 * <p>
 * P31StackPushPopOrder
 * </p>
 *
 * @author houcb
 * @since 2020/5/28 4:31 下午
 */

import java.util.Stack;

/**
 * 题目
 * 　　输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1、2、3、4、5是某栈的压栈序列，序列4、5、3、2、1是该压栈序列对应的一个弹出序列，但4、3、5、1、2就不可能是该压栈序列的弹出序列。
 * <p>
 * 思路
 * 　　建立一个栈，按照压栈序列依次进行入栈操作，按出栈序列的顺序依次弹出数字。在出栈时，若下一个要出栈的数字与栈顶数字相同则弹出。如果压栈序列中的所有数字都入栈后没有完全出栈成功则代表两个序列不匹配，返回false。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（两个数组长度不同；两个数组对应；两个数组不对应）
 * <p>
 * 　　2.特殊测试（数组为空；null；一个数字的数组）
 * <p>
 * 收获
 * 　　通过举例子，整理清楚逻辑顺序：依次先入栈，再判断是否出栈；出栈序列能实现栈的清空说明两个序列匹配。
 * <p>
 * 步骤
 * 1.申请一个辅助栈stack用来对pushA序列压入；并用一个指针idx初始化为0，指示pushB的第一个元素。
 * <p>
 * 2.遍历pushA中的所有元素，并将pushA中元素压入辅助栈stack中，
 * 每压入一个pushA中的元素，检查stack栈顶元素和idx指向pushB中的元素是否相同，
 * 如果相同，则需要弹出栈顶的元素，idx后移，直到栈顶元素与pushB元素不等。
 * <p>
 * 3.当遍历完pushA中所有元素之后，检查辅助栈是否为空，为空则说明pushB是合理的弹出序列，反之则不是
 * 例如：
 * pushA入栈序列：1, 2, 3, 4, 5
 * pushB出栈序列：4, 5, 3, 2, 1
 * 首先1入辅助栈，
 * 此时栈顶1≠4，继续入栈2
 * 此时栈顶2≠4，继续入栈3
 * 此时栈顶3≠4，继续入栈4
 * 此时栈顶4＝4，出栈4，弹出序列向后一位，此时为5，,辅助栈里面是1,2,3
 * 此时栈顶3≠5，继续入栈5
 * 此时栈顶5=5，出栈5,弹出序列向后一位，此时为3，,辅助栈里面是1,2,3
 */
public class P31StackPushPopOrder {

    public boolean isPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) return false;
        Stack<Integer> stack = new Stack<>();

        int pop = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[pop]) {
                stack.pop();
                pop++;
            }
        }
        return stack.isEmpty();
    }
}