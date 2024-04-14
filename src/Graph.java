package src;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;

import com.mxgraph.model.mxGraphModel;
import com.mxgraph.view.mxGraph;
import com.mxgraph.swing.mxGraphComponent;
import javax.swing.*;


public class Graph {
    private final int numVertices;

    /**
     * Creates a HashMap, mapping an integer vertex to a set of edges,
     * which contains weights and vertices for adjacent vertices.
     */
    private HashMap<Integer, HashSet<Edge>> graph;


    public Graph (int numVertices) {
        this.numVertices = numVertices;
        graph = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            graph.put(i, new HashSet<>());
        }
    }

    /**
     * Adds an edge between two vertices.
     */
    public void addEdge(int source, int target, int weight) {
        assert source >= 0 && source < graph.size() : "Invalid source index: " + source;
        assert target >= 0 && target < graph.size() : "Invalid destination index: " + target;
        graph.get(source).add(new Edge (target, weight));
    }

    /**
     * Generates a string representation of the graph containing all vertices and their adjacent vertices.
     * Each vertex is listed along with its adjacent vertex and their corresponding weights.
     * If a vertex has no adjacent vertices, it is indicated as "None".
     * @return A string representation of the graph.
     */
    public void stringGraph() {
        String allVerticesAndEdges = "";
        for (Integer currVertex : graph.keySet()) {
            String currentLine = "";
            currentLine += "Vertex: [" + currVertex + "] | Adjacent Vertices: ";
            if ((graph.get(currVertex).isEmpty())) {
                currentLine += "None  ";
            } else {
                for (Edge currEdge : graph.get(currVertex)) {
                    currentLine += "[" + currEdge.target + ", Weight: " + currEdge.weight + "], ";
                } 
            }
            currentLine = currentLine.substring(0, currentLine.length() - 2);
            allVerticesAndEdges += currentLine + "\n";
        }
        System.out.print(allVerticesAndEdges);
    }

    /**
     * Generates a visual representation of a Graph in a grid-like pattern.
     */
    public void visualizeGraph() {
        mxGraph visualizationGraph = new mxGraph();
        Object parent = visualizationGraph.getDefaultParent();

        visualizationGraph.getModel().beginUpdate();
        try {
            HashMap<Integer, Object> vertexMap = new HashMap<>();
            int numVertices = this.graph.size();
            int cols = (int) Math.ceil(Math.sqrt(numVertices));
            int rows = (int) Math.ceil((double) numVertices / cols);
            int vertexSpacingX = 100; // Adjusts horizontal spacing between vertices.
            int vertexSpacingY = 100; // Adjusts vertical spacing between vertices.

            // Inserts vertices in a grid-like pattern.
            for (Integer vertex : this.graph.keySet()) {
                int colIndex = vertex % cols;
                int rowIndex = vertex / cols;
                int x = 50 + colIndex * vertexSpacingX;
                int y = 50 + rowIndex * vertexSpacingY;
                Object v = visualizationGraph.insertVertex(parent, null, vertex.toString(), x, y, 40, 40);
                vertexMap.put(vertex, v);
            }

            // Inserts edges.
            for (Integer vertex : this.graph.keySet()) {
                Object sourceVertex = vertexMap.get(vertex);
                for (Edge edge : this.graph.get(vertex)) {
                    Object targetVertex = vertexMap.get(edge.target);
                    visualizationGraph.insertEdge(parent, null, edge.weight, sourceVertex, targetVertex);
                }
            }
        } finally {
            visualizationGraph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(visualizationGraph);
        JFrame frame = new JFrame("Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.pack();
        frame.setVisible(true);
    }

    public int getNumVertices() {
        return numVertices;
    }

    public HashMap<Integer, HashSet<Edge>> getGraph() {
        return graph;
    }

    class Edge {
        private final int target;
        private final int weight;
        public Edge(int target, int weight) {
            assert weight >= 0 : "Invalid weight: " + weight;
            assert target >= 0 && target < numVertices: "Invalid target: " + target;
            this.target = target;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public int getTarget() {
            return target;
        }
    }
}
