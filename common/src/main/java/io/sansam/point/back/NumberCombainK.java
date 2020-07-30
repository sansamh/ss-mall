package io.sansam.point.back;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * NumberCombainK
 * </p>
 *
 * @author houcb
 * @since 2020-07-30 17:40
 */
public class NumberCombainK {

    static List<List<Integer>> result = new LinkedList<>();

    public static List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0) return result;

        LinkedList<Integer> list = new LinkedList<>();

        backtrack(n, k, 1, list);

        return result;
    }

    private static void backtrack(int n, int k, int start, LinkedList<Integer> list) {
        if (list.size() == k) {
            result.add(new LinkedList<>(list));
            return;
        }

        for (int i = start; i <= n; i++) {
            list.add(i);
            backtrack(n, k, i + 1, list);
            list.removeLast();
        }

    }

    public static void main(String[] args) {
        combine(4, 2).forEach(System.out::println);
    }


}
