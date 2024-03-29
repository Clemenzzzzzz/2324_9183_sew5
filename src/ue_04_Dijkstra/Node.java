package ue_04_Dijkstra;

import java.util.TreeSet;

public class Node implements Comparable{

    String id;

    TreeSet<Edge> Edges;

    int distance;

    Node previous;

    boolean isVisited;

    public Node(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", Edges=" + Edges +
                ", distance=" + distance +
                ", previous=" + previous +
                ", isVisited=" + isVisited +
                '}';
    }

    public String getPath() {
        return null;
    }

    public void addEdge(Edge edge){
        Edges.add(edge);
    }

    public void init() {

    }

    public void change() {

    }

    //TODO maybe setStartNode


    public void visit() {

    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
