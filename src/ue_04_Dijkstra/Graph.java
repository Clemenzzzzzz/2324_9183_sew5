package ue_04_Dijkstra;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Graph{
    List<Node> nodes;

    Comparator<Node> pq;
    //PQ --> das müsste treeset sein, Schlüssel ist Name der Node, Value ist Distanz
    // dann kann ich die priority queue immer überschreiben weil ja immer nur ein Schlüssel ist

    public void readGraphFromAdjacencyMatrixFile(Path file) {
        StringBuilder data = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Append each line to the StringBuilder
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(data);
    }

    public String getAllPaths() {
        return null;
    }

    public void calcWithDijkstra(String startNodeId) {

    }




}
