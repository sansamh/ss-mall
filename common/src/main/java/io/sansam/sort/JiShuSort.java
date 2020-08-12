package io.sansam.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * JiShuSort 基数排序
 * </p>
 *
 * @author houcb
 * @since 2020-08-12 17:56
 */
public class JiShuSort implements Sort {
    public static void main(String[] args) {
        int[] arr = {3, 14, 56, 27, 98, 65};

        System.out.println(Arrays.toString(new JiShuSort().sort(arr)));
    }

    @Override
    public int[] sort(int[] array) {
        if (array.length <= 1) return array;

        List<LinkedList<Integer>> list = new ArrayList<>(10);

        fillList(list);

        int index = 0;

        boolean flag = sort(array, list, index);

        int start = 0;
        for (LinkedList<Integer> linkedList : list) {
            if (linkedList.peekFirst() != null) {
                array[start] = linkedList.getFirst();
                start++;
            }

        }

        list.clear();
        fillList(list);

        if (flag) {
            sort(array, list, index++);
        }
        return array;
    }

    private boolean sort(int[] array, List<LinkedList<Integer>> list, int index) {
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            if (String.valueOf(array[i]).length() < index) {
                list.get(0).add(array[i]);
            } else {
                flag = true;
                if (index == 0) {
                    list.get(array[i] % 10).add(array[i]);
                } else {
                    list.get(array[i] / (1 << index)).add(array[i]);
                }
            }
        }
        return flag;
    }

    private void fillList(List<LinkedList<Integer>> list) {
        for (int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }
    }
}
