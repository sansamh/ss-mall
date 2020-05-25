package io.sansam.point.array;

import io.sansam.util.ListNode;
import io.sansam.util.ListNodeUtil;

import java.util.Stack;

/**
 * <p>
 * P6PrintListInReversedOrder
 * </p>
 *
 * @author houcb
 * @since 2020/5/15 3:35 下午
 */
public class P6PrintListInReversedOrder {

    public static void main(String[] args) {
        P6PrintListInReversedOrder demo = new P6PrintListInReversedOrder();
        System.out.println("test1:");
//        demo.test1();
        System.out.println("test2:");
        demo.test2();
        System.out.println("test3:");
        demo.test3();
    }

    /**
     * 栈的方式
     *
     * @return
     */
    public static ListNode printListReversingly_Iteratively(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        ListNode head = dummy;
        Stack<ListNode> nodeStack = new Stack<>();
        while (node != null) {
            nodeStack.push(node);
            node = node.next;
        }
        while (!nodeStack.isEmpty()) {
            head.next = nodeStack.pop();
            head = head.next;
            head.next = null;
        }
        return dummy.next;
    }

    //采用递归
    public ListNode printListReversingly_Recursively(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode pre = null, next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }


    // ==================================测试代码==================================

    /**
     * 链表为空
     */
    public void test1() {
        ListNode aListNode = null;
        System.out.println("采用栈：");
        ListNodeUtil.print(printListReversingly_Iteratively(aListNode));

        System.out.println("采用递归：");
        ListNodeUtil.print(printListReversingly_Recursively(aListNode));
    }

    /**
     * 多个结点链表
     */
    public void test2() {
        ListNode ListNode1 = new ListNode(1);
        ListNode ListNode2 = new ListNode(2);
        ListNode ListNode3 = new ListNode(3);
        ListNode ListNode4 = new ListNode(4);
        ListNode ListNode5 = new ListNode(5);
        ListNode1.next = ListNode2;
        ListNode2.next = ListNode3;
        ListNode3.next = ListNode4;
        ListNode4.next = ListNode5;
        System.out.println("采用栈：");
//        ListNodeUtil.print(printListReversingly_Iteratively(ListNode1));
        System.out.println("采用递归：");
        ListNodeUtil.print(printListReversingly_Recursively(ListNode1));
    }

    /**
     * 单个结点链表
     */
    public void test3() {
        ListNode ListNode1 = new ListNode(1);
        System.out.println("采用栈：");
        ListNodeUtil.print(printListReversingly_Iteratively(ListNode1));
        System.out.println("采用递归：");
        ListNodeUtil.print(printListReversingly_Recursively(ListNode1));
    }


}
