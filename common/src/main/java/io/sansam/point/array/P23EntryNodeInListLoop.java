package io.sansam.point.array;

import io.sansam.util.ListNode;

/**
 * <p>
 * P21EntryNodeInListLoop
 * </p>
 *
 * @author houcb
 * @since 2020/5/27 4:21 下午
 */

/**
 * 题目
 * 　　一个链表中包含环，如何找出环的入口结点？例如，在图3.8的链表中，环的入口结点是结点3。
 * <p>
 * 思路
 * 　　1.确定链表是否有环：通过两个不同速度的指针确定，当两个指针指向同一个结点时，该结点为环中的一个结点。
 * <p>
 * 　　2.确定环中结点的数目n：指针走一圈，边走边计数
 * <p>
 * 　　3.找到环的入口：从头结点开始，通过两个相差为n的指针来得到（即寻找链表中倒数第n个结点）
 * <p>
 * 　　更简单的思路：【LeetCode】142. Linked List Cycle II
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（链表包含与不包含环；链表有多个或一个结点）
 * <p>
 * 　　2.特殊测试（头结点为null）
 * <p>
 * 收获
 * 　　1.通过两个不同速度的指针可以确定链表中是否有环
 * <p>
 * 　　2.相差n步的两个指针可以找到倒数第n个结点（链表中倒数第k个结点）
 * <p>
 * 　　3.复杂问题分解成为几个简单问题（本题分为三步：找出环中任一结点；得到环的个数；找到入口结点）
 */
public class P23EntryNodeInListLoop {

    private static ListNode meetingNode(ListNode head) {
        if (head == null) return null;
        ListNode pSlow = head;
        ListNode pFast = head;

        while (pFast != null && pSlow != null) {
            pSlow = pSlow.next;
            pFast = pFast.next;
            if (pFast != null) {
                pFast = pFast.next;
            }
            if (pSlow != null && pSlow == pFast) {
                return pFast;
            }
        }
        return null;
    }

    public static ListNode entryNodeOfLoop(ListNode head) {
        ListNode meetingNode = meetingNode(head);
        if (meetingNode == null) return null;
        // 圈内节点个数
        int loopNum = 0;
        while (meetingNode != null) {
            loopNum++;
            meetingNode = meetingNode.next;
        }
        // 再找倒数第loopNum个节点
        ListNode p1 = head;
        ListNode p2 = head;

        for (int i = 0; i < loopNum; i++) {
            p1 = p1.next;
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

}
