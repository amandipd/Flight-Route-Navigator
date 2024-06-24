package src;

import java.util.*;

public class ShortestPath {

    private Graph graph;
    private HashMap<String, HashSet<Graph.Edge>> airportGraph;

    public ShortestPath(Graph airGraph) {
        this.graph = airGraph;
        airportGraph = graph.getAirportGraph();
    }

    public void dijkstra(String source, String target) {
        HashMap<String, Integer> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        HashSet<String> visited = new HashSet<>();
        HashMap<String, String> previous = new HashMap<>();

        // Initialize distances
        for (String vertex : airportGraph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            String currVertex = currNode.vertex;

            if (currVertex == null || !visited.add(currVertex)) continue;

            if (currVertex.equals(target)) { // Early termination if target is reached
                break;
            }

            for (Graph.Edge adjacentEdge : airportGraph.getOrDefault(currVertex, new HashSet<>())) {
                String adjacent = adjacentEdge.getTarget();
                int weight = adjacentEdge.getWeight();

                Integer currentDistance = distances.get(currVertex);
                if (currentDistance == null) continue; // Safety check
                if (currentDistance != Integer.MAX_VALUE) {
                    int newDistance = currentDistance + weight;
                    Integer adjacentDistance = distances.get(adjacent);
                    if (adjacentDistance == null || newDistance < adjacentDistance) {
                        distances.put(adjacent, newDistance);
                        pq.add(new Node(adjacent, newDistance));
                        previous.put(adjacent, currVertex);
                    }
                }
            }
        }

        // Check if the target is reachable
        Integer targetDistance = distances.get(target);
        if (targetDistance == null || targetDistance == Integer.MAX_VALUE) {
            System.out.println("\nThere is no path from " + source + " to " + target);
        } else {
            List<String> path = reconstructPath(previous, source, target);
            System.out.println("\nShortest Distance: " + targetDistance);
            for (int i = 1; i < path.size(); i++) {
                System.out.println("Stop " + i + ": " + path.get(i) + " Distance: " + distances.get(path.get(i)));
            }
        }
    }

    private List<String> reconstructPath(HashMap<String, String> previous, String source, String target) {
        LinkedList<String> path = new LinkedList<>();
        for (String at = target; at != null; at = previous.get(at)) {
            path.addFirst(at);
        }
        if (path.isEmpty() || !path.getFirst().equals(source)) {
            return Collections.emptyList(); // Return an empty list if there is no path from source to target
        }
        return path;
    }

    private class Node {
        private final String vertex;
        private final int distance;

        public Node(String vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}