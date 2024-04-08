package ue_04_Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Node implements Comparable<Node>{

    /**
     * id so the "name" od the Node
     */
    private String id;

    /**
     * the edges connected to the node
     */
    private final List<Edge> edges = new ArrayList<>();

    /**
     * the distance to the node
     */
    private int distance = Integer.MAX_VALUE;

    /**
     * the previous node before this one
     */
    private Node previous = null;

    /**
     * true/false if the node has already been visited
     */
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

    /**
     * String of the path to the node
     *
     * @return String of the path
     */
    public String getPath() {
        StringBuilder erg = new StringBuilder();
        ArrayList<Node> path = getPathToSelf(this);
        erg.append(path.get(path.size() - 1).getId());
        if (path.size() == 1) {
            erg.append(": is start node");
        } else {
            for (int i = path.size() - 2; i >= 0; i--) {
                erg.append(" --(").append(path.get(i).getDistance()).append(")-> ").append(path.get(i).getId());
            }
        }
        erg.append("\n");
        return erg.toString();
    }


    /**
     * gets the path of nodes to one as a list of nodes
     *
     * @param node asked node
     * @return path
     */
    private static ArrayList<Node> getPathToSelf(Node node) {
        ArrayList<Node> path = new ArrayList<>();
        path.add(node);
        Node previous = node.getPrevious();
        if (node.getDistance() == 0 || (previous != null && previous.getId().equals(node.getId()))) {
            return path;
        }
        if (previous == null) {
            return null;
        }
        while (previous.getDistance() != 0) {
            path.add(previous);
            previous = previous.getPrevious();
        }
        path.add(previous);
        return path;
    }

    /**
     * adds a new edge to a node
     *
     * @param edge the new edge
     */
    public void addEdge(Edge edge){
        edges.add(edge);
    }

    /**
     *initializes a node with standard parameters
     */
    public void init() {
        isVisited = false;
        previous = null;
        distance = Integer.MAX_VALUE;
    }

    /**
     * changes the parameters of the node, after a new distance is calculated
     *
     * @param newPrevious the new previous node
     * @param newDistance the new distance
     */
    public void change(Node newPrevious, int newDistance) {
        previous = newPrevious;
        distance = newDistance;
    }

    /**
     * defines a node as a start node
     */
    public void setStartNode() {
        distance = 0;
    }

    /**
     * visits a node
     */
    public void visit() {
        isVisited = true;
        for (Edge edge: edges) {
            Graph.offerDistance(edge.getNeighbour(),this,edge.getDistance());
        }
    }

    /**
     * gets the id of a node
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * gets the distance of a node
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * gets the previous node
     * @return previous node
     */
    public Node getPrevious() {
        return previous;
    }


    @Override
    public int compareTo(Node o) {
        if (this.distance == o.distance) {
            return this.id.compareTo(o.id);
        } else {
            return Integer.compare(this.distance, o.distance);
        }
    }

    /**
     * String of edges for toString of Graph
     *
     * @return string with the edges of a node
     */
    public String stringOfEdges() {
        String erg = "";
        for (Edge e :
                edges) {
            erg += e.getNeighbour().getId() + ":" + e.getDistance() + " ";
        }
        return erg;
    }

    /**
     * checks if a node is a start node
     * @return the start node
     */
    public boolean isStartNode(){
        return previous == null && distance == 0;
    }
}
