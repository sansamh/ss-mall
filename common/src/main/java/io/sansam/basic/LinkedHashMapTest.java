package io.sansam.basic;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * LinkedHashMapTest
 * </p>
 *
 * @author houcb
 * @since 2020-06-17 14:24
 */
public class LinkedHashMapTest {

    // 类初始化的时候赋值为0 不会报错
    private int ther;

    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 3;
            }
        };

        map.put("1", "111");
        map.put("2", "222");
        map.put("3", "333");

        map.forEach((k, v) ->
                System.out.println(k + " : " + v)
        );

        System.out.println("---------------------");

        // 模拟访问 每次将访问的节点放到双向链表的尾巴
        map.get("3");
        map.get("2");

        map.forEach((k, v) ->
                System.out.println(k + " : " + v)
        );

        System.out.println("---------------------");

        // 触发删除
        map.put("4", "444");

        map.forEach((k, v) ->
                System.out.println(k + " : " + v)
        );

        // 输出0
        System.out.println(new LinkedHashMapTest().ther);

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("1", "1");
        hashMap.put("2", "2");
        hashMap.put("3", "3");
        hashMap.put("4", "4");
        hashMap.put("5", "5");
        hashMap.put("6", "6");
        hashMap.put("7", "7");
        hashMap.put("8", "8");
        hashMap.put("9", "9");
        hashMap.put("10", "10");
        hashMap.put("11", "11");
        hashMap.put("12", "12");
        hashMap.put("13", "13");
    }

}
