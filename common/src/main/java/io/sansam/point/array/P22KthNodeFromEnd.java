package io.sansam.point.array;

import io.sansam.util.ListNode;

import java.util.Stack;

/**
 * <p>
 * P22KthNodeFromEnd
 * </p>
 *
 * @author houcb
 * @since 2020/5/27 3:45 下午
 */

/**
 * 题目
 * 　　输入一个链表，输出该链表中倒数第k个结点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾结点是倒数第1个结点。例如一个链表有6个结点，从头结点开始它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个结点是值为4的结点。
 * <p>
 * <p>
 * 思路
 * 　　第一直觉是先从头开始遍历，计算链表个数n，然后重新遍历，第n-k+1个结点即为所需要的结点。但是需要遍历2次。后面采用了栈进行实现该方法，空间复杂度比较大。书中的方法则是：设置两个指针，第一个指针先遍历k-1步；从第k步开始，第二个指针指向头结点，两个结点同时往后遍历，当第一个指针到达最后一个结点时，第二个指针指向的正好是倒数第k个结点。（其实感觉还是近似遍历了两次啊。。）
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（第k个结点分别在链表的中间，头结点和尾结点）
 * <p>
 * 　　2.特殊测试（头结点为null，k超出范围）
 * <p>
 * 收获
 * 　　1.注意代码的鲁棒性，开始思考前都需要注意特殊输入测试；
 * <p>
 * 　　2.一个指针遍历链表无法解决问题时，可以考虑使用两个指针来遍历链表：两个指针先后遍历（即该题目）、或者两个指针遍历速度不同（如：求链表中的中间结点，可以令一个指针一次走一步，另一个指针一次走两步来实现）
 */
public class P22KthNodeFromEnd {

    public static void main(String[] args) {
        P22KthNodeFromEnd demo = new P22KthNodeFromEnd();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
        demo.test6();
    }

    /**
     * 双指针
     * 假设总共8节点 从1开始数 找倒数第三个节点
     * 倒数第三个节点 顺数是8-3+1=第6个节点
     * 第一个指针要领先第二个指针两步 3-1 = 2
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail2(ListNode head, int k) {
        if (head == null || k <= 0) return null;
        ListNode p1 = head, p2 = head;
        for (int i = 1; i < k; i++) {
            if (p1.next != null) {
                p1 = p1.next;
            } else {
                // 没有k个元素
                return null;
            }
        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }


    //=====================测试代码=======================

    /**
     * 栈
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail1(ListNode head, int k) {
        if (head == null || k <= 0) return null;
        Stack<ListNode> stack = new Stack<>();
        int num = 0;
        while (head != null) {
            stack.push(head);
            head = head.next;
            num++;
        }
        if (k > num) {
            return null;
        }
        ListNode node = null;
        for (int i = 0; i < k; i++) {
            node = stack.pop();
        }
        return node;
    }

    /*
     * null
     */
    void test1() {
        ListNode head = null;
        ListNode result = FindKthToTail2(head, 1);
        if (result == null)
            System.out.println("test1 passed!");
        else
            System.out.println("test1 failed!");
    }

    /*
     * k超出范围
     */
    void test2() {
        ListNode head = new ListNode(2);
        ListNode result = FindKthToTail2(head, 2);
        if (result == null)
            System.out.println("test2 passed!");
        else
            System.out.println("test2 failed!");
    }

    /*
     * 单个结点
     */
    void test3() {
        ListNode head = new ListNode(1);
        ListNode result = FindKthToTail2(head, 1);
        if (result.val == 1)
            System.out.println("test3 passed!");
        else
            System.out.println("test3 failed!");
    }

    /*
     * 尾结点
     */
    void test4() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode result = FindKthToTail2(node1, 1);
        if (result.val == 4)
            System.out.println("test4 passed!");
        else
            System.out.println("test4 failed!");
    }

    /*
     * 中间结点
     */
    void test5() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode result = FindKthToTail2(node1, 2);
        if (result.val == 3)
            System.out.println("test5 passed!");
        else
            System.out.println("test5 failed!");
    }

    /*
     * 头结点
     */
    void test6() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode result = FindKthToTail2(node1, 4);
        if (result.val == 1)
            System.out.println("test6 passed!");
        else
            System.out.println("test6 failed!");
    }
}
