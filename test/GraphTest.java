package test;

import org.junit.jupiter.api.Test;
import src.Graph;

class GraphTest {
    @Test
    public void testGraphCreation() {
        int numVertices = 5;
        Graph graph = new Graph(numVertices);
        graph.addEdge(0,1, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(0, 2, 5);
        graph.addEdge(4, 0, 3);
        graph.addEdge(2, 4, 8);
        System.out.print(graph.toString());
    }

}