package ue_04_Dijkstra;

public class Edge {
    private int distance;

    private Node neighbour;

    public Edge(int distance, Node neighbour) {
        this.distance = distance;
        this.neighbour = neighbour;
    }
}
