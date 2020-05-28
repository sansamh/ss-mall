package io.sansam.point.array;

/**
 * <p>
 * P26SubstructureInTree
 * </p>
 *
 * @author houcb
 * @since 2020/5/28 11:02 上午
 */

/**
 * 题目
 * 　　输入两棵二叉树A和B，判断B是不是A的子结构。
 * <p>
 * 思路
 * 　　1）先对A树进行遍历，找到与B树的根结点值相同的结点R；
 * <p>
 * 　　2）判断A树中以R为根结点的子树是否包含B树一样的结构。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（A、B为普通二叉树；B是或者不是A树的子结构）
 * <p>
 * 　　2.特殊测试（任意一个或者两个树的根结点为null；左斜树；右斜树）
 * <p>
 * 收获
 * 　　1.本题相当于对二叉树遍历的拓展，操作过程中，注意null的处理。
 * <p>
 * 　　2.注意判断浮点数相等时有误差，不要直接用“==”判断。
 */
public class P26SubstructureInTree {

    /*
     * 主程序，对每个结点遍历判断
     */
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
//        boolean res = false;
//        if (root1.val == root2.val) {
//            res = doesTree1HasTree2(root1, root2);
//            if (!res) {
//                res = doesTree1HasTree2(root1.left, root2) || doesTree1HasTree2(root1.right, root2);
//            }
//        }
//        return res;
        return doesTree1HasTree2(root1, root2)
                || doesTree1HasTree2(root1.left, root2)
                || doesTree1HasTree2(root1.right, root2);
    }

    private boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root1 == null) return false;
        if (root2 == null) return true;

        return equal(root1.val, root2.val)
                && doesTree1HasTree2(root1.left, root2.left)
                && doesTree1HasTree2(root1.right, root2.right);
    }

    private boolean equal(double num1, double num2) {
        if (num1 - num2 < 0.0000001 && num1 - num2 > -0.0000001)
            return true;
        return false;
    }

    public class TreeNode {
        double val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}