package src;

import java.util.*;

public class Graph {
    private int numVertices;
    private HashMap<Integer, HashSet<Edge>> graph;

    /**
     * Creates a HashMap, mapping an integer vertex to a set of edges,
     * which contains weights and vertices for adjacent nodes.
     */

    public Graph (int numVertices) {
        this.numVertices = numVertices;
        graph = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            graph.put(i, new HashSet<>());
        }
    }

    /**
     // Adds an edge between two vertices.
     */
    public void addEdge(int source, int destination, int weight) {
        assert source >= 0 && source < graph.size() : "Invalid source index: " + source;
        assert destination >= 0 && destination < graph.size() : "Invalid destination index: " + destination;
        graph.get(source).add(new Edge (destination, weight));
        graph.get(destination).add(new Edge(source, weight));
    }

    /**
     * Generates a string representation of the graph containing all vertices and their adjacent nodes.
     * Each vertex is listed along with its adjacent nodes and their corresponding weights.
     * If a vertex has no adjacent nodes, it is indicated as "None".
     * @return A string representation of the graph.
     */
    public String toString() {
        String allVerticesAndEdges = "";
        for (Integer currVertex : graph.keySet()) {
            String currentLine = "";
            currentLine += "Vertex: [" + currVertex + "] | Adjacent Nodes: ";
            if ((graph.get(currVertex).isEmpty())) {
                currentLine += "None  ";
            } else {
                for (Edge currEdge : graph.get(currVertex)) {
                    currentLine += "[" + currEdge.destination + ", Weight: " + currEdge.weight + "], ";
                }
            }
            currentLine = currentLine.substring(0, currentLine.length() - 2);
            allVerticesAndEdges += currentLine + "\n";
        }
        return allVerticesAndEdges;
    }

    private class Edge {
        private final int destination;
        private final int weight;
        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}
