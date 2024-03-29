package ue_04_Dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Graph {
    private final List<Node> nodes = new ArrayList<>();

    private Comparator<Node> pq;


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
                if (!line[j].isEmpty()){
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

    public String getAllPaths() {
        return null;
    }

    public void calcWithDijkstra(String startNodeId) {

    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                ", pq=" + pq +
                '}';
    }

    public static void main(String[] args) throws IOException {
        //readGraphFromAdjacencyMatrixFile(Path.of("C:\\Schule\\5_Klasse\\SEW\\Java\\src\\ue_04_Dijkstra\\ressources\\Graph_A-H.csv"));
        Graph a = new Graph(Path.of("C:\\Schule\\5_Klasse\\SEW\\Java\\src\\ue_04_Dijkstra\\ressources\\Graph_A-H.csv"));
        System.out.println(a);
    }


}
