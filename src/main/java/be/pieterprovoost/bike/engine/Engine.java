package be.pieterprovoost.bike.engine;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.geojson.Feature;
import org.geojson.FeatureCollection;

import java.io.InputStream;
import java.util.Iterator;

public class Engine {

    public Engine() {

        try {

            InputStream inputStream = this.getClass().getResourceAsStream("trajecten.geojson");
            FeatureCollection featureCollection = new ObjectMapper().readValue(inputStream, FeatureCollection.class);

            Iterator it = featureCollection.iterator();

            while (it.hasNext()) {
                Feature feature = (Feature) it.next();
                String begin = feature.getProperties().get("begin_geoid").toString();
                String end = feature.getProperties().get("end_geoid").toString();
                String length = feature.getProperties().get("Shape_Length").toString();

                System.out.println(begin + " " + end + " " + length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
