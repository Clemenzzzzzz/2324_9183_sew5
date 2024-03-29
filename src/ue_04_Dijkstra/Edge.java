package ue_04_Dijkstra;

public class Edge {
    private final int distance;

    private final Node neighbour;

    public Edge(int distance, Node neighbour) {
        this.distance = distance;
        this.neighbour = neighbour;
    }

    public int getDistance() {
        return distance;
    }

    public Node getNeighbour() {
        return neighbour;
    }
}
