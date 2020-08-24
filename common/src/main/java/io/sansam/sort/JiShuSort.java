package io.sansam.sort;

import java.util.*;

/**
 * <p>
 * JiShuSort 基数排序 从个位数开始 将每个数字放进对应下标的容器里 扫描一遍后 将容器数字依次 先进后出的取出 再循环下一位数字
 * </p>
 *
 * @author houcb
 * @since 2020-08-12 17:56
 */
public class JiShuSort implements Sort {
    public static void main(String[] args) {
        int[] arr = {72, 11, 82, 32, 44, 13, 17, 95, 54, 28, 79, 56};

//        int[] arr = {3, 14, 56, 27, 98, 65};
        System.out.println(Arrays.toString(new JiShuSort().sort(arr)));
    }

    @Override
    public int[] sort(int[] array) {
        if (array.length <= 1) return array;

        List<LinkedList<Integer>> list = new ArrayList<>(10);

        fillList(list);


        int maxLen = findMaxLen(array);


        for (int i = 0; i < maxLen; i++) {
            sort(array, list, i);
        }


        return array;
    }

    private int findMaxLen(int[] array) {
        int max = 0;
        for (int i : array) {
            max = Math.max(max, String.valueOf(i).length());
        }
        return max;
    }

    private void sort(int[] array, List<LinkedList<Integer>> list, int index) {
        for (int i = 0; i < array.length; i++) {
            String s = String.valueOf(array[i]);
            s = new StringBuffer(s).reverse().toString();
            if (s.length() <= index) {
                list.get(0).add(array[i]);
            } else {
                int num = Integer.parseInt(s.substring(index, index + 1));
                list.get(num).add(array[i]);
            }
        }

        int start = 0;
        for (LinkedList<Integer> linkedList : list) {
            System.out.println(linkedList.size());
            Iterator<Integer> iterator = linkedList.iterator();

            while (iterator.hasNext()) {
                array[start++] = iterator.next();
            }

        }

        list.clear();
        fillList(list);

    }

    private void fillList(List<LinkedList<Integer>> list) {
        for (int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }
    }
}
