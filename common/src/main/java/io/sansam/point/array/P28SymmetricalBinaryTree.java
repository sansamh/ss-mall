package io.sansam.point.array;

/**
 * <p>
 * P28SymmetricalBinaryTree
 * </p>
 *
 * @author houcb
 * @since 2020/5/28 2:27 下午
 */

/**
 * 题目
 * 　　请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * <p>
 * 思路
 * 　　还是画图分析，不用分析根结点，只需要分析左右子树。可以看出，左右子树刚好是呈镜像的两颗二叉树，
 * 所以：对左子树采用（父-左-右）的前序遍历，右子树采用（父-右-左）的前序遍历，遍历时判断两个结点位置的值是否相等即可。
 * （也可以这样理解：左树的左子树等于右树的右子树，左树的右子树等于右树的左子树，对应位置刚好相反，判断两子树相反位置上的值是否相等即可）
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（对称二叉树；结构不对称二叉树；结构对称但值不对称二叉树）
 * <p>
 * 　　2.特殊测试（根结点为null；单个结点；所有结点的值都相等的二叉树）
 * <p>
 * 收获
 * 　　画图使抽象问题形象化，本题还是相当于对数的遍历算法的理解。
 * <p>
 * 　　这道题主要突破点在于看出左右两个子树数值刚好呈镜像，相反位置对比即可。
 */
public class P28SymmetricalBinaryTree {

    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return isEqual(pRoot.left, pRoot.right);

    }

    private boolean isEqual(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return left.val == right.val
                && isEqual(left.left, right.right)
                && isEqual(left.right, right.left);
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
