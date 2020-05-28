package io.sansam.point.array;

/**
 * <p>
 * P24ReverseList
 * </p>
 *
 * @author houcb
 * @since 2020/5/27 4:58 下午
 */

import io.sansam.util.ListNode;

/**
 * 题目
 * 　　定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
 * <p>
 * 回到顶部
 * 思路
 * 　　方法一：使用三个指针（pre,p,next）进行实现。令p指向pre，next则是用于防止链表断裂（很简单，详见代码）。
 * <p>
 * 　　方法二（递归）：找到最后一个结点作为返回值，递归函数中，找到最后的头结点后，开始进行每个结点next值的转换。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（链表有多个或一个结点）
 * <p>
 * 　　2.特殊测试（头结点为null）
 */
public class P24ReverseList {

    /**
     * 遍历
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        /**
         *
         * 假设链表1 2 3 4
         * 当head = 3, reverseList(head.next) = reverseList(4), 因为4.next == null, 所以newHead = 4
         * head.next.next = head ---> 4.next = 3
         * head.next = null ---> 3.next = null
         * 此时 4 -> 3
         *
         * 返回上一层 return newHead ---> return 4
         * head = 2,  newHead = 4
         * 2.next.next = 2 ---> 3.next = 2
         * 2.next = null
         *
         *4 -> 3 -> 2
         *
         * 返回上一层 return newHead ---> return 4
         * head = 1, newHead = 4
         * 1.next.next = 1 ---> 2.next = 1
         * 1.next = null
         * 4 -> 3 -> 2 -> 1
         */


        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
