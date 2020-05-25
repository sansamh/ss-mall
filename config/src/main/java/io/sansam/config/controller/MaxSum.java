package io.sansam.config.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * MaxSum
 * </p>
 *
 * @author houcb
 * @since 2020-04-27 11:43
 */
public class MaxSum {

    public static void max(int[] a) {
        int sum = 0, b = 0, n = 6;
        for (int i = 1; i <= n; i++) {
            if (b < 0) b = a[i];
            else b = b + a[i];
            if (b > sum)
                sum = b;
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {
//        int[] a={0,-2,11,-4,13,-5,-2};
//        max(a);
//        int [] nums = {-2,0,1,1,2};
//        final List<List<Integer>> lists = threeSum(nums);
//        lists.forEach(x -> x.forEach(i -> System.out.println(i)));
//        System.out.println("----------");
//        threeSum2(nums).forEach(x -> x.forEach(i -> System.out.println(i)));

//        System.out.println(3>>1);

        int[] a = {6, 5, 5};
        System.out.println(majorityElement(a));
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        int len = nums.length;
        if (len < 3) {
            return res;
        }
        List<Integer> list;
        if (len == 3) {
            if (nums[0] + nums[1] + nums[2] == 0) {
                list = new LinkedList<>();
                list.add(nums[0]);
                list.add(nums[1]);
                list.add(nums[2]);
                res.add(list);
            }
            return res;
        }
        int l, r, n;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            l = i + 1;
            r = len - 1;
            n = 0 - nums[i];
            while (l < r) {
                if (nums[l] + nums[r] == n) {
                    list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (nums[l] + nums[r] < n) {
                    l++;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                } else {
                    r--;
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                }
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> listList = new ArrayList<>();
        Arrays.sort(nums);  //排序是前提
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    listList.add(list);
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return listList;
    }


    public static int majorityElement(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        int res = -1, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                res = nums[i];
                count++;
            } else {
                count = res == nums[i] ? ++count : --count;
            }
        }
        return res;
    }


}
