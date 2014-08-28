package be.pieterprovoost.bike.engine;

import org.junit.Test;

import java.util.List;

public class EngineTest {

    @Test
    public void engineTest() {

        Engine engine = new Engine();
        String begin = engine.getClosest("Weeldestuk Brugge");
        String end = engine.getClosest("Stroelputstraat Veldegem");
        List<BikePoint> path = engine.getPath(begin, end);
        for (BikePoint p : path) {
            System.out.println(p.getNumber() + " " + p.getDistance());
        }

    }

}
