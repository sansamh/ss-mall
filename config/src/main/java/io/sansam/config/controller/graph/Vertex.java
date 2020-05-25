package io.sansam.config.controller.graph;

/**
 * <p>
 * Vertex
 * </p>
 *
 * @author houcb
 * @since 2020-04-27 15:02
 */
public class Vertex {

    /**
     * 顶点编号
     */
    private int id;
    /**
     * 起点到顶点的距离
     */
    private int dist;

    public Vertex(int id, int dist) {
        this.id = id;
        this.dist = dist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}
