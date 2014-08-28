package be.pieterprovoost.bike.engine;

import org.junit.Test;

import java.util.List;

public class EngineTest {

    @Test
    public void engineTest() {

        Engine engine = new Engine();
        String begin = engine.getClosest(51.1829304, 3.1785468);
        String end = engine.getClosest(51.1026222,3.1538162);
        List<BikePoint> path = engine.getPath(begin, end);
        for (BikePoint p : path) {
            System.out.println(p.getNumber() + " " + p.getDistance());
        }

    }

}
