package be.pieterprovoost.bike.dijkstra;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.util.List;

public class DijkstraTest {

    @Test
    public void testDijkstra() {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.add("a", "b", 1.0);
        dijkstra.add("a", "c", 2.0);
        dijkstra.add("b", "c", 1.0);
        dijkstra.add("b", "d", 3.0);
        dijkstra.add("c", "e", 2.0);
        dijkstra.add("c", "d", 1.0);
        dijkstra.add("b", "e", 2.0);
        dijkstra.add("d", "e", 4.0);
        dijkstra.add("d", "f", 3.0);
        dijkstra.add("e", "f", 3.0);

        List<Node> path = dijkstra.calculate("a", "f");
        for (Node node : path) {
            System.out.println(node.getId() + " (" + node.getDistance() + ")");
        }
        assertTrue(path.get(path.size() - 1).getDistance() == 6.0);

        dijkstra.add("a", "f", 1.0);
        path = dijkstra.calculate("a", "f");
        for (Node node : path) {
            System.out.println(node.getId() + " (" + node.getDistance() + ")");
        }
        assertTrue(path.get(path.size() - 1).getDistance() == 1.0);
    }

}
