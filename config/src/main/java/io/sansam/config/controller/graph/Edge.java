package io.sansam.config.controller.graph;

/**
 * <p>
 * Edge
 * </p>
 *
 * @author houcb
 * @since 2020-04-27 14:27
 */
public class Edge {

    private int sid;
    private int tid;
    private int weight;

    public Edge(int sid, int tid, int weight) {
        this.sid = sid;
        this.tid = tid;
        this.weight = weight;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
