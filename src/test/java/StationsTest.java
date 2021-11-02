import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class StationsTest {

    @Test
    public void checkJson() throws IOException
    {
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
        assertEquals((Integer)1, station.features.get(0).properties.objectid);

    }

    @Test
    public void checkConnectingStations() throws IOException {
        //given
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/SubwayStations.json"));
        Stations stations = gson.fromJson(reader, Stations.class);

        //when
        stations.features.get(0).properties.getConnectingStations(stations);
        List<Stations.Station> connectingStations = stations.features.get(0).properties.connectingStations;

        //then
        assertEquals("Astor Pl", stations.features.get(0).properties.name);
        assertNotNull(connectingStations);
       // assertTrue(connectingStations.contains(stations.findStation(457)));
       // assertTrue(connectingStations.contains(105));
    }

}