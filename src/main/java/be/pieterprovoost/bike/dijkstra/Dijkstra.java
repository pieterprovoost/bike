package be.pieterprovoost.bike.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    private Graph graph = new Graph();
    private List<Node> settled = new ArrayList<Node>();
    private List<Node> unsettled = new ArrayList<Node>();

    public void reset() {
        settled.clear();
        unsettled.clear();
        graph.reset();
    }

    private Node lowest() {
        if (!unsettled.isEmpty()) {
            Node lowest = unsettled.get(0);
            for (Node node : unsettled) {
                if (node.getDistance() < lowest.getDistance()) {
                    lowest = node;
                }
            }
            return lowest;
        }
        return null;
    }

    public void add(String sourceId, String destinationId, Double distance) {
        Node source = graph.findNode(sourceId);
        Node destination = graph.findNode(destinationId);
        if (source == null) {
            source = new Node(sourceId);
            graph.add(source);
        }
        if (destination == null) {
            destination = new Node(destinationId);
            graph.add(destination);
        }
        Edge edge = new Edge(source, destination, distance);
        source.add(edge);
        destination.add(edge);
        graph.add(edge);
    }

    private void neighbours(Node node) {
        for (Edge edge : node.getEdges()) {
            Node destination;
            if (edge.getSource() == node) {
                destination = edge.getDestination();
            } else {
                destination = edge.getSource();
            }
            if (!settled.contains(destination)) {
                Double distance = node.getDistance() + edge.getDistance();
                if (destination.getDistance() > distance) {
                    destination.setDistance(distance);
                    destination.setPredecessor(node);
                    unsettled.add(destination);
                }
            }
        }
    }

    public List<Node> calculate(String sourceId, String destinationId) {
        reset();
        Node current;
        Node source = graph.findNode(sourceId);
        Node destination = graph.findNode(destinationId);
        source.setDistance(0.0);
        unsettled.add(source);
        while (!unsettled.isEmpty()) {
            current = lowest();
            unsettled.remove(current);
            settled.add(current);
            neighbours(current);
        }
        List<Node> path = new ArrayList<Node>();
        path.add(destination);
        Node next = destination;
        while ((next = next.getPredecessor()) != null) {
            path.add(0, next);
        }
        return path;
    }

}
