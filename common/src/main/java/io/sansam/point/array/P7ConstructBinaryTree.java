package io.sansam.point.array;

import io.sansam.util.TreeNode;

/**
 * <p>
 * P7ConstructBinaryTree
 * </p>
 *
 * @author houcb
 * @since 2020/5/18 10:24 上午
 */
public class P7ConstructBinaryTree {


    // 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    // 例如输入前序遍历序列{1,2, 4, 7, 3, 5, 6, 8}和中序遍历序列{4, 7, 2, 1, 5, 3, 8, 6}，
    // 则重建出二叉树并输出它的头结点。
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length <= 0 || in.length <= 0 || pre.length != in.length) {
            throw new RuntimeException("数组不符合规范！");
        }
        return construct(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }

    private static TreeNode construct(int[] pre, int[] in, int ps, int pe, int is, int ie) {
        TreeNode root = new TreeNode(pre[ps]);
        if (ps == pe && is == ie) {
            if (pre[ps] != in[is]) {
                throw new RuntimeException("invalid input");
            } else {
                return root;
            }
        }
        int index = is;
        while (index <= ie && in[index] != root.val) {
            index++;
        }
        int left = index - is;
        if (left > 0) {
            root.left = construct(pre, in, ps + 1, ps + left, is, index - 1);
        }

        if (left < pe - ps) {
            root.right = construct(pre, in, ps + 1 + left, pe, index + 1, ie);
        }
        return root;
    }

    private static TreeNode construct1(int[] pre, int[] in, int pStart, int pEnd, int iStart, int iEnd) {
        TreeNode root = new TreeNode(pre[0]);
        if (pStart == pEnd && iStart == iEnd) {
            if (pre[pStart] != in[iStart]) {
                throw new RuntimeException("数组不合规范");
            }
            return root;
        }
        int index = iStart;
        while (index <= iEnd && in[index] != pre[0]) {
            index++;
        }
        if (index > iEnd) {
            throw new RuntimeException("数组不合规范");
        }
        int left = index - iStart;
        if (left > 0) {
            root.left = construct(pre, in, pStart + 1, pStart + left, iStart, index - 1);
        }
        if (left < pEnd - pStart) {
            root.right = construct(pre, in, pStart + left + 1, pEnd, index + 1, iEnd);
        }

        return root;
    }
}
