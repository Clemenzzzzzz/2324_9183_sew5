package ue_04_Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Node implements Comparable<Node>{

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
        StringBuilder erg = new StringBuilder();
        ArrayList<Node> path = getPathToSelf(this);
        erg.append(path.get(path.size() - 1).getId());
        if (path.size() == 1) { // TODO is start node wird nicht angezeigt
            erg.append(": is start node");
        } else {
            for (int i = path.size() - 2; i >= 0; i--) {
                erg.append(" --(").append(path.get(i).getDistance()).append(")-> ").append(path.get(i).getId());
            }
        }
        erg.append("\n");
        return erg.toString();
    }


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

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public void init() {
        isVisited = false;
        previous = null;
        distance = Integer.MAX_VALUE;
    }

    public void change(Node newPrevious, int newDistance) {
        previous = newPrevious;
        distance = newDistance;
    }

    public void setStartNode() {
        distance = 0;
    }



    public void visit() {
        isVisited = true;
        for (Edge edge: edges) {
            Graph.offerDistance(edge.getNeighbour(),this,edge.getDistance());
        }
    }

    public String getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

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

    public String stringOfEdges() {
        String erg = "";
        for (Edge e :
                edges) {
            erg += e.getNeighbour().getId() + ":" + e.getDistance() + " ";
        }
        return erg;
    }

    public boolean isStartNode(){
        return previous == null && distance == 0;
    }
}
