package ue_04_Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Node implements Comparable{

    private String id;

    private final List<Edge> edges = new ArrayList<>();

    private int distance = Integer.MAX_VALUE;

    private Node previous = null;

    boolean isVisited;

    public Node(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", Edges=" + edges +
                ", distance=" + distance +
                ", previous=" + previous +
                ", isVisited=" + isVisited +
                '}';
    }

    public String getPath() {
        return null;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
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

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String stringOfEdges() {
        String erg = "";
        for (Edge e :
                edges) {
            erg += e.getNeighbour().getId() + ":" + e.getDistance() + " ";
        }
        return erg;
    }
}
