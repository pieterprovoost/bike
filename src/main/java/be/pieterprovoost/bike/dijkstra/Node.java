package be.pieterprovoost.bike.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String id;
    private List<Edge> edges = new ArrayList<Edge>();
    private Double distance = Double.POSITIVE_INFINITY;
    private Node predecessor;

    public Node(String id) {
        this.id = id;
    }

    public void add(Edge edge) {
        edges.add(edge);
    }

    public String getId() {
        return id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

}
