package io.sansam.point.array;

/**
 * <p>
 * P27MirrorOfBinaryTree
 * </p>
 *
 * @author houcb
 * @since 2020/5/28 11:16 上午
 */

/**
 * 题目
 * 　　请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * <p>
 * 思路
 * 　　画图可以很清晰地得到思路：先前序遍历，对每个结点交换左右子结点。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（普通二叉树；左斜树；右斜树；一个结点）
 * <p>
 * 　　2.特殊测试（根结点为null；）
 * <p>
 * 收获
 * 　　画图使抽象问题形象化，面试时要在编程前先用画图、举例子等来解释思路。
 */
public class P27MirrorOfBinaryTree {

    public void mirror(TreeNode root) {
        if (root == null) return;

        // 先交换节点的左右子节点 再递归交换左子节点和右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirror(root.left);
        mirror(root.right);

    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean findNode(TreeNode root, int target) {
        if (root == null) return false;

        if (root.val == target) return true;

        if (root.val > target) return findNode(root.left, target);

        return findNode(root.right, target);


    }
}
