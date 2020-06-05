package io.sansam.point.array;

/**
 * <p>
 * P54TreeDepth
 * </p>
 *
 * @author houcb
 * @since 2020-06-05 16:21
 */

/**
 * 题目
 * 　　输入一棵二叉树的根结点，求该树的深度。从根结点到叶结点依次经过的/结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 思路
 * 　　简洁理解：
 * <p>
 * 　　树的深度=max(左子树深度，右子树深度)+1，采用递归实现。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（左斜树、右斜树、普通树）
 * <p>
 * 　　2.边界值测试（一个结点）
 * <p>
 * 　　3.特殊测试（null）
 */
public class P55TreeDepth {

    public static int treeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
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
