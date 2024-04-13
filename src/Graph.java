package src;

import java.util.*;

public class Graph {
    private int numVertices;
    private HashMap<Integer, HashSet<Edge>> adjacencyList;

    public Graph (int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new HashSet<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge (destination, weight));
    }


    private class Edge {
        private int weight;
        private int destination;
        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public int getDestination() {
            return destination;
        }

        public String toString() {
            return "Weight: " + weight + " Destination: " + destination;
        }
    }


}
