package io.sansam.point.array;

import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p>
 * P9QueueWithTwoStacks
 * </p>
 *
 * @author houcb
 * @since 2020/5/21 2:29 下午
 */
public class P9QueueWithTwoStacks {

    public static void main(String[] args) {
        P9QueueWithTwoStacks demo = new P9QueueWithTwoStacks();
//        demo.test1();
//        demo.test2();

        demo.test3();


    }

    public void test1() {
        Queue queue = new Queue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.pop());
        queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }

    /**
     * 往空队列删除元素
     */
    public void test2() {
        Queue queue = new Queue();
        System.out.println(queue.pop());
    }

    public void test3() {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    // queue 先进先出
    class Queue {
        // 先进后出
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack2.isEmpty() && stack1.isEmpty()) return -1;
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

    class MyStack {
        // 任何时候两个队列总有一个是空的。
        // 添加元素总是向非空队列中 add 元素。
        // 取出元素的时候总是将元素除队尾最后一个元素外，导入另一空队列中，最后一个元素出队。


        java.util.Queue<Integer> queue1 = new LinkedBlockingDeque<>();
        java.util.Queue<Integer> queue2 = new LinkedBlockingDeque<>();

        public void push(int node) {
            if (queue2.isEmpty()) {
                queue1.add(node);
            }
            if (queue1.isEmpty()) {
                queue2.add(node);
            }
        }

        public int pop() {
            if (queue1.isEmpty() && queue2.isEmpty()) return -1;
            int target = -1;
            if (queue1.isEmpty()) {
                while (!queue2.isEmpty()) {
                    target = queue2.poll();
                    if (!queue2.isEmpty()) {
                        queue1.add(target);
                    }
                }
                return target;
            }
            if (queue2.isEmpty()) {
                while (!queue1.isEmpty()) {
                    target = queue1.poll();
                    if (!queue1.isEmpty()) {
                        queue2.add(target);
                    }
                }
            }
            return target;
        }
    }
}
