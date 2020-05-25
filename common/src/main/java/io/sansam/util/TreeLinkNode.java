package io.sansam.util;

/**
 * <p>
 * TreeLinkNode
 * </p>
 *
 * @author houcb
 * @since 2020/5/20 4:29 下午
 */
public class TreeLinkNode {
    public int val;
    public TreeLinkNode left = null;
    public TreeLinkNode right = null;
    public TreeLinkNode parent = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
