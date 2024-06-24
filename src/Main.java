package src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String airportIds = "data/airport_ids.csv";
        String airportDistances = "data/airport_distances.csv";
        Graph graph = new Graph(airportIds, airportDistances);
        ShortestPath path = new ShortestPath(graph);
        Scanner stringScanner = new Scanner(System.in);
        String departureAirport = getDeparture(stringScanner, graph);
        String arrivalAirport = getArrival(stringScanner, graph);
        path.dijkstra(departureAirport, arrivalAirport);
        System.out.println("\n" + "Enter 2 to enter new arrival and departure airports.");
        String input = stringScanner.nextLine().trim();
        while (input.equals("2")) {
            departureAirport = getDeparture(stringScanner, graph);
            arrivalAirport = getArrival(stringScanner, graph);
            path.dijkstra(departureAirport, arrivalAirport);
            System.out.println("\n" + "Enter 2 to enter new arrival and departure airports.");
            input = stringScanner.nextLine().trim();
        }
    }

    public static String getDeparture(Scanner stringScanner, Graph graph) {
        System.out.println("Please enter your departure airport: Enter 1 if you'd like to see a list of all airports." + "\n");
        String departure = stringScanner.nextLine().trim();
        Set<String> airportList = graph.getAirportStringToId().keySet();

        if (departure.equals("1")) {
            for (String airport : airportList) {
                System.out.println(airport);
            }
            System.out.println("\n" + "Please enter your departure airport:" + "\n");
            departure = stringScanner.nextLine().trim();
        }
        while (!airportList.contains(departure)) {
            System.out.println("The departure airport you entered is not found. Please enter a valid departure airport.");
            departure = stringScanner.nextLine().trim();
        }
        return departure;
    }

    public static String getArrival(Scanner stringScanner, Graph graph) {
        System.out.println("\n" + "Please enter your arrival airport: Enter 1 if you'd like to see a list of all airports." + "\n");
        String arrival = stringScanner.nextLine().trim();
        Set<String> airportList = graph.getAirportStringToId().keySet();

        if (arrival.equals("1")) {
            for (String airport : airportList) {
                System.out.println(airport);
            }
            System.out.println("\n" + "Please enter your arrival airport:" + "\n");
            arrival = stringScanner.nextLine().trim();
        }
        while (!airportList.contains(arrival)) {
            System.out.println("The arrival airport you entered is not found. Please enter a valid arrival airport.");
            arrival = stringScanner.nextLine().trim();
        }
        return arrival;
    }
}
