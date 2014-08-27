package be.pieterprovoost.bike.engine;

import be.pieterprovoost.bike.dijkstra.Dijkstra;
import be.pieterprovoost.bike.dijkstra.Node;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;

import java.io.InputStream;
import java.util.*;

public class Engine {

    private Dijkstra dijkstra = new Dijkstra();
    Map<String, KPoint> nodes = new HashMap<String, KPoint>();

    public Engine() {

        try {

            // read paths and insert into Dijkstra

            InputStream inputStream = this.getClass().getResourceAsStream("trajecten.geojson");
            FeatureCollection featureCollection = new ObjectMapper().readValue(inputStream, FeatureCollection.class);

            Iterator it = featureCollection.iterator();
            while (it.hasNext()) {
                Feature feature = (Feature) it.next();
                String begin = feature.getProperties().get("begin_geoid").toString();
                String end = feature.getProperties().get("end_geoid").toString();
                String length = feature.getProperties().get("Shape_Length").toString();
                dijkstra.add(begin, end, Double.parseDouble(length));
                System.out.println("Path: " + begin + " " + end + " " + length);
            }

            // read nodes

            InputStream pointStream = this.getClass().getResourceAsStream("knooppunten.geojson");
            FeatureCollection pointCollection = new ObjectMapper().readValue(pointStream, FeatureCollection.class);

            Iterator pit = pointCollection.iterator();
            while (pit.hasNext()) {
                Feature feature = (Feature) pit.next();
                String geoid = feature.getProperties().get("geoid").toString();
                String number = feature.getProperties().get("knooppuntnr").toString();
                Point point = (Point) feature.getGeometry();
                Double latitude = point.getCoordinates().getLatitude();
                Double longitude = point.getCoordinates().getLongitude();
                KPoint node = new KPoint();
                node.setNumber(number);
                node.setLatitude(latitude);
                node.setLongitude(longitude);
                nodes.put(geoid, node);
                System.out.println("Node: " + geoid + " " + number + " " + latitude + " " + longitude);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getPath(String begin, String end) {
        List<Node> list = dijkstra.calculate(begin, end);
        for (Node node : list) {
            String id = node.getId();
            KPoint point = nodes.get(id);
            System.out.println(point.getNumber());
        }
    }

}
