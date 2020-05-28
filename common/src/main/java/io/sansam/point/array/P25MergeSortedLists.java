package io.sansam.point.array;

import io.sansam.util.ListNode;

/**
 * <p>
 * P25MergeSortedLists
 * </p>
 *
 * @author houcb
 * @since 2020/5/28 10:40 上午
 */

/**
 * 题目
 * 　　输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的。
 * <p>
 * 思路
 * 　　递归实现：合并过程中，每次都是从两个链表中找出较小的一个来链接，因此可以采用递归来实现：当任意一个链表为null时，直接链接另一个链表即可；其余情况只需要在两个链表中找出较小的一个结点进行链接，该结点的next值继续通过递归函数来链接。
 * <p>
 * 　　非递归实现：非递归实现比较容易想到，直接进行分情况讨论即可，要稍微注意下后面代码中头结点的赋值过程。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（两个链表有多个或一个结点；结点值相同、不同）
 * <p>
 * 　　2.特殊测试（任意一个或者两个链表的头结点为null）
 */
public class P25MergeSortedLists {

    /**
     * 递归
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            list2.next = merge(list1, list2.next);
            return list2;
        }

    }

    public ListNode merge2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode newHead = new ListNode(-1);
        ListNode temp = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 == null) {
            temp.next = list2;
        }
        if (list2 == null) {
            temp.next = list1;
        }
        return newHead.next;
    }

}
