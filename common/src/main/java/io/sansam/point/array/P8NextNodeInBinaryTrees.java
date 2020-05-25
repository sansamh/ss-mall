package io.sansam.point.array;

import io.sansam.util.TreeLinkNode;

/**
 * <p>
 * P8NextNodeInBinaryTrees
 * </p>
 *
 * @author houcb
 * @since 2020/5/20 4:29 下午
 */
public class P8NextNodeInBinaryTrees {


    /**
     * 1 如果节点有右节点 则下一个元素是右子树中最左的元素
     * 2 如果没有右节点
     * 2.1 如果节点是父节点的左子节点 则下一个元素是父节点
     * 2.2 如果节点是父节点的右子节点 则需要找到父节点是其父节点的左子节点的元素
     *
     * @param node
     * @return
     */
    public static TreeLinkNode getNext(TreeLinkNode node) {
        if (node == null) return node;
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        while (node.parent == null) {
            if (node == node.parent.left) {
                return node.parent;
            }
            node = node.parent;


        }

        return null;
    }
}
