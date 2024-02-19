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


    public static void readGraphFromAdjacencyMatrixFile(Path file) {
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
        String dataString = data.toString();
        System.out.println(dataString);
        //data kann man hier verarbeiten
    }

    public String getAllPaths() {
        return null;
    }

    public void calcWithDijkstra(String startNodeId) {

    }

    public static void main(String[] args) {
        readGraphFromAdjacencyMatrixFile(Path.of("C:\\Schule\\5_Klasse\\SEW\\Java\\src\\ue_04_Dijkstra\\ressources\\Graph_A-H.csv"));
    }


}
