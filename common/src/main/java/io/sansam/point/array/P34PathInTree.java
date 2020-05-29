package io.sansam.point.array;

/**
 * <p>
 * P34PathInTree
 * </p>
 *
 * @author houcb
 * @since 2020/5/29 10:41 上午
 */

import java.util.ArrayList;

/**
 * 题目
 * 　　输入一棵二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * <p>
 * 思路
 * 　　1.假设找到了其中一条路径，达到叶结点后，由于没有指向父结点的指针，所以必须提前创建一个链表存储前面经过的结点。
 * <p>
 * 　　2.由于是从根结点出发，所以要想到使用前序遍历
 * <p>
 * 　　3.利用链表存储结点，在该结点完成左右子树的路径搜索后（即递归函数结束，返回到其父结点之前），要删除该结点，从而记录别的路径。
 * <p>
 * 　　具体实现：通过前序遍历，从根结点出发，每次在链表中存储遍历到的结点，若到达叶子结点，则根据所有结点的和是否等于输入的整数，判断是否打印输出。在当前结点访问结束后，递归函数将会返回到它的父结点，所以在函数退出之前，要删除链表中的当前结点，以确保返回父结点时，储存的路径刚好是从根结点到父结点。
 * <p>
 * 　　改进：书中的代码是根据所有结点的和是否等于输入的整数，判断是否打印输出。其实没有这个必要，只需要在每次遍历到一个结点时，令目标整数等于自己减去当前结点的值，若到达根结点时，最终的目标整数等于0就可以打印输出。（描述得不是很清楚，就是相当于每个结点的目标整数不同，详见代码）
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（一条或者多条对应路径；无对应路径；结点值为正负零；）
 * <p>
 * 　　2.特殊测试（根结点为null）
 * <p>
 * 收获
 * 　　1.二叉树的许多题目与遍历（包括层序遍历）有关，要深刻理解；根据根结点的位置判断使用哪一种遍历。
 * <p>
 * 　　2.二叉树遍历过程没有父结点指针，要保存路径的话，必须要创建容器存储之前的结点。
 * <p>
 * 　　3.熟悉这道题中在每次递归函数结束前删除当前结点的操作，这可以确保返回到父结点时路径刚好是从根结点到父结点。
 * <p>
 * 　　4.target-=node.val这句代码非常好，多多体会。
 * <p>
 * 　　5.牛客网的那部分代码：在链表中存储一个对象时，如果该对象是不断变化的，则应该创建一个新的对象复制该对象的内容（而不是指向同一个对象），将这个新的对象存储到链表中。。如果直接存储该对象的话，链表中的对象也会不断变化。基本数据类型和String则没有这种问题。说到底其实是存储的是地址还是值的问题。这篇文章讨论了一下。
 */
public class P34PathInTree {

    private static ArrayList<ArrayList<Integer>> pathList = new ArrayList<>();
    private static ArrayList<Integer> nodeList = new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        if (root == null) return pathList;

        return search(root, target);

    }

    private static ArrayList<ArrayList<Integer>> search(TreeNode node, int target) {
        if (node == null) return pathList;
        nodeList.add(node.val);
        target -= node.val;

        // 叶子节点
        if (target == 0 && node.left == null && node.right == null) {
            //长度大的nodeList插入到pathList的前面
            int i = 0;
            while (i < pathList.size() && nodeList.size() < pathList.get(i).size()) {
                i++;
            }
            pathList.add(i, nodeList);
        }
        // 非叶子节点
        else {
            search(node.left, target);
            search(node.right, target);
        }
        // 迭代 返回上一级
        nodeList.remove(nodeList.size() - 1);

        return pathList;
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
