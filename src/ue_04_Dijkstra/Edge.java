package ue_04_Dijkstra;

public class Edge implements Comparable<Edge>{
    /**
     * distance of the edge
     */
    private final int distance;

    /**
     * neighbor of the edge
     */
    private final Node neighbour;

    public Edge(int distance, Node neighbour) {
        this.distance = distance;
        this.neighbour = neighbour;
    }

    /**
     * get the distance of the edge
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * gets the neighbor of the edge
     * @return neighbor
     */
    public Node getNeighbour() {
        return neighbour;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.distance > o.distance) {
            return 1;
        } else if (this.distance < o.distance){
            return -1;
        }
        return this.neighbour.getId().compareTo(o.neighbour.getId());
    }
}
