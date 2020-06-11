package io.sansam.point.array;

/**
 * <p>
 * P54KthNodeInBST
 * </p>
 *
 * @author houcb
 * @since 2020-06-05 14:21
 */

/**
 * 题目
 * 　　给定一棵二叉搜索树，请找出其中的第k小的结点。
 * <p>
 * 思路
 * 　　设置全局变量index=0，对BST进行中序遍历，每遍历一个结点，index+1，当index=k时，该结点即为所求结点。
 * <p>
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（左斜树、右斜树、普通树）
 * <p>
 * 　　2.边界值测试（k=1,k=结点数目）
 * <p>
 * 　　3.特殊测试（null，k<=0，k>结点数目）
 */
public class P54KthNodeInBST {

    private static int index = 0;

    public static TreeNode KthNode(TreeNode pRoot, int k) {
        TreeNode pNode = null;
        if (pRoot == null || k <= 0)
            return pNode;
        pNode = getKthNode(pRoot, k);
        return pNode;
    }

    private static TreeNode getKthNode(TreeNode pRoot, int k) {
        TreeNode kthNode = null;

        if (pRoot.left != null)
            kthNode = getKthNode(pRoot.left, k);

        if (kthNode == null) {
            index++;
            if (k == index)
                kthNode = pRoot;
        }

        if (kthNode == null && pRoot.right != null)
            kthNode = getKthNode(pRoot.right, k);

        return kthNode;
    }

    private static int count;

    private static TreeNode getKthNode2(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        TreeNode left = getKthNode2(root.left, k);
        if (left != null) {
            return left;
        }
        count++;
        if (count == k) {
            return root;
        }
        TreeNode right = getKthNode2(root.right, k);
        if (right != null) {
            return right;
        }
        return null;
    }


    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(2);
//        TreeNode t2 = new TreeNode(1);
//        TreeNode t3 = new TreeNode(3);
//        t1.left = t2;
//        t1.right = t3;
//
//        System.out.println(KthNode(t1, 1).val);


        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);

        t4.left = t2;
        t4.right = t6;

        t2.left = t1;
        t2.right = t3;

        t6.left = t5;
        t6.right = t7;

        System.out.println(getKthNode2(t4, 6).val);
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
