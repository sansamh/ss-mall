package io.sansam.point.array;

/**
 * <p>
 * P55BalancedBinaryTree
 * </p>
 *
 * @author houcb
 * @since 2020-06-05 16:31
 */

/**
 * 题目
 * 　　输入一棵二叉树的根结点，判断该树是不是平衡二叉树。如果某二叉树中任意结点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * <p>
 * 思路
 * 　　在(55-1) 二叉树的深度基础上修改：计算树的深度，树的深度=max(左子树深度，右子树深度)+1。
 * 在遍历过程中，判断左右子树深度相差是否超过1，如果不平衡，则令树的深度=-1，用来表示树不平衡。最终根据树的深度是否等于-1来确定是否为平衡树。
 * <p>
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（左斜树、右斜树、平衡或者不平衡树）
 * <p>
 * 　　3.特殊测试（一个结点，null）
 */
public class P55BalancedBinaryTree {

    /**
     * 判断左右子树的高度差
     *
     * @param node
     * @return
     */
    public static boolean isBalance1(TreeNode node) {
        if (node == null) return true;

        int left = getDepth(node.left);
        int right = getDepth(node.right);
        int diff = left - right;
        if (Math.abs(diff) > 1) {
            return false;
        }
        return isBalance1(node.left) && isBalance1(node.right);
    }

    private static int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }

    /**
     * 判断节点左右子树差是否超过1 如果超过则设置节点高度为-1 返回上一级
     *
     * @param node
     * @return
     */
    public static boolean isBalance2(TreeNode node) {
        return getDepth2(node) != -1;
    }

    private static int getDepth2(TreeNode node) {
        if (node == null) return 0;

        int left = getDepth2(node.left);
        if (left == -1) return -1;

        int right = getDepth2(node.right);
        if (right == -1) return -1;

        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
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
