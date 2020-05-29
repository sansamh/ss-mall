package io.sansam.point.array;

/**
 * <p>
 * P35CopyComplexList
 * </p>
 *
 * @author houcb
 * @since 2020/5/29 11:02 上午
 */

/**
 * 题目
 * 　　请实现函数ComplexListNode* Clone(ComplexListNode* pHead)，复制一个复杂链表。在复杂链表中，每个结点除了有一个m_pNext指针指向下一个点外，还有一个m_pSibling 指向链表中的任意结点或者nullptr。
 * <p>
 * 思路
 * 　　思路1：先复制结点，用next链接，最后根据原始结点的sibling指针确定该sibling结点距离头结点的位置，从而对复制结点设置sibling指针。但是该思路对于n个结点的链表，每个结点的sibling都需要O(n)个时间步才能找到，所以时间复杂度为O(n^2)
 * <p>
 * 　　思路2：复制原始结点N创建N’，用next链接。将<N,N'>的配对信息存放入一个哈希表中；在设置sibling时，通过哈希表，只需要用O(1)的时间即可找到复制结点的sibling。该方法的时间复杂度为O(n)，但空间复杂度为O(n)。
 * <p>
 * 　　思路3：复制原始结点N创建N’，将N'链接到N的后面；根据原始结点N的sibling可以快速设置N'结点的sibling，最后将这个长链表拆分成原始链表和复制链表（根据奇偶位置）
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（sibling指向自己；链表只有一个结点；sibling指向null或者指向结点）
 * <p>
 * 　　2.特殊测试（头结点为null）
 * <p>
 * 收获
 * 　　1.涉及链表结点操作，必须时刻注意对null的判断
 * <p>
 * 　　2.复制链表时，在原始结点后面直接插入复制结点，这种方法非常方便，有较高的时间效率，先记住，以后可能会遇到类似的应用
 * <p>
 * 　　3.查找时间复杂度为O(1)，可以考虑使用哈希表。哈希表的应用要掌握。
 */
public class P35CopyComplexList {

    /*
     * 主程序（包含三步）
     */
    public static ComplexListNode cloneList(ComplexListNode head) {
        if (head == null) return head;
        cloneNodes(head);           //1.复制结点
        connectSiblingNodes(head);  //2.设置sibling
        return reconnectNodes(head);//3.拆分长链表
    }

    /*
     * 第三步：将长链表拆分成原始链表和复制链表（根据奇偶位置）
     */
    private static ComplexListNode reconnectNodes(ComplexListNode head) {
        // 先根据奇偶位置把两个链表的头结点找出来 再循环处理
        ComplexListNode pHead = head, nHead = null, nNode = null;
        if (head != null) {
            pHead = head;
            nNode = nHead = head.next;

            pHead.next = nHead.next;
            pHead = pHead.next; // 提前将pHead指向第三个节点
        }

        while (pHead != null) {
            nNode.next = pHead.next;
            nNode = nNode.next;
            pHead.next = nHead.next;
            pHead = pHead.next;
        }
        return nHead;
    }

    /*
     * 第二步：根据原结点的sibling，设置复制结点的sibling
     */
    private static void connectSiblingNodes(ComplexListNode head) {
        ComplexListNode dummy = head;
        while (dummy != null) {
            if (dummy.sibling != null && dummy.next != null) {
                dummy.next.sibling = dummy.sibling.next;
                dummy = dummy.next.next;
            }
        }
    }

    /*
     * 第一步：复制每个结点，并插入到原始节点的后面
     */
    private static void cloneNodes(ComplexListNode head) {
        ComplexListNode dummy = head;
        ComplexListNode node, next;
        while (dummy != null) {
            next = dummy.next;
            node = new ComplexListNode(head.val);

            dummy.next = node;
            node.next = next;

            dummy = next;
        }
    }

    public static class ComplexListNode {
        int val;
        ComplexListNode next = null;
        ComplexListNode sibling = null;

        ComplexListNode(int label) {
            this.val = label;
        }
    }
}
