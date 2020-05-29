package io.sansam.point.array;

/**
 * <p>
 * P36ConvertBinarySearchTree
 * </p>
 *
 * @author houcb
 * @since 2020/5/29 2:39 下午
 */

/**
 * 题目
 * 　　输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * <p>
 * 思路
 * 　　二叉搜索树、排序链表，想到使用中序遍历。
 * <p>
 * 　　要实现双向链表，必须知道当前结点的前一个结点。根据中序遍历可以知道，当遍历到根结点的时候，左子树已经转化成了一个排序的链表了，根结点的前一结点就是该链表的最后一个结点（这个结点必须记录下来，将遍历函数的返回值设置为该结点即可），链接根结点和前一个结点，此时链表最后一个结点就是根结点了。再处理右子树，遍历右子树，将右子树的最小结点与根结点链接起来即可。左右子树的转化采用递归即可。
 * <p>
 * 　　大概思想再理一下：首先想一下中序遍历的大概代码结构（先处理左子树，再处理根结点，之后处理右子树），假设左子树处理完了，就要处理根结点，而根结点必须知道左子树的最大结点，所以要用函数返回值记录下来；之后处理右子树，右子树的最小结点（也用中序遍历得到）要和根结点链接。
 * <p>
 * 　　注意搞清楚修改后的中序遍历函数的意义（见代码注释）
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（一个结点；左右斜树；完全二叉树；普通二叉树）
 * <p>
 * 　　2.特殊测试（根结点为null）
 * <p>
 * 收获
 * 　　题目较复杂时，不要慌。这道题和中序遍历有关，把树分为三部分：根结点、左子树和右子树，思考在中序遍历中根结点应该如何处理，这是关键——要将左子树的最大结点、根结点、右子树的最小结点链接起来。左右子树的处理是相同的，因此采用递归。
 */
public class P36ConvertBinarySearchTree {

    public static TreeNode convert(TreeNode head) {
        if (head == null)
            return head;
        TreeNode lastNodeInList = null;
        lastNodeInList = convertHelper(head, lastNodeInList);
        TreeNode firstNodeInList = lastNodeInList;
        while (firstNodeInList.left != null) {
            firstNodeInList = firstNodeInList.left;
        }
        return firstNodeInList;
    }

    //将以node为根结点的树转化为排序链表，链表头部与lastNode链接，并返回最后一个结点
    private static TreeNode convertHelper(TreeNode node, TreeNode lastNode) {
        //处理左子树，获得最大结点
        if (node.left != null)
            lastNode = convertHelper(node.left, lastNode);
        //链接最大结点和根结点
        node.left = lastNode;
        if (lastNode != null)
            lastNode.right = node;
        //处理右子树
        lastNode = node;
        if (node.right != null)
            lastNode = convertHelper(node.right, lastNode);
        return lastNode;
    }

    public static void main(String[] args) {

// TODO Auto-generated method stub

        TreeNode root = new TreeNode(10);

        TreeNode six = new TreeNode(6);

        TreeNode four = new TreeNode(4);

        TreeNode eight = new TreeNode(8);

        TreeNode fourteen = new TreeNode(14);

        TreeNode twelve = new TreeNode(12);

        TreeNode sixteen = new TreeNode(16);

        root.left = six;

        root.right = fourteen;

        six.left = four;

        six.right = eight;

        four.left = null;

        four.right = null;

        eight.left = null;

        eight.right = null;

        fourteen.left = twelve;

        fourteen.right = sixteen;

        twelve.left = null;

        twelve.right = null;

        sixteen.right = null;

        sixteen.left = null;

        TreeNode result = convert(root);

        while (result != null) {

            System.out.println(result.val);

            result = result.right;

        }
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
