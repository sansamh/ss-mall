package io.sansam.point.array;

/**
 * <p>
 * P68LowestParent
 * </p>
 *
 * @author houcb
 * @since 2020-06-11 11:27
 */

/**
 * 题目
 * 　　输入两个树结点，求它们的最低公共祖先。
 * <p>
 * 思路
 * 　　该题首先要和面试官确定是否为二叉树，得到肯定答复后，还要确定是否为二叉搜索树，是否有父指针，或者仅仅是普通二叉树。
 * <p>
 * 　　1.树为二叉搜索树时，最低公共祖先结点的大小在两个树结点大小的中间。
 * <p>
 * 　　2.树为普通树时，使用遍历将子结点的信息往上传递。在左右子树中进行查找是否存在两个树结点，如果两个树结点分别在左右子树上，说明该根结点就是它们的最低公共祖先。
 * <p>
 * 3.树为普通树，且有指向父节点的指针时，遍历两个节点到root节点，得到两个链表，最低公共父节点就是链表的第一个共同节点
 * <p>
 * <p>
 * 测试用例
 * <p>
 * 　　1.功能测试（普通树，左斜树，右斜树）
 * <p>
 * 　　2.特殊测试（null）
 */
public class P68LowestParent {

    /**
     * 二叉搜索树
     * 因为bts排过序 左子树小于右子树
     * 判断两节点的值和root节点的大小关系 如果两个节点都大于root节点 则继续在右子树找
     * 如果两个节点都小于root节点 则在左子树找
     * 否则此节点就是最低公共父节点
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public static TreeNode getLowestCommonParentBST(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) return root;
        while (true) {
            if (root == null) {
                return root;
            }
            if (node1.val > root.val && node2.val > root.val) {
                root = root.right;
            } else if (node1.val < root.val && node2.val < root.val) {
                root = root.left;
            } else {
                return root;
            }
        }
    }

    /**
     * 普通二叉树
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    public static TreeNode getLowestCommonParent(TreeNode root, TreeNode node1, TreeNode node2) {
        // 已经到了叶子节点 或者 找到了要找的节点
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        TreeNode left = getLowestCommonParent(root.left, node1, node2);
        TreeNode right = getLowestCommonParent(root.right, node1, node2);

        if (left == null) {
            return right;
        } else {
            if (right == null) {
                return left;
            } else {
                return root;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);

        root.left = t1;
        root.right = t2;

        t1.left = t3;
        t1.right = t4;

        t3.left = t5;
        t3.right = t6;

        t4.left = t7;
        t4.right = t8;

        System.out.println(getLowestCommonParent(root, t5, t7).val);


    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }


}
