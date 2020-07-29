package io.sansam.basic;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * <p>
 * FullSort
 * </p>
 *
 * @author houcb
 * @since 2020-06-23 11:22
 */
public class FullSort {

    private static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE;

    public static void main(String[] args) {


        printDefaultCapacityList();
        printEmptyCapacityList();

        System.out.println(howMany1(9));
        System.out.println(howMany2(9));
    }


    public static void printDefaultCapacityList() {
        ArrayList defaultCapacity = new ArrayList();
        System.out.println(
                "default 初始化长度：" + getCapacity(defaultCapacity));

        defaultCapacity.add(1);
        System.out.println(
                "default add 之后 长度：" + getCapacity(defaultCapacity));
    }

    public static void printEmptyCapacityList() {
        ArrayList emptyCapacity = new ArrayList(0);
        System.out.println(
                "empty 初始化长度：" + getCapacity(emptyCapacity));

        emptyCapacity.add(1);
        System.out.println(
                "empty add 之后 长度：" + getCapacity(emptyCapacity));
    }

    public static int getCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            // 获取 elementData 字段
            Field field = arrayListClass.getDeclaredField("elementData");
            // 开启访问权限
            field.setAccessible(true);
            // 把示例传入get，获取实例字段elementData的值
            Object[] objects = (Object[]) field.get(arrayList);
            //返回当前ArrayList实例的容量值
            return objects.length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int howMany1(int n) {
        int count = 0;
        int flag = 1;

        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
            System.out.println(Integer.toBinaryString(flag));
        }

        return count;
    }

    public static int howMany2(int n) {
        int count = 0;

        while (n != 0) {

            count++;
            n = n & (n - 1);
            System.out.println(Integer.toBinaryString(n));
        }

        return count;
    }
}
