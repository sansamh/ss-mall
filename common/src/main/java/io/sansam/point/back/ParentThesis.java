package io.sansam.point.back;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ParentThesis
 * </p>
 *
 * @author houcb
 * @since 2020-07-30 11:30
 */
public class ParentThesis {

    public static void main(String[] args) {
        ParentThesis parentThesis = new ParentThesis();
        parentThesis.generateParenthesis(3).forEach(x -> System.out.println(x));
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n <= 0) {
            return list;
        }
        // 两个规律
        // 1、一个「合法」括号组合的左括号数量一定等于右括号数量，这个显而易见。

        // 2、对于一个「合法」的括号字符串组合p，必然对于任何0 <= i < len(p)都有：子串p[0..i]中左括号的数量都大于或等于右括号的数量。
        String track = "";
        backtrack(n, n, track, list);

        return list;
    }

    private void backtrack(int left, int right, String track, List<String> list) {
        // 子串中 左括号应该大于等于右括号 我们这里使用的是左右括号剩余的数量 如果剩余的大于左大于右 则不成立
        // 假设第一个括号为右括号 剩余左括号n 右括号n-1 应该第一个不能为右括号 所以不合法
        if (left > right) return;
        if (left < 0 || right < 0) return;
        if (left == 0 && right == 0) {
            list.add(track);
            return;
        }
        // 放一个左括号
        track += "(";
        backtrack(left - 1, right, track, list);
        track = track.substring(0, track.length() - 1);

        // 放一个右括号
        track += ")";
        backtrack(left, right - 1, track, list);
        track = track.substring(0, track.length() - 1);

    }
}
