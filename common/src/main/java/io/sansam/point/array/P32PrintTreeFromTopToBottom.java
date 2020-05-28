package io.sansam.point.array;

/**
 * <p>
 * P32PrintTreeFromTopToBottom
 * </p>
 *
 * @author houcb
 * @since 2020/5/28 5:16 下午
 */

import java.util.LinkedList;
import java.util.Stack;

/**
 * 题目
 * 　　（一）从上往下打印出二叉树的每个结点，同一层的结点按照从左到右的顺序打印。
 * <p>
 * 　　（二）从上到下按层打印二叉树，同一层的结点按从左到右的顺序打印，每一层打印到一行。
 * <p>
 * 　　（三）请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * <p>
 * 思路
 * 　　（一）不分行从上往下打印二叉树：该题即为对二叉树的层序遍历，结点满足先进先出的原则，采用队列。每从队列中取出头部结点并打印，若其有子结点，把子结点放入队列尾部，直到所有结点打印完毕。
 */
public class P32PrintTreeFromTopToBottom {

    public static void main(String[] args) {
        P32PrintTreeFromTopToBottom demo = new P32PrintTreeFromTopToBottom();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        demo.test5();
    }

    /*
     *  不分行从上往下打印二叉树
     */
    // 题目：从上往下打印出二叉树的每个结点，同一层的结点按照从左到右的顺序打印。
    public void printTree1(TreeNode root) {
        if (root == null) return;

        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        TreeNode node;

        while (!list.isEmpty()) {
            node = list.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                list.add(node.left);
            }
            if (node.right != null) {
                list.add(node.right);
            }
        }
        System.out.println();
    }

    /*
     * 分行从上到下打印二叉树
     */
    // 题目：从上到下按层打印二叉树，同一层的结点按从左到右的顺序打印，每一层
    // 打印到一行。
    public void printTree2(TreeNode root) {
        if (root == null) return;

        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        TreeNode node;

        int curCount = 1;
        int nextCount;

        while (!list.isEmpty()) {
            nextCount = 0;
            for (int i = 0; i < curCount; i++) {
                node = list.poll();
                System.out.print(node.val + " ");
                if (node.left != null) {
                    list.add(node.left);
                    nextCount++;
                }
                if (node.right != null) {
                    list.add(node.right);
                    nextCount++;
                }
            }
            curCount = nextCount;
            System.out.println();
        }
    }


    /*
     * 之字形打印二叉树
     */
    // 题目：请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺
    // 序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，
    // 其他行以此类推。
    public void printTree3_1(TreeNode root) {
        if (root == null) return;
        TreeNode node;
        // 画个图找规律 \n换行
        // 假设顺序打印为 1 \n 2(left) 3(right) \n 4(left)5(right) 6(left)7(right) stack.push(1)
        // 当stack.pop得到1 这是第二层需要从右往左 即输出 3(right) 2(left) 因此stack1选push left 再push right
        // 当stack1.pop得到3 这是第三层需要从左往右 即输出  6(left) 7(right) 因此stack选push right 再push left

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack1 = new Stack<>();
        stack.push(root);
        while (!(stack.isEmpty() && stack1.isEmpty())) {
            while (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.val + " ");
                if (node.left != null) {
                    stack1.push(node.left);
                }
                if (node.right != null) {
                    stack1.push(node.right);
                }
            }

            System.out.println();


            while (!stack1.isEmpty()) {
                node = stack1.pop();
                System.out.print(node.val + " ");

                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            System.out.println();
        }


    }


    //============测试代码==============
    private void test(int testNum, TreeNode root) {
        System.out.println("=========test" + testNum + "===========");
        System.out.println("method1:");
        printTree1(root);
        System.out.println("method2:");
        printTree2(root);
        System.out.println("method3_1:");
        printTree3_1(root);
//        System.out.println("method3_2:");
//        printTree3_2(root);
    }

    //null
    private void test1() {
        TreeNode node = null;
        test(1, node);
    }

    //单个结点
    private void test2() {
        TreeNode node = new TreeNode(1);
        test(2, node);
    }

    //左斜
    private void test3() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node2.left = node3;
        test(3, node1);
    }

    //右斜
    private void test4() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.right = node2;
        node2.right = node3;
        test(4, node1);
    }

    //完全二叉树
    private void test5() {
        TreeNode[] nodes = new TreeNode[15];
        for (int i = 0; i < 15; i++) {
            nodes[i] = new TreeNode(i + 1);
        }
        for (int i = 0; i < 7; i++) {
            nodes[i].left = nodes[2 * i + 1];
            nodes[i].right = nodes[2 * i + 2];
        }
        test(5, nodes[0]);
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
