package be.pieterprovoost.bike.engine;

import be.pieterprovoost.bike.exceptions.GeocodingException;
import org.junit.Test;

import java.util.List;

public class EngineTest {

    @Test
    public void engineTest() {
        try {
            Engine engine = new Engine();
            long startTime = System.nanoTime();
            String begin = engine.getClosest("aaWeeldestuk aaBrugge");
            String end = engine.getClosest("Stroelputstraat Veldegem");
            long midTime = System.nanoTime();
            List<BikePoint> path = engine.getPath(begin, end);
            long endTime = System.nanoTime();
            for (BikePoint p : path) {
                System.out.println(p.getNumber() + " " + p.getDistance());
            }
            long find = (midTime - startTime) / 1000000;
            long calculate = (endTime - midTime) / 1000000;
            System.out.println(find + " ms");
            System.out.println(calculate + " ms");
        } catch (GeocodingException e) {
            e.printStackTrace();
        }
    }

}
