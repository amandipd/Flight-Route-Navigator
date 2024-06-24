package src;

import java.util.HashMap;
import java.util.HashSet;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Graph {

    private HashMap<String, HashSet<Edge>> airportGraph; // Graph containing airports as vertices, edge weights as distances
    private HashMap<Integer, String>  airportIdToString;
    private HashMap<String, HashSet<Integer>> airportStringToId; // Handling if an airport has multiple ids.
    private HashSet<Integer> allAiportIds;

    public Graph (String airportIds, String airportDist) throws FileNotFoundException {
        airportGraph = new HashMap<>();
        airportIdToString = new HashMap<>();
        airportStringToId = new HashMap<>();
        allAiportIds = new HashSet<>();
        File aiportIdObj = new File(airportIds);
        File airportDists = new File(airportDist);

        Scanner idReader = new Scanner(aiportIdObj);
        Scanner distReader = new Scanner(airportDists);

        while(idReader.hasNextLine()) { // Reading airport_ids.csv
            String data = idReader.nextLine();
            String[] currLine = data.split("\",\"");
            int airportId = Integer.parseInt(currLine[0].substring(1));
            String airportName = currLine[1].substring(0, currLine[1].length() - 1);
            airportIdToString.put(airportId, airportName);
            airportStringToId.computeIfAbsent(airportName, k -> new HashSet<>());
            airportStringToId.get(airportName).add(airportId);
            allAiportIds.add(airportId);
        }

        while(distReader.hasNextLine()) { // Reading airport_distances.csv
            String data = distReader.nextLine();
            String[] currLine = data.split(",");
            String startAirport = airportIdToString.get(Integer.parseInt(currLine[1]));
            String destinationAirport = airportIdToString.get(Integer.parseInt(currLine[3]));
            int distance = (int) Double.parseDouble(currLine[4]);
            addEdge(startAirport, destinationAirport, distance);
        }
    }

    public void addEdge(String source, String target, int weight) {
        assert source != null: "Invalid source index: " + source;
        assert target != null: "Invalid destination index: " + target;
        airportGraph.computeIfAbsent(source, k -> new HashSet<>());
        airportGraph.get(source).add(new Edge (target, weight));
    }

    public HashMap<Integer, String> getAirportIdToString() {
        return airportIdToString;
    }

    public HashMap<String, HashSet<Integer>> getAirportStringToId() {
        return airportStringToId;
    }

    public HashMap<String, HashSet<Edge>> getAirportGraph() {
        return airportGraph;
    }

    class Edge {
        private final String target;
        private final int weight;
        public Edge(String target, int weight) {
            assert weight >= 0 : "Invalid weight: " + weight;
            assert target != null : "Invalid target: " + target;
            this.target = target;
            this.weight = (int) weight;
        }

        public int getWeight() {
            return weight;
        }

        public String getTarget() {
            return target;
        }
    }
}
