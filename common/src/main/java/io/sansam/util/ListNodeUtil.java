package io.sansam.util;

/**
 * <p>
 * ListNodeUtil
 * </p>
 *
 * @author houcb
 * @since 2020/5/15 3:56 下午
 */
public class ListNodeUtil {

    public static void print(ListNode node) {
        StringBuffer buffer = new StringBuffer();
        while (node != null) {
            buffer.append("node[" + node.val + "]");
            node = node.next;
            if (node != null) {
                buffer.append(" -> ");
            }
        }
        System.out.println(buffer);
    }
}
