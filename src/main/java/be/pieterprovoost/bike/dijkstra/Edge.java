package be.pieterprovoost.bike.dijkstra;

public class Edge {

    private Node source;
    private Node destination;
    private Double distance;

    public Edge(Node source, Node destination, Double distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
