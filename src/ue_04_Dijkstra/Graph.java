package ue_04_Dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
    private static List<Node> nodes = new ArrayList<>();

    private static PriorityQueue<Node> pq = new PriorityQueue<>(); // TODO final ?


    public Graph(Path path) throws IOException {
        readGraphFromAdjacencyMatrixFile(path);
    }

    public void readGraphFromAdjacencyMatrixFile(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        String[] topLine = lines.get(0).split(";");
        if (lines.size() != topLine.length) {
            throw new IllegalArgumentException("There have to be as many rows as columns!");
        }
        for (int i = 1; i < lines.size(); i++) {
            String[] line = lines.get(i).split(";");
            if (!topLine[i].equals(line[0])) {
                throw new IllegalArgumentException("First element of the row needs to be the same value as the corresponding value in the first row!");
            }
            Node node = getOrCreateNode(line[0]);
            for (int j = 1; j < line.length; j++) {
                if (!line[j].isEmpty()) {
                    int distance = Integer.parseInt(line[j]);
                    node.addEdge(new Edge(distance, getOrCreateNode(topLine[j])));
                }
            }
        }
    }

    public Node getOrCreateNode(String id) {
        Node node = new Node(id);
        boolean nodeExists = false;
        for (Node n :
                nodes) {
            if (n.getId().equals(id)) {
                node = n;
                nodeExists = true;
            }
        }
        if (!nodeExists) {
            nodes.add(node);
        }
        return node;
    }

    public static String getAllPaths() {
        StringBuilder erg = new StringBuilder();
        boolean paths = pathsAvailable();
        for (Node n :
                nodes) {
            if (!paths) {
                erg.append("no path available for ").append(n.getId()).append(" [totalDistance: ?] ").append(n.stringOfEdges()).append("\n");
            } else {
                erg.append(n.getPath());
            }
        }
        return erg.toString();
    }

    public static boolean pathsAvailable(){
        for (Node n :
                nodes) {
            if (n.getPrevious() != null) {
                return true;
            }
        }
        return false;
    }

    public static void calcWithDijkstra(String startNodeId) {
        for (Node n :
                nodes) {
            n.init();
        }
        Node startNode = getNodebyId(startNodeId);
        pq.add(startNode);
        startNode.setStartNode();
        Node currentNode;
        while ((currentNode = pq.poll()) != null) {
            currentNode.visit();
        }
    }

    public static boolean offerDistance(Node node2change, Node newPrevious, int newDistance) {
        if (node2change.getDistance() == Integer.MAX_VALUE || node2change.getDistance() > newPrevious.getDistance() + newDistance) {
            node2change.change(newPrevious, newPrevious.getDistance() + newDistance);
            if (!node2change.isVisited) {
                pq.add(node2change);
            }
            return true;
        }
        return false;
    }

    public static Node getNodebyId(String s) {
        for (Node n :
                nodes) {
            if (n.getId().equals(s)) {
                return n;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String erg = "";
        String distance = "";
        for (Node n :
                nodes) {
            if (n.isStartNode()) {
                erg += n.getId() + "----> is start Node " + n.stringOfEdges() + "\n";
            } else {
                if (n.getDistance() == Integer.MAX_VALUE) {
                    distance = "?";
                } else {
                    distance = Integer.toString(n.getDistance());
                }
                erg += n.getId() + " [totalDistance: " + distance + "] " + n.stringOfEdges() + "\n";
            }
        }


        return erg;
    }

    public static void main(String[] args) throws IOException {
        //readGraphFromAdjacencyMatrixFile(Path.of("C:\\Schule\\5_Klasse\\SEW\\Java\\src\\ue_04_Dijkstra\\ressources\\Graph_A-H.csv"));
        Graph a = new Graph(Path.of("C:\\Schule\\5_Klasse\\SEW\\Java\\src\\ue_04_Dijkstra\\ressources\\Graph_A-H.csv"));
        System.out.println(a);
        System.out.println(getAllPaths());
        calcWithDijkstra("A");
        System.out.println(a);
        System.out.println(getAllPaths());
        calcWithDijkstra("D");
        System.out.println(a);
        System.out.println(getAllPaths());
    }


}
