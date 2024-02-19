package ue_04_Dijkstra;

import java.util.TreeSet;

public class Node {

    String id;

    TreeSet<Edge> Edges;

    int distance;

    Node previous;

    boolean isVisited;

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

    public void addEdge(){

    }

    public void init() {

    }


    //TODO maybe setStartNode


    public void visit() {

    }
}
