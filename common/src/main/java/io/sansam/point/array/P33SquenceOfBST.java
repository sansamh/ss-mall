package io.sansam.point.array;

/**
 * <p>
 * P33SquenceOfBST
 * </p>
 *
 * @author houcb
 * @since 2020/5/29 10:22 上午
 */

/**
 * 题目
 * 　　输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互不相同。
 * <p>
 * 思路
 * 　　二叉树后序遍历数组的最后一个数为根结点，剩余数字中，小于根结点的数字（即左子树部分）都排在前面，大于根结点的数字（即右子树部分）都排在后面。根据遍历数组的这个特性，可以编写出一个递归函数，用于实现题目所要求的判断功能。
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（左斜树；右斜树；能对应的二叉树；不能对应的二叉树序列）
 * <p>
 * 　　2.特殊测试（null；一个结点）
 */
public class P33SquenceOfBST {

    public static boolean verifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length <= 0) return false;
        return check(sequence, 0, sequence.length - 1);
    }

    private static boolean check(int[] sequence, int index, int end) {
        // 循环结束
        if (index >= end) {
            return true;
        }
        // 找到左子树和右子树的分界点
        int mid = index;
        int last = sequence[end];

        while (sequence[mid] < last) {
            mid++;
        }
        // index 到 mid - 1为左
        // mid 到 end-1 为右
        for (int i = mid; i <= end; i++) {
            if (sequence[mid] < last) {
                return false;
            }
        }
        // 如果index = 0， mid = -1 则只有右子树 没有左子树 index>=mid 返回true
        return check(sequence, index, mid - 1) && check(sequence, mid, end - 1);
    }

}
