package be.pieterprovoost.bike.dijkstra;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Node> nodes = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();

    public Node findNode(String id) {
        for (Node n : nodes) {
            if (n.getId().equals(id)) {
                return n;
            }
        }
        return null;
    }

    public void clear() {
        nodes.clear();
        edges.clear();
    }

    public void reset() {
        for (Node node : nodes) {
            node.setDistance(Double.POSITIVE_INFINITY);
            node.setPredecessor(null);
        }
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public void add(Edge edge) {
        edges.add(edge);
    }

}
