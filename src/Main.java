package src;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String airportIds = "data/airport_ids.csv";
        String airportDistances = "data/airport_distances.csv";
        Graph graph = new Graph(airportIds, airportDistances);
        graph.visualizeGraph();
        /*graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2,2);
        graph.addEdge(1,2,3);
        graph.addEdge(2,1,1);
        graph.addEdge(1,3,2);
        graph.addEdge(2,4,5);
        graph.addEdge(2,3,4);
        graph.addEdge(1,4,3);
        graph.addEdge(4,3, 1);*/

        //Dijkstras dijGraph = new Dijkstras(graph);
        //graph.visualizeGraph();
    }

    /**
     * Creates a randomized graph between a minimum number of vertices (inclusive)
     * and a maximum number of vertices (exclusive).
     */
    /*public static Graph createRandomGraph(int minVertexCount, int maxVertexCount, int maxWeight, int maxEdgesPerVertex) {
        Random random = new Random();
        int numOfVertices = getRandomIntegerInRange(random,  minVertexCount, maxVertexCount);
        Graph graph = new Graph(numOfVertices);

        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < maxEdgesPerVertex; j++) {
                int target = random.nextInt(numOfVertices);
                int weight = random.nextInt(maxWeight);
                graph.addEdge(i, target, weight);
            }
        }
        return graph;
    }

    /**
     * Generates a random number between minimum (inclusive) and maximum (exclusive).
     */
    /* public static int getRandomIntegerInRange(Random random, int lower, int upper) {
        return random.nextInt(upper - lower) + lower;
    } */

}
