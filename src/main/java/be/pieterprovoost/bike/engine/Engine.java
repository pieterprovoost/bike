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
    Map<String, BikePoint> bikePoints = new HashMap<String, BikePoint>();

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
                BikePoint bikePoint = new BikePoint();
                bikePoint.setNumber(number);
                bikePoint.setLatitude(latitude);
                bikePoint.setLongitude(longitude);
                bikePoints.put(geoid, bikePoint);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Double rad(Double degrees) {
        return degrees * (Math.PI / 180.0);
    }

    private Double distance(Double lat1, Double lon1, Double lat2, Double lon2) {
        Double r = 6371000.0;
        Double dlat = rad(lat2 - lat1);
        Double dlon = rad(lon2 - lon1);
        Double a =
            Math.sin(dlat / 2) * Math.sin(dlat / 2) +
            Math.cos(rad(lat1)) * Math.cos(rad(lat2)) *
            Math.sin(dlon / 2) * Math.sin(dlon / 2);
        Double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double d = r * c;
        return d;
    }

    public String getClosest(Double latitude, Double longitude) {
        String closest = null;
        Double cd = Double.MAX_VALUE;
        Iterator<Map.Entry<String, BikePoint>> it = bikePoints.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, BikePoint> entry = it.next();
            Double d = distance(entry.getValue().getLatitude(), entry.getValue().getLongitude(), latitude, longitude);
            if (d < cd) {
                cd = d;
                closest = entry.getKey();
            }
        }
        return closest;
    }

    public List<BikePoint> getPath(String begin, String end) {
        List<Node> list = dijkstra.calculate(begin, end);
        List<BikePoint> pathPoints = new ArrayList<BikePoint>();
        for (Node node : list) {
            String id = node.getId();
            BikePoint point = bikePoints.get(id);
            point.setDistance(node.getDistance());
            pathPoints.add(point);
        }
        return pathPoints;
    }

}
