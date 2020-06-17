package io.sansam.basic;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * <p>
 * PriorityBlockingQueueTest
 * </p>
 *
 * @author houcb
 * @since 2020-06-17 10:38
 */
public class PriorityBlockingQueueTest {

    public static void main(String[] args) {

        PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<>();
        // 入队 最小堆 最小元素在第一位 最终顺序 1 2 3 4
        queue.add(new User(4, "444"));
        System.out.println(queue);

        queue.add(new User(3, "333"));
        System.out.println(queue);

        queue.add(new User(2, "222"));
        System.out.println(queue);

        queue.add(new User(1, "111"));
        System.out.println(queue);

        System.out.println("---------------");

        // 出队 按最小堆poll 顺序 1 2 3 4
        System.out.println(queue.poll());
        System.out.println(queue);

        System.out.println(queue.poll());
        System.out.println(queue);

        System.out.println(queue.poll());
        System.out.println(queue);

        System.out.println(queue.poll());
        System.out.println(queue);

    }

}

class User implements Comparable<User> {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.id, o.id);
    }
}
