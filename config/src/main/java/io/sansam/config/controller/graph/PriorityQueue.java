package io.sansam.config.controller.graph;

/**
 * <p>
 * PriorityQueue
 * </p>
 *
 * @author houcb
 * @since 2020-04-27 14:25
 */
public class PriorityQueue {

    private Vertex[] nodes; // 小顶堆
    private int count;
    private int mod;    //当前最后元素的下标

    public PriorityQueue(int v) {
        this.nodes = new Vertex[v + 1];
        this.count = v;
        this.mod = 0;
    }

    public Vertex poll() {
        if (mod == 0) return null;
        Vertex out = nodes[1], temp;
        nodes[1] = nodes[mod];
        nodes[mod] = null;
        mod--;
        int i = 1;
        while (true) {
            int max = i;
            if (i * 2 <= mod && nodes[i * 2].getDist() >= nodes[i].getDist()) max = i * 2;
            if (i * 2 + 1 <= mod && nodes[max].getDist() >= nodes[i].getDist()) max = i * 2 + 1;
            if (max == i) break;
            temp = nodes[i];
            nodes[i] = nodes[max];
            nodes[max] = temp;
            i = max;
        }


        return null;
    }

    public int add(Vertex node) {
        if (mod >= count) return -1;
        nodes[mod++] = node;
        int i = mod;
        Vertex temp;
        while (i / 2 > 0 && nodes[i].getDist() < nodes[i / 2].getDist()) {
            temp = nodes[i];
            nodes[i] = nodes[i / 2];
            nodes[i / 2] = temp;
            i = i / 2;
        }

        return 1;
    }

    public void update(Vertex vertex) {
        Vertex[] old = nodes;


    }


}
