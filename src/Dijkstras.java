package src;

import org.w3c.dom.Node;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Dijkstras {
    public static Graph graph;
    public Dijkstras(Graph graph) {
        this.graph = graph;
    }

    public HashMap<Integer, Integer> dijkstra(int source) {
        HashMap<Integer, Integer> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        HashSet<Integer> visited = new HashSet<>();

        // Initialize all distances to a maximum value.
        for (int i = 0; i < graph.getNumVertices(); i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            int currVertex = currNode.vertex;
                visited.add(currVertex);
                for (Graph.Edge adjacentEdge : graph.getGraph().get(currVertex)) {
                    int adjacent = adjacentEdge.getTarget();
                    int weight = adjacentEdge.getWeight();
                    int newDistance = distances.get(currVertex) + weight;
                    if (!visited.contains(adjacent)) {
                        if (newDistance < distances.get(adjacent)) {
                            distances.put(adjacent, newDistance);
                            pq.add(new Node(adjacent, newDistance));
                        }
                    }
                }
        }
        return distances;
    }

    public void printShortestPaths(int source) {
        HashMap<Integer, Integer> shortestPaths = dijkstra(source);
        for (int vertex : shortestPaths.keySet()) {
            String distance = String.valueOf(shortestPaths.get(vertex));
            if (Integer.parseInt(distance) == Integer.MAX_VALUE) {
                distance = "N/A";
            }
            System.out.println("Target: " + vertex + " | Distance: " + distance);
        }
    }
    private class Node {
        private final int vertex;
        private final int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
