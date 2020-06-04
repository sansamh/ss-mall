package io.sansam.point.array;

/**
 * <p>
 * P52FirstCommonNodesInLists
 * </p>
 *
 * @author houcb
 * @since 2020-06-04 17:48
 */

import java.util.Stack;

/**
 * 题目
 * 　　输入两个链表，找出它们的第一个公共结点。
 * <p>
 * 思路
 * 　　蛮力法：遍历第一个链表的结点，每到一个结点，就在第二个链表上遍历每个结点，判断是否相等。时间复杂度为O(m*n)，效率低；
 * <p>
 * 　　使用栈：由于公共结点出现在尾部，所以用两个栈分别放入两个链表中的结点，从尾结点开始出栈比较。时间复杂度O(m+n)，空间复杂度O(m+n)。
 * <p>
 * 　　利用长度关系：计算两个链表的长度之差，长链表先走相差的步数，之后长短链表同时遍历，找到的第一个相同的结点就是第一个公共结点。
 * <p>
 * 　　利用两个指针：一个指针顺序遍历list1和list2，另一个指针顺序遍历list2和list1，（这样两指针能够保证最终同时走到尾结点），两个指针找到的第一个相同结点就是第一个公共结点。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（有/无公共结点；公共结点分别在链表的中间，头结点和尾结点）
 * <p>
 * 　　2.特殊测试（头结点为null）
 */
public class P52FirstCommonNodesInLists {

    //方法1：利用stack
    public ListNode findFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while (pHead1 != null) {
            stack1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            stack2.push(pHead2);
            pHead2 = pHead2.next;
        }

        ListNode listNode1, listnode2, pre = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            listNode1 = stack1.pop();
            listnode2 = stack2.pop();
            if (listNode1 == listnode2) {
                pre = listNode1;
            } else {
                break;
            }
        }
        return pre;
    }

    //方法2：利用长度
    public ListNode findFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        int l1 = getLength(pHead1);
        int l2 = getLength(pHead2);

        int diff = l1 > l2 ? l1 - l2 : l2 - l1;
        ListNode longNode = l1 > l2 ? pHead1 : pHead2;
        ListNode shortNode = l1 > l2 ? pHead2 : pHead1;

        for (int i = 0; i < diff; i++) {
            longNode = longNode.next;
        }

        while (longNode != null && longNode != shortNode) {
            longNode = longNode.next;
            shortNode = shortNode.next;
        }
        return longNode;
    }

    private int getLength(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    //方法3：两个指针，p1顺序遍历list1和list2；p2顺序遍历list2和list1；最终一定会相遇
    public ListNode findFirstCommonNode3(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while (p1 != p2) {
            p1 = p1 == null ? pHead2 : p1.next;
            p2 = p2 == null ? pHead1 : p2.next;
        }
        return p1;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
