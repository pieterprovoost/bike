# bike

Bike uses Dijkstra's algorithm to find a shortest path in the Toerisme Vlaanderen cyling network. 

The required data can be obtained from the WFS service at http://trip.toerismevlaanderen.be/gdi/WFSServer and need to be converted to WGS84 GeoJSON.

## Usage

```java
Engine engine = new Engine();
String begin = engine.getClosest("Weeldestuk Brugge");
String end = engine.getClosest("Stroelputstraat Veldegem");
List<BikePoint> path = engine.getPath(begin, end);
for (BikePoint p : path) {
    System.out.println(p.getNumber() + " " + p.getDistance());
}
```

```text
70 0.0
71 2426.0994289299033
72 2600.709568933383
96 5202.073548605184
94 8257.794245907426
91 12295.284235901556
88 15483.423599294183
```
