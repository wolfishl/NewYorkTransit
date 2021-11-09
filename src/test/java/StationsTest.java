import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class StationsTest {

    public Stations givenStations() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));

        Stations stations = gson.fromJson(reader, Stations.class);
        return stations;
    }

    @Test
    public void checkJson() throws IOException {
        //given
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));

        //when
        Stations station = gson.fromJson(reader, Stations.class);

        //then
        assertEquals("Astor Pl", station.features.get(0).properties.name);
        assertEquals("4-6-6 Express", station.features.get(0).properties.line);
        assertEquals(-73.99106999861966, station.features.get(0).geometry.getx(), 0.01);
        assertEquals(40.73005400028978, station.features.get(0).geometry.gety(), 0.01);
        station.features.get(0).properties.parseLines();
        assertEquals("4", station.features.get(0).properties.parsedLines[0]);
        assertEquals("6", station.features.get(0).properties.parsedLines[1]);
        assertEquals("6 Express", station.features.get(0).properties.parsedLines[2]);
        assertEquals((Integer) 1, station.features.get(0).properties.objectid);

    }

    @Test
    public void checkConnectingStations() throws IOException {
        //given
        Stations stations = givenStations();

        //when
        stations.features.get(0).properties.findConnectingStations(stations);
        List<Stations.Station> connectingStations = stations.features.get(0).properties.connectingStations;

        //then
        assertEquals("Astor Pl", stations.features.get(0).properties.name);
        assertNotNull(connectingStations);
        assertTrue(connectingStations.contains(stations.findStation(457)));
        assertTrue(connectingStations.contains(stations.findStation(105)));
    }

    @Test
    public void checkShortestPath_one() throws IOException {
        //given
        Stations stations = givenStations();

        //when
        Path path = stations.findShortestPath(stations.findStation(55), stations.findStation(186));

        //then
        assertEquals(1, path.length);
        //add checking stations
    }

    @Test
    public void checkShortestPath_two() throws IOException {
        //given
        Stations stations = givenStations();

        //when
        Path path = stations.findShortestPath(stations.findStation(32), stations.findStation(105));

        //then
        assertEquals(2, path.length);
        assertEquals(stations.findStation(32), path.stationsOnPath.get(0));
        assertEquals(stations.findStation(31), path.stationsOnPath.get(1));
        assertEquals(stations.findStation(105), path.stationsOnPath.get(2));
    }

    @Test
    public void checkShortestPath_three() throws IOException {
        //given
        Stations stations = givenStations();

        //when
        Path path = stations.findShortestPath(stations.findStation(12), stations.findStation(302));

        //then
        assertEquals(5, path.length);
        assertEquals(stations.findStation(12), path.stationsOnPath.get(0));
        assertEquals(stations.findStation(224), path.stationsOnPath.get(1));
        assertEquals(stations.findStation(35), path.stationsOnPath.get(2));
        assertEquals(stations.findStation(301), path.stationsOnPath.get(3));
        assertEquals(stations.findStation(373), path.stationsOnPath.get(4));
        assertEquals(stations.findStation(302), path.stationsOnPath.get(5));

        //add stations
    }

    @Test
    public void checkGetDistance() throws IOException {
        //given
        Stations stations = givenStations();
        Stations.Geometry location1 = new Stations.Geometry(-73.99106999861966,40.73005400028978);
        Stations.Geometry location2 = new Stations.Geometry(-5, 30);

        //when
        double distance1 = stations.features.get(0).geometry.getDistance(location1);
        double distance2 = stations.features.get(0).geometry.getDistance(location2);

        //then
        assertEquals(0, distance1, 0.01);
        assertEquals(69.82, distance2, 0.01);
    }

    @Test
    public void checkGetNearestStation() throws IOException {
        //given
        Stations stations = givenStations();
        Stations.Geometry location1 = new Stations.Geometry(-73.99106999861966,40.73005400028978);
        Stations.Geometry location2 = new Stations.Geometry(-73.991062,40.73005);

        //when
        Stations.Station nearestStation1 = stations.getNearestStation(location1);
        Stations.Station nearestStation2 = stations.getNearestStation(location2);

        //then
        assertEquals((Integer)1, nearestStation1.properties.objectid);
        assertEquals((Integer)1, nearestStation2.properties.objectid);
    }


    @Test
    public void checkFindRoute() throws IOException{
        //given
        Stations stations = givenStations();
        Stations.Geometry starting = new Stations.Geometry(-73.99106999861966,40.73005400028978);
        Stations.Geometry ending = new Stations.Geometry(-73.989958,40.734673000996125);

        //when
        Path route = stations.findRoute(starting, ending);

        //then
        assertEquals(stations.findStation(1), route.stationsOnPath.get(0));
        assertEquals(stations.findStation(105), route.stationsOnPath.get(1));
        assertEquals(1, route.length);
    }
}