package io.sansam.config.controller;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * <p>
 * Graph
 * </p>
 *
 * @author houcb
 * @since 2020/3/27 10:16 上午
 */
public class Graph {

    // 是否找到
    boolean found = false;
    /**
     * 顶点个数
     */
    private int v;
    /**
     * 各个定点相连的链表数组 无向图
     * 1 2 3
     * 2 1 4 5
     * 3 1 6 7
     */
    private LinkedList<Integer>[] linkedPoint;

    /**
     * 构造函数
     *
     * @param v
     */
    public Graph(int v) {
        this.v = v;
        linkedPoint = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            linkedPoint[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        final Graph graph = new Graph(8);
        graph.add(0, 1);
        graph.add(0, 3);
        graph.add(1, 0);
        graph.add(1, 2);
        graph.add(1, 4);
        graph.add(2, 1);
        graph.add(2, 5);
        graph.add(3, 0);
        graph.add(3, 4);
        graph.add(4, 3);
        graph.add(4, 1);
        graph.add(4, 5);
        graph.add(4, 6);
        graph.add(5, 4);
        graph.add(5, 2);
        graph.add(5, 7);
        graph.add(6, 4);
        graph.add(6, 7);
        graph.add(7, 6);
        graph.add(7, 5);

//        graph.bfs(1, 5);
        graph.dfs(1, 5);
    }

    /**
     * 无向图增加
     *
     * @param s 起点
     * @param t 终点
     */
    public void add(int s, int t) {
        linkedPoint[s].add(t);
        linkedPoint[t].add(s);
    }

    /**
     * 起点增加终点数组
     *
     * @param s  起点
     * @param ts 终点数组
     */
    public void add(int s, int[] ts) {
        for (int t : ts) {
            linkedPoint[s].add(t);
        }
    }

    /**
     * 广度优先算法
     *
     * @param s 起点
     * @param t 终点
     */
    public void bfs(int s, int t) {
        if (s == t) {
            System.out.println(s);
        }
        // 1. 记录当前顶点是否被访问过数组
        boolean[] visited = new boolean[v];
        visited[s] = true;
        // 2. 当前访问的顶点 但其相连的顶点还未被访问的 队列 起点为s
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // 3. 记录访问路径
        int[] path = new int[v];
        for (int i = 0; i < v; i++) {
            path[i] = -1;
        }
        int p, n;
        while (!Objects.isNull(queue.peek())) {
            p = queue.poll();
            for (int i = 0, j = linkedPoint[p].size(); i < j; i++) {
                n = linkedPoint[p].get(i);
                // 检查顶点是否被访问过
                if (!visited[n]) {
                    // 记录为已经访问过
                    visited[n] = true;
                    // 记录此节点前面的节点
                    path[n] = p;
                    // 如果匹配到了则结束
                    if (t == n) {
                        // 找到了 输出路径
                        printPath(path, s, t);
                        return;
                    }
                    // 未匹配则进入队列
                    queue.offer(n);
                }
            }
        }
    }

    private void printPath(int[] path, int s, int t) {
        if (path[t] != -1 && t != s) {
            printPath(path, s, path[t]);
        }
        System.out.print(t + " ");

    }

    /**
     * 深度优先算法
     *
     * @param s 起点
     * @param t 终点
     */
    public void dfs(int s, int t) {
        if (s == t) {
            System.out.println(s);
        }
        // 1. 是否找到
        found = false;
        // 2. 是否访问过
        boolean[] visited = new boolean[v];
        // 3. 路径
        int[] path = new int[v];
        for (int i = 0; i < v; i++) {
            path[i] = -1;
        }
        // 搜索
        recurDfs(s, t, visited, path);
        // 输出
        printPath(path, s, t);
    }

    private void recurDfs(int s, int t, boolean[] visited, int[] path) {
        if (found) return;
        visited[s] = true;
        if (s == t) {
            found = true;
            return;
        }
        int c;
        for (int i = 0; i < linkedPoint[s].size(); i++) {
            c = linkedPoint[s].get(i);
            // 未访问过
            if (!visited[c]) {
                path[c] = s;
                recurDfs(c, t, visited, path);
            }
        }

    }
}
