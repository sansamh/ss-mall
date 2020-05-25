package io.sansam.config.controller.graph;

import java.util.LinkedList;

/**
 * <p>
 * Graph
 * </p>
 *
 * @author houcb
 * @since 2020-04-27 14:28
 */
public class Graph {

    private int v;
    private LinkedList<Edge> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int weight) {
        this.adj[s].add(new Edge(s, t, weight));
    }
}
