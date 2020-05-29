package io.sansam.point.array;

/**
 * <p>
 * P37SerializeBinaryTrees
 * </p>
 *
 * @author houcb
 * @since 2020/5/29 4:03 下午
 */

/**
 * 题目
 * 　　请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 回到顶部
 * 思路
 * 　　一般情况下，需要采用前/后序遍历和中序遍历才能确定一个二叉树，但是其实可以只采用前序遍历（从根结点开始），将空结点(null)输出为一个特殊符号（如“$”），就可以确定一个二叉树了。
 * <p>
 * 　　将二叉树序列化为字符串，就是前序遍历的过程，遇见空结点时，序列化为“$”，每个结点间使用逗号分隔开。
 * <p>
 * 　　将字符串反序列化为二叉树，也使用前序遍历，遇见一个新数字(或者$)就建立一个新结点，不过需要注意的是，数字可能不只是个位数字，因此创建了一个全局Int变量index（在字符串上的移动的指针），以便于截取字符串中当前的结点值。（详见代码）
 * <p>
 * 测试算例
 * <p>
 * 　　1.功能测试（一个结点；左右斜树；完全二叉树；普通二叉树）
 * <p>
 * 　　2.特殊测试（根结点为null）
 * <p>
 * 收获
 * 　　1.记住这种序列化的方式，用于表示二叉树时非常方便。
 * <p>
 * 　　2.字符串中有分割符号时，可以对字符串采用split()方法，变为字符串数组，但是自己觉得数组的保存会消耗一定的空间，因此自己定义了全局变量index，通过substring()方法来截取每一部分的字符串。
 * <p>
 * 　　3.字符串的比较以后尽量用equal来比较。在对某字符串采用substring()方法得到的字符串用==判断会返回false。substring的==与equal()使用
 * <p>
 * 　　4.String 转int 类型采用 int i = Integer.parseInt( s ); 不能用Integer.valueOf(s)，这返回的是Integer对象。
 * <p>
 * 　　5.index++的位置一定不能放错
 */
public class P37SerializeBinaryTrees {

    /**
     * 采用字符串下标的方式
     * 若当前字符不是, index++
     * <p>
     * index字符不是$ 则是数字 需要组建节点 index++ 指向下个字符
     * 下个字符应该是左子节点，如果左子节点为空，则处理右子节点，最后弹出此节点
     */
    int index = 0;
    /**
     * 采用数组方式处理
     * start++
     * 当前字符不是$ 则创建节点
     * 继续处理下个字符 为左子节点 如果左子节点为空 则处理右子节点 最后弹出此节点
     *
     * @param str
     * @return
     */
    int start = -1;

    /**
     * 序列化采用前序遍历
     * 需要将空节点用特殊字符$表示，叶子节点的左右子节点都为$
     *
     * @param node
     * @return
     */
    String serialize(TreeNode node) {
        if (node == null) return "$,";
        StringBuffer buffer = new StringBuffer();
        buffer.append(node.val + ",");
        buffer.append(serialize(node.left));
        buffer.append(serialize(node.right));
        return buffer.toString();
    }

    TreeNode deserialize(String str) {
        if (str == null || str.length() == 0) return null;
        TreeNode node = null;
        int start = index;
        while (str.charAt(start) != ',') {
            index++;
            if (!str.substring(start, index).equals("$")) {
                node = new TreeNode(Integer.parseInt(str.substring(start, index)));
                index++;
                node.left = deserialize2(str);
                node.right = deserialize(str);
            } else {
                index++;
            }
        }
        return node;
    }

    TreeNode deserialize2(String str) {
        if (str == null || str.length() == 0) return null;
        String[] split = str.split(",");

        return deserialize2ByArr(split);
    }

    private TreeNode deserialize2ByArr(String[] split) {
        start++;
        if (start < split.length && !split[start].equals("$")) {
            TreeNode node = new TreeNode(Integer.parseInt(split[start]));
            node.left = deserialize2ByArr(split);
            node.right = deserialize2ByArr(split);
            return node;
        }
        return null;
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
