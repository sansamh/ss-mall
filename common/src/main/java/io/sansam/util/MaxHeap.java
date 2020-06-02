package io.sansam.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * MaxHeap
 * </p>
 *
 * @author houcb
 * @since 2020-06-02 09:55
 */
public class MaxHeap<E extends Comparable> {

    static final int DEFAULT_CAPACITY = 16;
    ArrayList<E> data;

    public MaxHeap(int capacity) {
        this.data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 数组堆化 从最后一个节点的父节点开始 向下堆化
     *
     * @param arrs
     */
    public MaxHeap(E[] arrs) {
        this();
        if (arrs == null || arrs.length == 0) {
            return;
        }
        this.data = new ArrayList<>(Arrays.asList(arrs));
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    public int size() {
        return data.size();
    }

    // 树操作

    /**
     * 下标1开始，当前节点的父节点、左孩子、右孩子的索引就会有如下的关系：
     * <p>
     * 父节点的索引：index/2 (index为当前节点的索引)
     * 左孩子的索引：index*2
     * 右孩子的索引：index*2+1
     * <p>
     * 如果从数组的第一个节点开始存放数据的话，下标0开始，当前节点的父节点、左孩子、右孩子的索引就会有如下的关系：
     * <p>
     * 父节点的索引：(index-1)/2 (index为当前节点的索引)
     * 左孩子的索引：index*2+1
     * 右孩子的索引：index*2+2
     * ————————————————
     * 版权声明：本文为CSDN博主「岁月长ch」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_37044026/java/article/details/86714130
     */

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回index的父节点 从0开始
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index should bigger than 0");
        }
        if (index == 0) {
            return index;
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index should bigger than 0");
        }
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index should bigger than 0");
        }
        return index * 2 + 2;
    }

    public void swap(int i, int j) {
        E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    /**
     * 添加元素到末尾 然后调整堆 从最后一个元素和其父节点对比 如果大于父节点就交换位置 交换后再与父节点比较
     *
     * @param e
     */
    public void add(E e) {
        this.data.add(e);
        shiftUp(data.size() - 1);
    }

    private void shiftUp(int i) {
        while (i > 0 && data.get(i).compareTo(data.get(parent(i))) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        return data.get(0);
    }

    /**
     * 去掉最大元素 先将最大元素和末尾元素交换位置 然后移除末尾元素
     * 再从头部节点开始 向下堆化，对比左子节点和右子节点 找出最大子节点和当前节点比较，如果最大子节点大于当前节点 则交换，继续比较，否则不用交换则退出
     *
     * @return
     */
    public E removeMax() {
        E max = findMax();
        swap(0, size() - 1);
        data.remove(size() - 1);

        shiftDown(0);
        return max;
    }

    /**
     * 向下堆化
     *
     * @param i
     */
    private void shiftDown(int i) {
        // 先判断有没有左子节点
        E node;
        int index;
        while (leftChild(i) < data.size()) {
            index = leftChild(i);
            // 如果右子节点存在 并且 大于左子节点，则node为右子节点
            if (index + 1 < data.size() && data.get(index).compareTo(data.get(index + 1)) < 0) {
                index = rightChild(i);
            }
            // 比较node和父节点i节点 如果node大则交换
            node = data.get(index);
            if (node.compareTo(data.get(i)) > 0) {
                swap(i, index);
                i = index;
            } else {
                break;
            }
        }
    }
}
