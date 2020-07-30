package io.sansam.point.back;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SubSet
 * </p>
 *
 * @author houcb
 * @since 2020-07-30 16:33
 */
public class SubSetByMath {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        new SubSetByMath().subSet(arr).forEach(System.out::println);

    }

    public List<Integer> subSet(int[] set) {
        List<Integer> res = new ArrayList<>();
        if (set == null || set.length == 0) {
            res.add(-1);
            return res;
        }

        backtrack(set, res, set.length - 1);
        return res;
    }

    private void backtrack(int[] set, List<Integer> res, int index) {
        if (index <= 0) {
            res.add(-1);
            res.add(set[0]);
            return;
        }
        int last = set[index];

        backtrack(set, res, index - 1);

        int size = res.size();
        for (int i = 0; i < size; i++) {
            if (res.get(i) == -1) {
                res.add(last);
                continue;
            }
            res.add(res.get(i) * 10 + last);
        }

    }
}
